package com.gumillea.matchaflavoured.core.data.providers.server;

import com.gumillea.inlandport.common.block.EdibleBlock;
import com.gumillea.inlandport.common.block.StorageBlock;
import com.gumillea.inlandport.common.block.crop.Age3Block;
import com.gumillea.inlandport.common.block.crop.Age7Block;
import com.gumillea.inlandport.common.block.family.wooden.WoodenBaseBlock;
import com.gumillea.inlandport.common.item.FoodItem;
import com.gumillea.inlandport.common.item.RecordItem;
import com.gumillea.inlandport.common.item.SimpleHatItem;
import com.gumillea.inlandport.core.data.providers.server.IPItemTagsProvider;
import com.gumillea.inlandport.core.util.tags.IPItemTags;
import com.gumillea.inlandport.core.util.utils.CompatUtil;
import com.gumillea.inlandport.core.util.utils.RegUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.item.DolabraItem;
import com.gumillea.matchaflavoured.common.item.EarringItem;
import com.gumillea.matchaflavoured.common.item.MattockItem;
import com.gumillea.matchaflavoured.core.data.tags.MFItemTags;
import com.gumillea.matchaflavoured.core.reg.MFItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

import static com.gumillea.inlandport.core.util.helpers.AutoDataGeneHelper.getItems;
import static com.gumillea.matchaflavoured.core.reg.MFItems.*;

public class MFItemTagsProvider extends IPItemTagsProvider {

    public MFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> lookUp) {
        super(output, provider, MatchaFlavoured.MODID, lookUp);
    }

    @Override
    public void addManualTags() {
        autoGeneItemTags(this, modId);

        addIfExist(MFItemTags.DOUGH, DOUGH.get());
        addIfExist(MFItemTags.FLOUR, FLOUR.get());

        addIfExist(MFItemTags.SMELTING_WHITELIST, Items.COPPER_INGOT, Items.RAW_COPPER);
        addIfExist(MFItemTags.BLASTING_WHITELIST, Items.ANCIENT_DEBRIS);
        addIfExist(MFItemTags.INGOT_SILVER, SILVER_INGOT.get());

        addIfExist(MFItemTags.CROP_TOMATO, TOMATOES.get());
        addIfExist(MFItemTags.FOOD_CHEESE, CHEESE.get());
        addIfExist(MFItemTags.FOOD_CHOCOLATE, CHOCOLATE.get());

        addIfExist(Tags.Items.FOODS_RAW_MEAT, SEITAN.get());
        addIfExist(Tags.Items.DRINKS_MILK, MILK_BOTTLE.get());

        addIfExist(Tags.Items.TOOLS_SHEAR, COPPER_SHEARS.get());
        addIfExist(Tags.Items.TOOLS_IGNITER, TINDER.get());
        addIfExist(Tags.Items.GEMS, RUBY.get(), OPAL.get(), AMBER.get(), TOPAZ.get());
        addIfExist(Tags.Items.RAW_MATERIALS, RAW_DIAMOND.get(), RAW_SILVER.get());
        addIfExist(Tags.Items.BONES, FISH_BONES.get());

        addIfExist(ItemTags.AXES, MFItemTags.DOLABRAS);
        addIfExist(ItemTags.PICKAXES, MFItemTags.DOLABRAS);
        addIfExist(ItemTags.HOES, MFItemTags.MATTOCKS);
        addIfExist(ItemTags.SHOVELS, MFItemTags.MATTOCKS);
    }

    public static void autoGeneItemTags(IPItemTagsProvider provider, String modId) {
        for (Item item : getItems(modId)) {
            switch (item) {
                case EarringItem ignored -> provider.tag(MFItemTags.EARRING).add(item);
                case DolabraItem ignored -> provider.tag(MFItemTags.DOLABRAS).add(item);
                case MattockItem ignored -> provider.tag(MFItemTags.MATTOCKS).add(item);
                default -> {}
            }
        }
    }

}
