package com.gumillea.matchaflavoured.core.data.tags;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.gumillea.inlandport.core.util.utils.CompatUtil.itemTag;


public class MFItemTags {
    public static final TagKey<Item> SMELTING_WHITELIST =  itemTag(MatchaFlavoured.MODID,"can_crafted_by_mud_klin");
    public static final TagKey<Item> BLASTING_WHITELIST =  itemTag(MatchaFlavoured.MODID,"can_crafted_by_blast_furnace");
    public static final TagKey<Item> FLOUR =  itemTag("flour");
    public static final TagKey<Item> DOUGH =  itemTag("dough");

    public static final TagKey<Item> CROP_TOMATO = cropTag("tomato");

    public static final TagKey<Item> FOOD_CHOCOLATE = foodTag("chocolate");
    public static final TagKey<Item> FOOD_CHEESE = foodTag("cheese");

    public static final TagKey<Item> INGOT_SILVER = ingotTag("silver");

    public static final TagKey<Item> EARRING = itemTag("earrings");

    public static TagKey<Item> curiosTag(String name) {
        return itemTag("curios", name);
    }

    public static TagKey<Item> cropTag(String name) {
        return itemTag("crops/" + name);
    }

    public static TagKey<Item> foodTag(String name) {
        return itemTag("foods/" + name);
    }

    public static TagKey<Item> drinkTag(String name) {
        return itemTag("drinks/" + name);
    }

    public static TagKey<Item> ingotTag(String name) {
        return itemTag("ingots/" + name);
    }
}
