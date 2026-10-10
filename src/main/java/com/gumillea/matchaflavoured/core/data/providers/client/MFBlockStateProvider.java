package com.gumillea.matchaflavoured.core.data.providers.client;

import com.gumillea.inlandport.core.data.providers.client.IPBlockStateProvider;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MFBlockStateProvider extends IPBlockStateProvider {

    public MFBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, MatchaFlavoured.MODID, helper);
    }

    public void overrideModels() {
    }


}
