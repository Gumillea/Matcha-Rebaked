package com.gumillea.matchaflavoured.core.data.providers.server;

import com.gumillea.inlandport.core.data.providers.server.IPBlockTagsProvider;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.gumillea.matchaflavoured.core.reg.MFBlocks.*;

public class MFBlockTagsProvider extends IPBlockTagsProvider {

    public MFBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, provider, MatchaFlavoured.MODID, helper);
    }

    @Override
    public void addManualTags() {
        addIfExist(BlockTags.MINEABLE_WITH_HOE, SACK.get());
        addIfExist(BlockTags.MINEABLE_WITH_AXE, SACK.get());
        addIfExist(BlockTags.MINEABLE_WITH_PICKAXE, OBOL_BLOCK.get());

        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL).remove(Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE);
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL).remove(SILVER_ORE.get(), DEEPSLATE_SILVER_ORE.get(), SULFUR_ORE.get());
    }
}
