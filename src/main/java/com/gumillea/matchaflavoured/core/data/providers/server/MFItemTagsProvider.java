package com.gumillea.matchaflavoured.core.data.providers.server;

import com.gumillea.inlandport.core.data.providers.server.IPItemTagsProvider;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.core.data.tags.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

import static com.gumillea.matchaflavoured.core.reg.MFItems.*;

public class MFItemTagsProvider extends IPItemTagsProvider {

    public MFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> lookUp) {
        super(output, provider, MatchaFlavoured.MODID, lookUp);
    }

    @Override
    public void addManualTags() {
        addIfExist(MFItemTags.DOUGH, DOUGH.get());
        addIfExist(MFItemTags.FLOUR, FLOUR.get());

        addIfExist(MFItemTags.SMELTING_WHITELIST, Items.COPPER_INGOT, Items.RAW_COPPER);
        addIfExist(MFItemTags.BLASTING_WHITELIST, Items.ANCIENT_DEBRIS);
        addIfExist(MFItemTags.INGOT_SILVER, SILVER_INGOT.get());

        addIfExist(MFItemTags.CROP_TOMATO, TOMATOES.get());
        addIfExist(MFItemTags.FOOD_CHEESE, CHEESE.get());
        addIfExist(MFItemTags.FOOD_CHOCOLATE, CHOCOLATE.get());
        addIfExist(MFItemTags.EARRING, LAPIS_EARRINGS.get(), EMERALD_EARRINGS.get(), DIAMOND_EARRINGS.get(), AMBER_EARRINGS.get(), OPAL_EARRINGS.get(), TOPAZ_EARRINGS.get());

        addIfExist(Tags.Items.FOODS_RAW_MEAT, SEITAN.get());
        addIfExist(Tags.Items.DRINKS_MILK, MILK_BOTTLE.get());

        addIfExist(Tags.Items.TOOLS_SHEAR, COPPER_SHEARS.get());
        addIfExist(Tags.Items.TOOLS_IGNITER, TINDER.get());
        addIfExist(Tags.Items.GEMS, RUBY.get(), OPAL.get(), AMBER.get(), TOPAZ.get());
        addIfExist(Tags.Items.RAW_MATERIALS, RAW_DIAMOND.get(), RAW_SILVER.get());
        addIfExist(Tags.Items.BONES, FISH_BONES.get());
    }

}
