package com.gumillea.matchaflavoured.common.block;

import com.gumillea.inlandport.core.util.utils.IPUtil;
import com.gumillea.matchaflavoured.common.block.entity.SackBlockEntity;
import com.gumillea.matchaflavoured.core.reg.MFBlocks;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class SackBlock extends BaseEntityBlock {
    public static final MapCodec<SackBlock> CODEC = RecordCodecBuilder.mapCodec((p_308835_) -> p_308835_.group(DyeColor.CODEC.optionalFieldOf("color").forGetter((p_304373_) -> Optional.ofNullable(p_304373_.color)), propertiesCodec()).apply(p_308835_, (p_304374_, p_304375_) -> new SackBlock(p_304374_.orElse(null), p_304375_)));
    private static final Component UNKNOWN_CONTENTS = Component.translatable("container.shulkerBox.unknownContents");
    public static final ResourceLocation CONTENTS = IPUtil.mcLoc("contents");
    @Nullable
    private final DyeColor color;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

    public SackBlock(@Nullable DyeColor color, Properties properties) {
        super(properties);
        this.color = color;
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPEN);
    }

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof SackBlockEntity) {
                player.openMenu((SackBlockEntity)blockentity);
                player.awardStat(Stats.OPEN_SHULKER_BOX);
            }

            return InteractionResult.CONSUME;
        }
    }

    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof SackBlockEntity sack) {
            if (!level.isClientSide && player.isCreative() && !sack.isEmpty()) {
                ItemStack itemstack = getColoredItemStack(getColor());
                itemstack.applyComponents(blockentity.collectComponents());
                ItemEntity itementity = new ItemEntity(level, (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, itemstack);
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            } else {
                sack.unpackLootTable(player);
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        BlockEntity blockentity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof SackBlockEntity sack) {
            builder = builder.withDynamicDrop(CONTENTS, (p_56219_) -> {
                for(int i = 0; i < sack.getContainerSize(); ++i) {
                    p_56219_.accept(sack.getItem(i));
                }

            });
        }

        return super.getDrops(state, builder);
    }

    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean p_56238_) {
        if (!state.is(state1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            super.onRemove(state, level, pos, state1, p_56238_);
            if (blockentity instanceof SackBlockEntity) {
                level.updateNeighbourForOutputSignal(pos, state.getBlock());
            }
        }

    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof SackBlockEntity) {
            ((SackBlockEntity)blockentity).recheckOpen();
        }

    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);
        if (stack.has(DataComponents.CONTAINER_LOOT)) {
            components.add(UNKNOWN_CONTENTS);
        }

        int i = 0;
        int j = 0;

        for(ItemStack itemstack : stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).nonEmptyItems()) {
            ++j;
            if (i <= 4) {
                ++i;
                components.add(Component.translatable("container.shulkerBox.itemCount", itemstack.getHoverName(), itemstack.getCount()));
            }
        }

        if (j - i > 0) {
            components.add(Component.translatable("container.shulkerBox.more", j - i).withStyle(ChatFormatting.ITALIC));
        }

    }

    @Nullable
    public static DyeColor getColorFromItem(Item item) {
        return getColorFromBlock(Block.byItem(item));
    }

    @Nullable
    public static DyeColor getColorFromBlock(Block block) {
        return block instanceof SackBlock ? ((SackBlock)block).getColor() : null;
    }

    public static Block getBlockByColor(@Nullable DyeColor color) {
        if (color == null) {
            return MFBlocks.SACK.get();
        } else {
            Block block;
            switch (color) {
                case WHITE -> block = Blocks.WHITE_SHULKER_BOX;
                case ORANGE -> block = Blocks.ORANGE_SHULKER_BOX;
                case MAGENTA -> block = Blocks.MAGENTA_SHULKER_BOX;
                case LIGHT_BLUE -> block = Blocks.LIGHT_BLUE_SHULKER_BOX;
                case YELLOW -> block = Blocks.YELLOW_SHULKER_BOX;
                case LIME -> block = Blocks.LIME_SHULKER_BOX;
                case PINK -> block = Blocks.PINK_SHULKER_BOX;
                case GRAY -> block = Blocks.GRAY_SHULKER_BOX;
                case LIGHT_GRAY -> block = Blocks.LIGHT_GRAY_SHULKER_BOX;
                case CYAN -> block = Blocks.CYAN_SHULKER_BOX;
                case BLUE -> block = Blocks.BLUE_SHULKER_BOX;
                case BROWN -> block = Blocks.BROWN_SHULKER_BOX;
                case GREEN -> block = Blocks.GREEN_SHULKER_BOX;
                case RED -> block = Blocks.RED_SHULKER_BOX;
                case BLACK -> block = Blocks.BLACK_SHULKER_BOX;
                case PURPLE -> block = Blocks.PURPLE_SHULKER_BOX;
                default -> throw new MatchException(null, null);
            }

            return block;
        }
    }

    @Nullable
    public DyeColor getColor() {
        return color;
    }

    public static ItemStack getColoredItemStack(@Nullable DyeColor color) {
        return new ItemStack(getBlockByColor(color));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SackBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

}
