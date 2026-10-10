package com.gumillea.matchaflavoured.core.data.providers.server;

import com.gumillea.inlandport.core.data.providers.server.IPDataMapProvider;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

import static com.gumillea.matchaflavoured.core.reg.MFItems.*;

public class MFDataMapProvider extends IPDataMapProvider {

    public MFDataMapProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MatchaFlavoured.MODID);
    }

    @Override
    public void gatherManualMaps() {
        addFuel(160F, STABILIZED_ESTUS.get());
        addFuel(10F, STRAW.get());
        addFuel(5F, TALL_DRY_GRASS.get());
        addFuel(2.5F, SHORT_DRY_GRASS.get());
    }
}