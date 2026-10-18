package com.gumillea.matchaflavoured.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BeaconCampfireBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 7.0F, 16.0F);
    protected static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BeaconCampfireBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51305_) {
        p_51305_.add(FACING);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().campfire(), 1F);
        }

        super.entityInside(state, level, pos, entity);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(10) == 0) {
            level.playLocalSound((double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + random.nextFloat(), random.nextFloat() * 0.7F + 0.6F, false);
        }

        if (random.nextInt(5) == 0) {
            for(int i = 0; i < random.nextInt(1) + 1; ++i) {
                level.addParticle(ParticleTypes.LAVA, (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, (double)(random.nextFloat() / 2.0F), 5.0E-5, (double)(random.nextFloat() / 2.0F));
            }
        }

        for(int i = 0; i < random.nextInt(5) + 1; ++i) {
            level.addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + (double)0.5F, (double)pos.getZ() + random.nextDouble(), 0, 0.05, 0);
        }

    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity living, ItemStack stack) {
        super.setPlacedBy(level, pos, state, living, stack);

        RandomSource random = level.getRandom();

        for (int i = 0; i < 30; ++i) {
            level.addParticle(ParticleTypes.FLAME, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + (double)0.75F, (double)pos.getZ() + random.nextDouble(), (random.nextDouble() - 0.5) * 0.3, (random.nextDouble() - 0.8) * 0.1, (random.nextDouble() - 0.5) * 0.3);
        }

        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WITHER_SPAWN, SoundSource.BLOCKS, 0.5F, 1.0F);
        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 0.5F, 1.0F);

        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 12000);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        WanderingTrader trader = EntityType.WANDERING_TRADER.create(level);
        if (trader == null) return;

        BlockPos targetPos = null;

        for (int i = 0; i < 64; ++i) {
            int offsetX = random.nextInt(16) - 8;
            int offsetY = random.nextInt(8) - 4;
            int offsetZ = random.nextInt(16) - 8;

            BlockPos checkPos = pos.offset(offsetX, offsetY, offsetZ);

            if (level.getBlockState(checkPos).isSolid() && level.isEmptyBlock(checkPos.above()) && level.isEmptyBlock(checkPos.above(2))) {
                targetPos = checkPos;
                break;
            }
        }

        if (targetPos != null) {
            trader.moveTo(targetPos.getX() + 0.5D, targetPos.getY() + 1.0D, targetPos.getZ() + 0.5D, random.nextFloat() * 360.0F, 0.0F);
        } else {
            trader.moveTo(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, random.nextFloat() * 360.0F, 0.0F);
        }

        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BELL_BLOCK, SoundSource.BLOCKS, 1.0F, 1.0F);
        trader.setDespawnDelay(48000);
        level.addFreshEntity(trader);

        for (int i = 0; i < 2; ++i) {
            TraderLlama llama = EntityType.TRADER_LLAMA.create(level);
            if (llama == null) continue;

            llama.moveTo(trader.getX() + random.nextInt(5) - 2, trader.getY(), trader.getZ() + random.nextInt(5) - 2, random.nextFloat() * 360.0F, 0.0F);
            llama.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.EVENT, null);
            llama.setLeashedTo(trader, true);
            level.addFreshEntity(llama);
        }

        level.setBlock(pos, Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, false), 11);
    }

    @Override
    protected VoxelShape getShape(BlockState p_51309_, BlockGetter p_51310_, BlockPos p_51311_, CollisionContext p_51312_) {
        return SHAPE;
    }

    @Override
    protected boolean isPathfindable(BlockState p_51264_, PathComputationType p_51267_) {
        return false;
    }

}
