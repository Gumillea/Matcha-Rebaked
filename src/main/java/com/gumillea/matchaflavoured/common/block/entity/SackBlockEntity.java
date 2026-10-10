package com.gumillea.matchaflavoured.common.block.entity;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.block.SackBlock;
import com.gumillea.matchaflavoured.core.reg.MFBlockEntityTypes;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class SackBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    public static final int COLUMNS = 9;
    public static final int ROWS = 3;
    public static final int CONTAINER_SIZE = 27;
    private static final int[] SLOTS = IntStream.range(0, CONTAINER_SIZE).toArray();
    private NonNullList<ItemStack> itemStacks;
    @Nullable
    private final DyeColor color;
    private final ContainerOpenersCounter openersCounter;

    public SackBlockEntity(@Nullable DyeColor p_155666_, BlockPos p_155667_, BlockState p_155668_) {
        super(MFBlockEntityTypes.SACK.get(), p_155667_, p_155668_);
        this.itemStacks = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
        this.color = p_155666_;
        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(Level p_155062_, BlockPos pos, BlockState p_155064_) {
                SackBlockEntity.this.playSound(SoundEvents.BUNDLE_INSERT, pos);
                SackBlockEntity.this.updateBlockState(p_155064_, true);
            }

            protected void onClose(Level p_155072_, BlockPos pos, BlockState p_155074_) {
                SackBlockEntity.this.playSound(SoundEvents.BUNDLE_REMOVE_ONE, pos);
                SackBlockEntity.this.updateBlockState(p_155074_, false);
            }

            protected void openerCountChanged(Level p_155066_, BlockPos p_155067_, BlockState p_155068_, int p_155069_, int p_155070_) {
            }

            protected boolean isOwnContainer(Player p_155060_) {
                if (p_155060_.containerMenu instanceof ChestMenu) {
                    Container container = ((ChestMenu)p_155060_.containerMenu).getContainer();
                    return container == SackBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    public SackBlockEntity(BlockPos pos, BlockState state) {
        super(MFBlockEntityTypes.SACK.get(), pos, state);
        this.itemStacks = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
        this.color = SackBlock.getColorFromBlock(state.getBlock());
        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(Level p_155062_, BlockPos pos, BlockState p_155064_) {
                SackBlockEntity.this.playSound(SoundEvents.BUNDLE_INSERT, pos);
                SackBlockEntity.this.updateBlockState(p_155064_, true);
            }

            protected void onClose(Level p_155072_, BlockPos pos, BlockState p_155074_) {
                SackBlockEntity.this.playSound(SoundEvents.BUNDLE_REMOVE_ONE, pos);
                SackBlockEntity.this.updateBlockState(p_155074_, false);
            }

            protected void openerCountChanged(Level p_155066_, BlockPos p_155067_, BlockState p_155068_, int p_155069_, int p_155070_) {
            }

            protected boolean isOwnContainer(Player player) {
                if (player.containerMenu instanceof ChestMenu) {
                    Container container = ((ChestMenu)player.containerMenu).getContainer();
                    return container == SackBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }


    @Override
    public int getContainerSize() {
        return this.itemStacks.size();
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @org.jetbrains.annotations.Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return true;
    }

    public static String KEY = "container." + MatchaFlavoured.MODID + ".sack";

    @Override
    protected Component getDefaultName() {
        return Component.translatable(KEY);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.itemStacks = nonNullList;
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ShulkerBoxMenu(i, inventory, this);
    }

    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.loadFromTag(tag, provider);
    }

    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.itemStacks, false, provider);
        }
    }

    public void loadFromTag(CompoundTag tag, HolderLookup.Provider provider) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag) && tag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(tag, this.itemStacks, provider);
        }
    }

    public void startOpen(Player p_58616_) {
        if (!this.remove && !p_58616_.isSpectator()) {
            this.openersCounter.incrementOpeners(p_58616_, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void stopOpen(Player p_58614_) {
        if (!this.remove && !p_58614_.isSpectator()) {
            this.openersCounter.decrementOpeners(p_58614_, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    void updateBlockState(BlockState p_58607_, boolean p_58608_) {
        this.level.setBlock(this.getBlockPos(), p_58607_.setValue(SackBlock.OPEN, p_58608_), 3);
    }

    void playSound(SoundEvent event, BlockPos pos) {
        this.level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), event, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
