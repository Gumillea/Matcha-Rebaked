package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.inlandport.core.util.helpers.reg.BlockEntityHelper;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.block.entity.SackBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public class MFBlockEntityTypes {
    public static final BlockEntityHelper HELPER = new BlockEntityHelper(MatchaFlavoured.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SackBlockEntity>> SACK = HELPER.reg("sack", SackBlockEntity::new, () -> Set.of(MFBlocks.SACK.get()), null);

}
