package com.gumillea.matchaflavoured.core.data.providers.server;

import com.gumillea.inlandport.core.util.tags.IPDamageTypeTags;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.gumillea.matchaflavoured.core.data.tags.MFBiomeTags.*;
import static net.minecraft.world.level.biome.Biomes.*;

public class MFBiomeTagsProvider extends BiomeTagsProvider {

    public MFBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, provider, MatchaFlavoured.MODID, helper);
    }

    protected void addTags(HolderLookup.Provider provider) {
        tag(IS_FRESHWATER).addTags(IS_FRESHWATER_COLD, IS_FRESHWATER_COOL, IS_FRESHWATER_TEMPERATE, IS_FRESHWATER_ARID, IS_FRESHWATER_TROPICAL);
        //tag(IS_FRESHWATER).addTag(IS_FRESHWATER_COLD).addTag(IS_FRESHWATER_COOL).addTag(IS_FRESHWATER_TEMPERATE).addTag(IS_FRESHWATER_ARID).addTag(IS_FRESHWATER_TROPICAL);

        tag(IS_FRESHWATER_COLD).add(GROVE, CHERRY_GROVE, STONY_PEAKS, JAGGED_PEAKS, FROZEN_PEAKS, SNOWY_PLAINS, SNOWY_SLOPES, SNOWY_TAIGA, FROZEN_RIVER);
        tag(IS_FRESHWATER_COOL).add(BIRCH_FOREST, OLD_GROWTH_BIRCH_FOREST, FOREST, MEADOW, STONY_SHORE, OLD_GROWTH_PINE_TAIGA, WINDSWEPT_HILLS, WINDSWEPT_FOREST, WINDSWEPT_GRAVELLY_HILLS);
        tag(IS_FRESHWATER_TEMPERATE).add(PLAINS, SUNFLOWER_PLAINS, RIVER, FLOWER_FOREST, CHERRY_GROVE);
        tag(IS_FRESHWATER_ARID).add(SAVANNA, SAVANNA_PLATEAU, WINDSWEPT_SAVANNA, BADLANDS, WOODED_BADLANDS, ERODED_BADLANDS);
        tag(IS_FRESHWATER_TROPICAL).add(JUNGLE, SPARSE_JUNGLE, BAMBOO_JUNGLE);

        tag(IS_SALTWATER_COLD).add(FROZEN_OCEAN, DEEP_FROZEN_OCEAN);
        tag(IS_SALTWATER_COOL).add(COLD_OCEAN, DEEP_COLD_OCEAN);
        tag(IS_SALTWATER_TEMPERATE).add(OCEAN, DEEP_OCEAN, BEACH);
        tag(IS_SALTWATER_WARM).add(LUKEWARM_OCEAN, DEEP_LUKEWARM_OCEAN);
        tag(IS_SALTWATER_HOT).add(WARM_OCEAN);
        tag(IS_BRACKISH_WATER).add(SWAMP, MANGROVE_SWAMP);
    }

}
