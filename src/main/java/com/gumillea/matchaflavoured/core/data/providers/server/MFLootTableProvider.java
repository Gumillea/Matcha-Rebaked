package com.gumillea.matchaflavoured.core.data.providers.server;

import com.gumillea.inlandport.core.data.providers.server.IPLootTableProvider;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

import static com.gumillea.inlandport.core.util.utils.LootTableUtil.cropAge;
import static com.gumillea.matchaflavoured.core.reg.MFBlocks.*;
import static com.gumillea.matchaflavoured.core.reg.MFBlocks.DEEPSLATE_QUARTZ_ORE;
import static com.gumillea.matchaflavoured.core.reg.MFItems.*;

public class MFLootTableProvider extends IPLootTableProvider {

    public MFLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, MatchaFlavoured.MODID, future, MFBlockLoot::new);
    }

    public static class MFBlockLoot extends IPBlockLoot {

        protected MFBlockLoot(HolderLookup.Provider provider, String modId) {
            super(provider, MatchaFlavoured.MODID);
        }

        public void addManualBlockLoots() {
            shulkerBox(SACK.get());

            ore(SILVER_ORE.get(), RAW_SILVER.get());
            ore(DEEPSLATE_SILVER_ORE.get(), RAW_SILVER.get());
            ore(QUARTZ_ORE.get(), Items.QUARTZ);
            ore(DEEPSLATE_QUARTZ_ORE.get(), Items.QUARTZ);

            ore(SULFUR_ORE.get(), SULFUR_CHUNK.get(), 3F, 5F);

            crop(TOMATOES_CROP.get(), TOMATOES.get(), TOMATO_SEEDS.get(), 3);

            nothing(WARDING_STONE.get());
        }

    }
}
