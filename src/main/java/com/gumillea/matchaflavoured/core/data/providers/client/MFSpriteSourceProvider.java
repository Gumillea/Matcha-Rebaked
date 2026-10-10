package com.gumillea.matchaflavoured.core.data.providers.client;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.client.WardingShieldRender;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class MFSpriteSourceProvider extends SpriteSourceProvider {
    public MFSpriteSourceProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MatchaFlavoured.MODID, existingFileHelper);
    }

    @Override
    protected void gather() {
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(WardingShieldRender.TEXTURE.texture(), Optional.empty()));
    }
}
