package com.gumillea.matchaflavoured.common.block;

import com.gumillea.matchaflavoured.core.reg.MFItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ClayFetishBlock extends Block {

    protected final List<BiConsumer<BlockPos, Player>> useItemOn = new ArrayList<>();
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 10, 12);

    public ClayFetishBlock(Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public ClayFetishBlock fetishEffect(BiConsumer<BlockPos, Player> action) {
        useItemOn.add(action);
        return this;
    }

    public ClayFetishBlock changesWeather(String type) {
        useItemOn.add(((pos, player) -> {
            ServerLevel server = (ServerLevel) player.level();
            switch (type) {
                case "clear" -> {
                    server.setWeatherParameters(12000, 0, false, false);
                    server.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(1), SoundSource.BLOCKS, 0.5F, 1.0F);
                }
                case "rain" -> {
                    server.setWeatherParameters(0, 12000, true, false);
                    server.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(3), SoundSource.BLOCKS, 0.5F, 1.0F);
                }
                case "thunder" -> {
                    server.setWeatherParameters(0, 12000, true, true);
                    server.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(3), SoundSource.BLOCKS, 0.5F, 1.0F);
                }
            }
        }));
        return this;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (stack.is(MFItems.ESTUS_DUST)) {
            if (level instanceof ServerLevel server) {
                server.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D, pos.getY() + 0.25, pos.getZ() + 0.5D, server.random.nextInt(6,12), 0.5, 0.5, 0.5, 0.05);
                stack.consume(1, player);
                for (BiConsumer<BlockPos, Player> action : useItemOn) {
                    action.accept(pos, player);
                }
            }
            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        return direction == Direction.DOWN && !state.canSurvive(levelAccessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    @Override
    protected boolean canSurvive(BlockState p_51209_, LevelReader p_51210_, BlockPos p_51211_) {
        return p_51210_.getBlockState(p_51211_.below()).isSolid();
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

}
