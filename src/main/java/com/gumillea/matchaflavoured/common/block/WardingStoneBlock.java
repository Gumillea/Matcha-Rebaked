package com.gumillea.matchaflavoured.common.block;

import com.gumillea.matchaflavoured.core.util.MFEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WardingStoneBlock extends Block {

    public WardingStoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity living, ItemStack stack) {
        super.setPlacedBy(level, pos, state, living, stack);

        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 20);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() == Difficulty.PEACEFUL) return;

        MFEvents.wardingEffect(level, pos, 3, 16, 16, 16);

        level.scheduleTick(pos, this, 20);
    }
}
