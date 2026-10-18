package com.gumillea.matchaflavoured.core.data.providers.server;


import com.gumillea.inlandport.core.data.providers.server.IPRecipeProvider;
import com.gumillea.inlandport.core.util.tags.IPItemTags;
import com.gumillea.inlandport.core.util.utils.CompatUtil;
import com.gumillea.inlandport.core.util.utils.RegUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;

import com.gumillea.matchaflavoured.common.item.SecretRecipeItem;
import com.gumillea.matchaflavoured.core.data.tags.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.gumillea.matchaflavoured.core.reg.MFItems.*;
import static com.gumillea.matchaflavoured.core.reg.MFBlocks.*;
import static com.gumillea.matchaflavoured.core.reg.MFItems.HEPATIZON_DOLABRA;

public class MFRecipeProvider extends IPRecipeProvider {

    public MFRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, MatchaFlavoured.MODID, provider);
    }

    @Override
    public void buildRecipes(RecipeOutput output, HolderLookup.Provider provider) {
        disableRecipes(output, Items.BAKED_POTATO);

        allStorageBlockRecipes(output);
        storageBlock3x3Recipe(output, STRAW_BALE.get(), STRAW.get());
        storageBlock3x3Recipe(output, OBOL_BLOCK.get(), OBOL.get());

        stoneFamilies(output, SULFUR.get());

        cookingRecipe(output, BAKED_APPLE.get(), IPItemTags.APPLE);
        cookingRecipe(output, BAKED_GOLDEN_APPLE.get(), Items.GOLDEN_APPLE);
        cookingRecipe(output, BAKED_PUMPKIN.get(), Tags.Items.CROPS_PUMPKIN);
        cookingRecipe(output, SWEET_BERRY_MASH.get(), IPItemTags.SWEET_BERRY);
        cookingRecipe(output, GLOW_BERRY_MASH.get(), IPItemTags.GLOW_BERRY);
        cookingRecipe(output, STEAMED_CARROTS.get(), IPItemTags.CARROT);
        cookingRecipe(output, STEAMED_GOLDEN_CARROTS.get(), Items.GOLDEN_CARROT);
        cookingRecipe(output, COOKED_FISH.get(), Ingredients.COOKED_FISH_INGREDIENTS);
        cookingRecipe(output, COOKED_LARGE_FISH.get(), Ingredients.COOKED_LARGE_FISH_INGREDIENTS);
        cookingRecipe(output, CHOCOLATE.get(), Items.COCOA_BEANS);
        cookingRecipe(output, BRAISED_BROWN_MUSHROOM.get(), Items.BROWN_MUSHROOM);
        cookingRecipe(output, BRAISED_RED_MUSHROOM.get(), Items.RED_MUSHROOM);
        cookingRecipe(output, BRAISED_CRIMSON_FUNGUS.get(), Items.CRIMSON_FUNGUS);
        cookingRecipe(output, BRAISED_WARPED_FUNGUS.get(), Items.WARPED_FUNGUS);
        cookingRecipe(output, NOPALES.get(), Items.CACTUS);
        cookingRecipe(output, Items.BREAD, DOUGH);
        cookingRecipe(output, FRIED_EGG.get(), Items.EGG);
        cookingRecipe(output, GRILLED_TOMATOES.get(), TOMATOES);
        cookingRecipe(output, HONEY_GINGER_TEA.get(), Items.HONEY_BOTTLE);
        cookingRecipe(output, GRILLED_MELON.get(), Items.MELON_SLICE);

        cookingRecipe(output, RAMEN.get(), 15F, RAMEN_STOCK);
        cookingRecipe(output, CURRY.get(), 15F, CURRY_STOCK);
        cookingRecipe(output, GREEN_CURRY.get(), 15F, GREEN_CURRY_STOCK);
        cookingRecipe(output, PANEER_MAKHANI.get(), 15F, PANEER_MAKHANI_STOCK);
        cookingRecipe(output, PUMPKIN_CURRY.get(), 15F, PUMPKIN_CURRY_STOCK);

        smeltingRecipe(output, RecipeCategory.FOOD, CHARRED_FISH.get(), 0.35F, 5F, Tags.Items.FOODS_RAW_FISH);
        smeltingRecipe(output, RecipeCategory.FOOD, CHARRED_MEAT.get(), 0.35F, 5F, Tags.Items.FOODS_RAW_MEAT);
        smeltingRecipe(output, RecipeCategory.FOOD, CHARRED_POTATO.get(), 0.35F, 5F, Items.POTATO);
        smeltingRecipe(output, RecipeCategory.BUILDING_BLOCKS, Items.GLASS, 0.35F, 5F, Items.QUARTZ);

        overrideBlasting(output, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7F, 5F, Ingredients.COPPER, "copper_ingot_from_blasting_raw_copper");
        overrideBlasting(output, RecipeCategory.MISC, Items.IRON_INGOT, 0.7F, 5F, Ingredients.IRON, "iron_ingot_from_blasting_raw_iron");
        overrideBlasting(output, RecipeCategory.MISC, Items.GOLD_INGOT, 0.7F, 5F, Ingredients.GOLD, "gold_ingot_from_blasting_raw_gold");
        blastingRecipe(output, RecipeCategory.MISC, Items.DIAMOND, 0.7F, 5F, Ingredients.DIAMOND);
        blastingRecipe(output, RecipeCategory.MISC, Items.NETHERITE_INGOT, 0.7F, 5F, Ingredients.NETHERITE);
        blastingRecipe(output, RecipeCategory.MISC, STEEL_ALLOY.get(), 0.7F, 5F, CARBON_RICH_IRON);
        blastingRecipe(output, RecipeCategory.MISC, SILVER_INGOT.get(), 0.7F, 5F, Ingredients.SILVER);

        shapelessRecipe(output, RecipeCategory.MISC, HEPATIZON_ALLOY.get(), 1, new Object[]{Tags.Items.INGOTS_COPPER, Tags.Items.INGOTS_COPPER, Tags.Items.INGOTS_COPPER, Tags.Items.INGOTS_COPPER, Tags.Items.INGOTS_COPPER, Tags.Items.INGOTS_COPPER, Tags.Items.INGOTS_COPPER, MFItemTags.INGOT_SILVER ,Tags.Items.INGOTS_GOLD});
        shapelessRecipe(output, RecipeCategory.MISC, ELECTRUM_ALLOY.get(), 1, new Object[]{Tags.Items.INGOTS_GOLD, Tags.Items.INGOTS_GOLD, Tags.Items.INGOTS_GOLD, Tags.Items.INGOTS_GOLD, MFItemTags.INGOT_SILVER, MFItemTags.INGOT_SILVER, MFItemTags.INGOT_SILVER, MFItemTags.INGOT_SILVER, DIVINE_FRAGMENT});
        shapelessRecipe(output, RecipeCategory.MISC, SHAKUDO_ALLOY.get(), 1, new Object[]{Items.COPPER_BLOCK, Items.COPPER_BLOCK, Items.COPPER_BLOCK, Items.COPPER_BLOCK, Items.GOLD_BLOCK, Items.GOLD_BLOCK});

        simpleSmithing(output, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), STEEL_ALLOY, Ingredient.of(IRON_MATTOCK.get(), Items.IRON_HOE), STEEL_MATTOCK.get());
        simpleSmithing(output, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), STEEL_ALLOY, Ingredient.of(IRON_DOLABRA.get(), Items.IRON_AXE), STEEL_DOLABRA.get());
        simpleSmithing(output, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), HEPATIZON_ALLOY, Ingredient.of(COPPER_MATTOCK.get(), Items.STONE_HOE), HEPATIZON_MATTOCK.get());
        simpleSmithing(output, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), HEPATIZON_ALLOY, Ingredient.of(COPPER_DOLABRA.get(), Items.STONE_AXE), HEPATIZON_DOLABRA.get());
        simpleSmithing(output, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), SHAKUDO_ALLOY, Ingredient.of(COPPER_MATTOCK.get(), Items.STONE_HOE), SHAKUDO_MATTOCK.get());
        simpleSmithing(output, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), SHAKUDO_ALLOY, Ingredient.of(COPPER_DOLABRA.get(), Items.STONE_AXE), SHAKUDO_DOLABRA.get());
        simpleSmithing(output, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), ELECTRUM_ALLOY, Ingredient.of(DIAMOND_MATTOCK.get(), Items.DIAMOND_HOE), ELECTRUM_MATTOCK.get());
        simpleSmithing(output, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), ELECTRUM_ALLOY, Ingredient.of(DIAMOND_DOLABRA.get(), Items.DIAMOND_AXE), ELECTRUM_DOLABRA.get());

        simpleSmithing(output, BLUEPRINT.get(), STEEL_ALLOY, Items.SHEARS, STEEL_SHEARS.get());
        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, WARDING_SWORD, SILVER_SWORD.get());
        simpleSmithing(output, BLUEPRINT.get(), NAZAR, Items.IRON_SWORD, WARDING_SWORD.get());
        simpleSmithing(output, BLUEPRINT.get(), NAZAR, Items.SHIELD, WARDING_SHIELD.get());

        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, Tags.Items.GEMS_LAPIS, LAPIS_EARRINGS.get());
        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, Tags.Items.GEMS_AMETHYST, AMETHYST_EARRINGS.get());
        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, Tags.Items.GEMS_EMERALD, EMERALD_EARRINGS.get());
        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, Tags.Items.GEMS_DIAMOND, DIAMOND_EARRINGS.get());

        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, NAZAR, NAZAR_CIRCLET.get());
        simpleSmithing(output, BLUEPRINT.get(), Tags.Items.INGOTS_COPPER, RUBY, RUBY_CIRCLET.get());
        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, HEPATIZON_ALLOY, HEPATIZON_LAUREL.get());

        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, AMBER, AMBER_EARRINGS.get());
        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, OPAL, OPAL_EARRINGS.get());
        simpleSmithing(output, BLUEPRINT.get(), MFItemTags.INGOT_SILVER, TOPAZ, TOPAZ_EARRINGS.get());

        alloySmithing(output, BLUEPRINT.get(), Items.IRON_INGOT, STEEL_ALLOY.get());
        alloySmithing(output, BLUEPRINT.get(), Items.DIAMOND, ELECTRUM_ALLOY.get());

        alloyTools(output, BLUEPRINT.get(), Items.STONE, HEPATIZON_ALLOY.get());
        alloyTools(output, BLUEPRINT.get(), Items.STONE, SHAKUDO_ALLOY.get());

        armorsRecipe(output, Items.COPPER_INGOT);
        extraToolsRecipe(output, Items.COPPER_INGOT);
        extraToolsRecipe(output, Items.IRON_INGOT);
        extraToolsRecipe(output, Items.DIAMOND);
        alloyArmors(output, MatchaFlavoured.MODID, BLUEPRINT.get(), Items.COPPER_INGOT, HEPATIZON_ALLOY.get());
        alloyArmors(output, MatchaFlavoured.MODID, BLUEPRINT.get(), Items.COPPER_INGOT, SHAKUDO_ALLOY.get());

        cookingRecipe(output, SHORT_DRY_GRASS.get(), Ingredients.SHORT_GRASS);
        cookingRecipe(output, TALL_DRY_GRASS.get(), Items.TALL_GRASS);
        cookingRecipe(output, MOLASSES.get(), IPItemTags.SUGARCANE);

        shapelessRecipe(output, RecipeCategory.FOOD, MILK_BOTTLE.get(), 4, new Object[]{Items.MILK_BUCKET, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE});
        shapelessRecipe(output, RecipeCategory.FOOD, CANNED_APPLES.get(), 1, new Object[]{Items.GLASS_BOTTLE, BAKED_APPLE, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, CANNED_GOLDEN_APPLES.get(), 1, new Object[]{Items.GLASS_BOTTLE, BAKED_GOLDEN_APPLE, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, SWEET_BERRY_JAM.get(), 1, new Object[]{Items.GLASS_BOTTLE, SWEET_BERRY_MASH, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, GLOW_BERRY_JAM.get(), 1, new Object[]{Items.GLASS_BOTTLE, GLOW_BERRY_MASH, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, PUMPKIN_JAM.get(), 1, new Object[]{Items.GLASS_BOTTLE, BAKED_PUMPKIN, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, RIND_JAM.get(), 1, new Object[]{Items.GLASS_BOTTLE, Items.MELON_SLICE, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, PICKLED_CARROTS.get(), 1, new Object[]{Items.GLASS_BOTTLE, IPItemTags.CARROT, Items.BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, PICKLED_GOLDEN_CARROTS.get(), 1, new Object[]{Items.GLASS_BOTTLE, Items.GOLDEN_CARROT, Items.BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, PICKLED_POTATOES.get(), 1, new Object[]{Items.GLASS_BOTTLE, IPItemTags.POTATO, Items.BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, PICKLED_TOMATOES.get(), 1, new Object[]{Items.GLASS_BOTTLE, MFItemTags.CROP_TOMATO, Items.BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, PICKLED_BROWN_MUSHROOMS.get(), 1, new Object[]{Items.GLASS_BOTTLE, BRAISED_BROWN_MUSHROOM, Items.BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, PICKLED_RED_MUSHROOMS.get(), 1, new Object[]{Items.GLASS_BOTTLE, BRAISED_RED_MUSHROOM, Items.BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, PICKLED_CRIMSON_FUNGUS.get(), 1, new Object[]{Items.GLASS_BOTTLE, BRAISED_CRIMSON_FUNGUS, Items.BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, PICKLED_WARPED_FUNGUS.get(), 1, new Object[]{Items.GLASS_BOTTLE, BRAISED_WARPED_FUNGUS, Items.BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, MEAD.get(), 1, new Object[]{Items.GLASS_BOTTLE, Tags.Items.FOODS_FRUIT, Items.BROWN_MUSHROOM, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, BUBBLING_MEAD.get(), 1, new Object[]{Items.GLASS_BOTTLE, Tags.Items.FOODS_FRUIT, Items.BROWN_MUSHROOM, Items.SUGAR, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, BUBBLING_MEAD.get(), 1, new Object[]{MEAD, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, MELON_SORBET.get(), 1, new Object[]{Items.GLASS_BOTTLE, Items.MELON_SLICE, Items.SNOWBALL, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, PUERQUITO.get(), 2, new Object[]{MOLASSES, Items.SUGAR, ESTUS_DUST, MFItemTags.DOUGH});

        shapelessRecipe(output, RecipeCategory.FOOD, BRUSCHETTA.get(), 1, new Object[]{MFItemTags.CROP_TOMATO, MFItemTags.FOOD_CHEESE, Items.BREAD});
        shapelessRecipe(output, RecipeCategory.FOOD, PUPUSA.get(), 4, new Object[]{MFItemTags.DOUGH, MFItemTags.FOOD_CHEESE, OIL, Ingredient.of(Items.COOKED_PORKCHOP, Items.COOKED_CHICKEN)});
        shapelessRecipe(output, RecipeCategory.FOOD, SWEET_BERRY_DANISH.get(), 1, new Object[]{SWEET_BERRY_MASH, Items.SUGAR, MFItemTags.FOOD_CHEESE, MFItemTags.DOUGH});
        shapelessRecipe(output, RecipeCategory.FOOD, GLOW_BERRY_CRUMBLE.get(), 1, new Object[]{GLOW_BERRY_MASH, Items.SUGAR, MFItemTags.FOOD_CHEESE, MFItemTags.DOUGH});
        shapelessRecipe(output, RecipeCategory.FOOD, ICE_CREAM.get(), 1, new Object[]{Tags.Items.FOODS_BREAD, Items.SNOWBALL, Items.SUGAR, Tags.Items.DRINKS_MILK, Items.BLUE_ORCHID});
        shapelessRecipe(output, RecipeCategory.FOOD, GIMMARI.get(), 1, new Object[]{Items.DRIED_KELP, IPItemTags.CARROT, Items.SUGAR, Tags.Items.EGGS, MFItemTags.FLOUR, OIL});
        shapelessRecipe(output, RecipeCategory.FOOD, LUMPIA.get(), 1, new Object[]{Items.DRIED_KELP, IPItemTags.CARROT, Tags.Items.FOODS_COOKED_MEAT, MFItemTags.DOUGH, OIL});
        shapelessRecipe(output, RecipeCategory.FOOD, FISH_AND_CHIPS.get(), 1, new Object[]{Items.BAKED_POTATO, Tags.Items.FOODS_COOKED_FISH, MFItemTags.FLOUR, OIL});
        shapelessRecipe(output, RecipeCategory.FOOD, LATKE.get(), 1, new Object[]{Tags.Items.FOODS_BREAD, Items.BAKED_POTATO, Tags.Items.EGGS, Tags.Items.DRINKS_MILK});

        shapelessRecipe(output, RecipeCategory.FOOD, PUMPKIN_EMPANADA.get(), 1, new Object[]{BAKED_PUMPKIN, Items.SUGAR, ESTUS_DUST, MFItemTags.DOUGH});
        shapelessRecipe(output, RecipeCategory.FOOD, APPLE_EMPANADA.get(), 1, new Object[]{BAKED_APPLE, Items.SUGAR, ESTUS_DUST, MFItemTags.DOUGH});
        shapelessRecipe(output, RecipeCategory.FOOD, GOLDEN_APPLE_EMPANADA.get(), 1, new Object[]{BAKED_GOLDEN_APPLE, Items.SUGAR, ESTUS_DUST, MFItemTags.DOUGH});
        shapelessRecipe(output, RecipeCategory.FOOD, CARROT_CUPCAKE.get(), 1, new Object[]{STEAMED_CARROTS, Items.SUGAR, ESTUS_DUST, MFItemTags.DOUGH, Items.COCOA_BEANS});
        shapelessRecipe(output, RecipeCategory.FOOD, GOLDEN_CARROT_CUPCAKE.get(), 1, new Object[]{STEAMED_GOLDEN_CARROTS, Items.SUGAR, ESTUS_DUST, MFItemTags.DOUGH, Items.COCOA_BEANS});
        shapelessRecipe(output, RecipeCategory.FOOD, BUNUELO.get(), 1, new Object[]{OIL, Items.SUGAR, ESTUS_DUST, MFItemTags.DOUGH});
        shapelessRecipe(output, RecipeCategory.FOOD, BROWNIE.get(), 1, new Object[]{MFItemTags.FOOD_CHOCOLATE, MFItemTags.FOOD_CHOCOLATE, Items.SUGAR, MFItemTags.DOUGH});

        shapelessRecipe(output, RecipeCategory.FOOD, FRENCH_TOAST.get(), 1, new Object[]{Tags.Items.FOODS_BREAD, Tags.Items.EGGS, ESTUS_DUST, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.FOOD, HONIED_FRENCH_TOAST.get(), 1, new Object[]{Tags.Items.FOODS_BREAD, Tags.Items.EGGS, ESTUS_DUST, Items.SUGAR, Items.HONEY_BOTTLE});
        shapelessRecipe(output, RecipeCategory.FOOD, HONIED_FRENCH_TOAST.get(), 1, new Object[]{FRENCH_TOAST, Items.HONEY_BOTTLE});

        secretRecipe(output, RecipeCategory.FOOD, SWEET_BERRY_TOAST.get(), 1, new Object[]{Tags.Items.FOODS_BREAD, Tags.Items.EGGS, ESTUS_DUST, Items.SUGAR, IPItemTags.SWEET_BERRY});
        secretRecipe(output, RecipeCategory.FOOD, SWEET_BERRY_TOAST.get(), 1, new Object[]{FRENCH_TOAST, IPItemTags.SWEET_BERRY});
        secretRecipe(output, RecipeCategory.FOOD, WARPED_FUNGUS_STROGANOFF.get(), 1, new Object[]{Items.BOWL, BRAISED_WARPED_FUNGUS, Items.COOKED_BEEF, MFItemTags.DOUGH, Tags.Items.DRINKS_MILK});
        secretRecipe(output, RecipeCategory.FOOD, GNOCCHI.get(), 1, new Object[]{Items.BOWL, Items.BAKED_POTATO, MFItemTags.FOOD_CHEESE, Tags.Items.EGGS, MFItemTags.FLOUR});
        secretRecipe(output, RecipeCategory.FOOD, CHORUS_MOCHI.get(), 4, new Object[]{Items.POPPED_CHORUS_FRUIT, Items.SNOWBALL, Items.SUGAR, Tags.Items.DRINKS_MILK, MFItemTags.DOUGH});

        shapelessRecipe(output, RecipeCategory.FOOD, BOKGUK.get(), 1, new Object[]{Items.BOWL, Items.DRIED_KELP, IPItemTags.CARROT, Items.PUFFERFISH, Tags.Items.FOODS_COOKED_FISH, BRAISED_BROWN_MUSHROOM});
        shapelessRecipe(output, RecipeCategory.FOOD, CRIMSON_FUNGUS_STROGANOFF.get(), 1, new Object[]{Items.BOWL, BRAISED_CRIMSON_FUNGUS, Items.COOKED_BEEF, MFItemTags.DOUGH, Tags.Items.DRINKS_MILK});
        shapelessRecipe(output, RecipeCategory.FOOD, RED_MUSHROOM_STROGANOFF.get(), 1, new Object[]{Items.BOWL, BRAISED_RED_MUSHROOM, Items.COOKED_BEEF, MFItemTags.DOUGH, Tags.Items.DRINKS_MILK});
        shapelessRecipe(output, RecipeCategory.FOOD, STROGANOFF.get(), 1, new Object[]{Items.BOWL, BRAISED_BROWN_MUSHROOM, Items.COOKED_BEEF, MFItemTags.DOUGH, Tags.Items.DRINKS_MILK});
        shapelessRecipe(output, RecipeCategory.FOOD, KONTOMIRE_STEW.get(), 1, new Object[]{Items.BOWL, TOMATOES, Items.DANDELION, Items.FERN, Tags.Items.FOODS_COOKED_FISH, Tags.Items.EGGS});
        shapelessRecipe(output, RecipeCategory.FOOD, APPLE_CINNAMON_OATMEAL.get(), 1, new Object[]{Items.BOWL, Items.HONEY_BOTTLE, Tags.Items.DRINKS_MILK, ESTUS_DUST, IPItemTags.APPLE, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS});
        shapelessRecipe(output, RecipeCategory.FOOD, PAD_THAI.get(), 1, new Object[]{Items.BOWL, MFItemTags.DOUGH, Tags.Items.FOODS_COOKED_FISH, Tags.Items.EGGS, IPItemTags.CARROT, Items.SUGAR});

        shapelessRecipe(output, RecipeCategory.FOOD, CURRY_STOCK.get(), 1, new Object[]{Items.BOWL, ESTUS_DUST, Items.SUGAR, MFItemTags.FLOUR, OIL, IPItemTags.POTATO, IPItemTags.CARROT, Tags.Items.FOODS_RAW_MEAT});
        shapelessRecipe(output, RecipeCategory.FOOD, GREEN_CURRY_STOCK.get(), 1, new Object[]{Items.BOWL, Items.GREEN_DYE , ESTUS_DUST, Items.SUGAR, MFItemTags.FLOUR, OIL, Tags.Items.FOODS_RAW_FISH});
        shapelessRecipe(output, RecipeCategory.FOOD, PANEER_MAKHANI_STOCK.get(), 1, new Object[]{Items.BOWL, TOMATOES, ESTUS_DUST, Items.SUGAR, Tags.Items.DRINKS_MILK, MFItemTags.FOOD_CHEESE, OIL});
        shapelessRecipe(output, RecipeCategory.FOOD, PUMPKIN_CURRY_STOCK.get(), 1, new Object[]{Items.BOWL, ESTUS_DUST, Items.SUGAR, BAKED_PUMPKIN, Items.SUNFLOWER,  Tags.Items.DRINKS_MILK, IPItemTags.CARROT, Items.BAMBOO, OIL});
        shapelessRecipe(output, RecipeCategory.FOOD, RAMEN_STOCK.get(), 1, new Object[]{Items.BOWL, Items.BROWN_MUSHROOM, Items.DRIED_KELP, Tags.Items.EGGS, MFItemTags.DOUGH, OIL, Tags.Items.FOODS_RAW_MEAT});

        shapedRecipe(output, RecipeCategory.TOOLS, Items.CLOCK, 1, new String[]{" g ", "gqg", " g "}, Map.of('g', Tags.Items.INGOTS_GOLD, 'q', Tags.Items.GEMS_QUARTZ));
        shapedRecipe(output, RecipeCategory.FOOD, CHEESE_PIZZA.get(), 4, new String[]{"ccc", "ttt", "ddd"}, Map.of('c', MFItemTags.FOOD_CHEESE, 't', MFItemTags.CROP_TOMATO, 'd', MFItemTags.DOUGH));
        shapedRecipe(output, RecipeCategory.FOOD, MEAT_PIZZA.get(), 4, new String[]{"cmc", "ttt", "ddd"}, Map.of('c', MFItemTags.FOOD_CHEESE, 't', MFItemTags.CROP_TOMATO, 'd', MFItemTags.DOUGH, 'm', Tags.Items.FOODS_COOKED_MEAT));
        shapedRecipe(output, RecipeCategory.FOOD, MUSHROOM_PIZZA.get(), 4, new String[]{"mcm", "ttt", "ddd"}, Map.of('c', MFItemTags.FOOD_CHEESE, 't', MFItemTags.CROP_TOMATO, 'd', MFItemTags.DOUGH, 'm', BRAISED_BROWN_MUSHROOM));
        shapedRecipe(output, RecipeCategory.FOOD, WARPED_PIZZA.get(), 4, new String[]{"mcm", "ttt", "ddd"}, Map.of('c', MFItemTags.FOOD_CHEESE, 't', MFItemTags.CROP_TOMATO, 'd', MFItemTags.DOUGH, 'm', BRAISED_WARPED_FUNGUS));

        shapelessRecipe(output, RecipeCategory.FOOD, CHEESE.get(), 4, new Object[]{Tags.Items.DRINKS_MILK, Items.SUGAR, Items.BROWN_MUSHROOM});

        shapelessRecipe(output, RecipeCategory.MISC, FLOUR.get(), 1, new Object[]{Items.WHEAT_SEEDS, Items.WHEAT_SEEDS, Items.WHEAT_SEEDS});
        shapelessRecipe(output, RecipeCategory.MISC, FLOUR.get(), 9, new Object[]{FLOUR_BAG});
        shaped3x3Recipe(output, RecipeCategory.MISC, FLOUR_BAG.get(), 1, MFItemTags.FLOUR);

        shaped2x2Recipe(output, RecipeCategory.BUILDING_BLOCKS, SULFUR.get(), 1, SULFUR_CHUNK);
        shapelessRecipe(output, RecipeCategory.MISC, SULFUR_CHUNK.get(), 4, new Object[]{SULFUR});

        shapelessRecipe(output, RecipeCategory.MISC, SEITAN.get(), 1, new Object[]{MFItemTags.DOUGH, Items.POTION, Ingredient.of(Items.CARROT, Items.DRIED_KELP, Items.POTATO)});
        shapelessRecipe(output, RecipeCategory.MISC, OIL.get(), 1, new Object[]{Items.GLASS_BOTTLE, Ingredients.OIL_INGREDIENTS, Ingredients.OIL_INGREDIENTS, Ingredients.OIL_INGREDIENTS});
        shapelessRecipe(output, RecipeCategory.MISC, DOUGH.get(), 4, new Object[]{Tags.Items.EGGS, MFItemTags.FLOUR, MFItemTags.FLOUR});
        shapelessRecipe(output, RecipeCategory.MISC, DOUGH.get(), 1, new Object[]{MFItemTags.FLOUR, Items.POTION});
        shapelessRecipe(output, RecipeCategory.MISC, Items.SLIME_BALL, 2, new Object[]{MFItemTags.DOUGH, Items.GREEN_DYE});
        shapelessRecipe(output, RecipeCategory.MISC, Items.SUGAR, 3, new Object[]{MOLASSES});
        shapelessRecipe(output, RecipeCategory.MISC, Items.GUNPOWDER, 4, new Object[]{SULFUR_CHUNK, ItemTags.COALS, ESTUS_DUST});
        shapelessRecipe(output, RecipeCategory.MISC, BENZENE.get(), 1, new Object[]{Items.GLASS_BOTTLE, Items.NETHER_WART, Items.NETHER_WART, Items.NETHER_WART, SULFUR_CHUNK});

        shapelessRecipe(output, RecipeCategory.MISC, Items.DISC_FRAGMENT_5, 3, new Object[]{Tags.Items.MUSIC_DISCS});

        shaped3x3Recipe(output, RecipeCategory.TOOLS, Items.MUSIC_DISC_11, 1, Items.DISC_FRAGMENT_5);
        overrideShaped(output, RecipeCategory.TOOLS, Items.MUSIC_DISC_5, 1, new String[]{"fff", "fef", "fff"}, Map.of('e', Items.ECHO_SHARD, 'f', Items.DISC_FRAGMENT_5));

        discRecipe(output, Items.MUSIC_DISC_13, Items.YELLOW_DYE);
        discRecipe(output, Items.MUSIC_DISC_CAT, Items.LIME_DYE);
        discRecipe(output, Items.MUSIC_DISC_BLOCKS, Items.ORANGE_DYE);
        discRecipe(output, Items.MUSIC_DISC_CHIRP, Items.RED_DYE);
        discRecipe(output, Items.MUSIC_DISC_FAR, Items.GLOW_INK_SAC);
        discRecipe(output, Items.MUSIC_DISC_MALL, Items.BLUE_DYE);
        discRecipe(output, Items.MUSIC_DISC_MELLOHI, Items.PURPLE_DYE);
        discRecipe(output, Items.MUSIC_DISC_STAL, Items.BLACK_DYE);
        discRecipe(output, Items.MUSIC_DISC_STRAD, Items.WHITE_DYE);
        discRecipe(output, Items.MUSIC_DISC_WARD, Items.GREEN_DYE);
        discRecipe(output, Items.MUSIC_DISC_WAIT, Items.LIGHT_BLUE_DYE);

        discRecipe(output, MUSIC_DISC_CRETE.get(), Items.HONEYCOMB);
        discRecipe(output, MUSIC_DISC_MALACHITE.get(), Items.WET_SPONGE);
        discRecipe(output, MUSIC_DISC_GOLDEN.get(), Items.SUNFLOWER);
        discRecipe(output, MUSIC_DISC_LABYRINTHINE.get(), Items.MOSSY_STONE_BRICKS);

        shapelessRecipe(output, RecipeCategory.MISC, Items.RED_DYE, 1, new Object[]{TOMATOES});
        shapelessRecipe(output, RecipeCategory.MISC, Items.MAGENTA_DYE, 1, new Object[]{HYACINTH});
        shapelessRecipe(output, RecipeCategory.MISC, Items.CYAN_DYE, 1, new Object[]{CYAN_ROSE});

        shapelessRecipe(output, RecipeCategory.MISC, CARBON_RICH_IRON.get(), 4, new Object[]{Blocks.COAL_BLOCK, Tags.Items.INGOTS_IRON, Tags.Items.INGOTS_IRON, Tags.Items.INGOTS_IRON, Tags.Items.INGOTS_IRON});

        shapelessRecipe(output, RecipeCategory.MISC, DIVINE_FRAGMENT.get(), 9, new Object[]{DIVINE_FAVOR});
        shaped3x3Recipe(output, RecipeCategory.MISC, DIVINE_FAVOR.get(), 1, DIVINE_FRAGMENT);
        enclosedRecipe(output, RecipeCategory.MISC, CRYSTAL_HEART.get(), 1, Items.DIAMOND, DIVINE_FRAGMENT);

        shapelessRecipe(output, RecipeCategory.TOOLS, TINDER.get(), 1, new Object[]{Items.STICK, Items.STICK, Ingredients.SHORT_GRASS});

        shapelessRecipe(output, RecipeCategory.MISC, SHORT_DRY_GRASS.get(), 2, new Object[]{TALL_DRY_GRASS});
        shapelessRecipe(output, RecipeCategory.MISC, TALL_DRY_GRASS.get(), 1, new Object[]{SHORT_DRY_GRASS, SHORT_DRY_GRASS});
        shapelessRecipe(output, RecipeCategory.MISC, TALL_DRY_GRASS.get(), 2, new Object[]{STRAW});
        shapelessRecipe(output, RecipeCategory.MISC, STRAW.get(), 1, new Object[]{TALL_DRY_GRASS, TALL_DRY_GRASS});
        shapelessRecipe(output, RecipeCategory.BUILDING_BLOCKS, Items.PACKED_MUD, 8, new Object[]{STRAW, Items.MUD, Items.MUD, Items.MUD, Items.MUD, Items.MUD, Items.MUD, Items.MUD, Items.MUD});

        shapelessRecipe(output, RecipeCategory.TOOLS, AMNESTIC.get(), 1, new Object[]{Items.PAPER, Items.AZURE_BLUET, ESTUS_DUST, Items.SUGAR});
        shapelessRecipe(output, RecipeCategory.MISC, Items.OMINOUS_BOTTLE, 1, new Object[]{BENZENE, Items.ECHO_SHARD, Items.SCULK_VEIN, Items.SCULK_VEIN});
        shapelessRecipe(output, RecipeCategory.MISC, PHOSPHOR.get(), 8, new Object[]{Items.POTION, SULFUR_CHUNK, Items.GLOW_LICHEN, Items.GLOW_LICHEN, Items.GLOW_LICHEN, Items.GLOW_LICHEN});
        shapelessRecipe(output, RecipeCategory.MISC, Items.GLOW_INK_SAC, 1, new Object[]{PHOSPHOR, Items.INK_SAC});
        shapedRecipe(output, RecipeCategory.MISC, PHOSPHOR_TORCH.get(), 8, new String[]{"c ", "s "}, Map.of('s', Items.STICK, 'c', PHOSPHOR));

        shapelessRecipe(output, RecipeCategory.MISC, BLUEPRINT.get(), 4, new Object[]{Items.PAPER, Tags.Items.GEMS_LAPIS});
        shapedRecipe(output, RecipeCategory.MISC, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1, new String[]{"nsn", "ses", "nsn"}, Map.of('s', BLUEPRINT, 'e', Ingredients.NETHERITE, 'n', Items.NETHERRACK));

        shapedRecipe(output, RecipeCategory.MISC, GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), 1, new String[]{"isi", "iti", "iii"}, Map.of('i', Tags.Items.INGOTS_IRON, 's', STEEL_ALLOY, 't', ItemTags.STONE_CRAFTING_MATERIALS));
        shapedRecipe(output, RecipeCategory.MISC, BEACON_KINDLING.get(), 1, new String[]{" o ", " c ", " s "}, Map.of('o', OBOL, 's', STRAW_BALE, 'c', Items.CAMPFIRE));
        shapedRecipe(output, RecipeCategory.MISC, SACK.get(), 1, new String[]{" s ", "ses", " s "}, Map.of('s', Tags.Items.LEATHERS, 'e', Tags.Items.CHESTS_WOODEN));
        shapedRecipe(output, RecipeCategory.TOOLS, Items.BUNDLE, 1, new String[]{"c  ", "s  "}, Map.of('s', Items.STRING, 'c', Ingredients.SMALL_LEATHERS));
        shapedRecipe(output, RecipeCategory.TOOLS, WOODEN_CROSS.get(), 1, new String[]{" s ", "ses", " s "}, Map.of('s', Items.STICK, 'e', ESTUS_DUST));
        shapedRecipe(output, RecipeCategory.TOOLS, NAZAR.get(), 1, new String[]{"ell", "lwl", "lle"}, Map.of('w', Items.QUARTZ, 'l', Tags.Items.GEMS_LAPIS, 'e', ESTUS_DUST));
        shapedRecipe(output, RecipeCategory.MISC, WARDING_STONE.get(), 1, new String[]{"cnc", "nsn", "cnc"}, Map.of('c', Items.CHISELED_STONE_BRICKS, 'n', NAZAR, 's', STABILIZED_ESTUS));

        shapedRecipe(output, RecipeCategory.TOOLS, COPPER_SHEARS.get(), 1, new String[]{" c ", "c  "}, Map.of('c', Tags.Items.INGOTS_COPPER));
        shapedRecipe(output, RecipeCategory.MISC, Items.ANVIL, 1, new String[]{"sss", " i ", "iii"}, Map.of('s', STEEL_ALLOY, 'i', Tags.Items.INGOTS_IRON));
        shapedRecipe(output, RecipeCategory.MISC, Items.TORCH, 4, new String[]{"c ", "s "}, Map.of('s', Items.STICK, 'c', SHORT_DRY_GRASS));
        shapedRecipe(output, RecipeCategory.MISC, STABILIZED_ESTUS.get(), 1, new String[]{"ebe", "eye", "eee"}, Map.of('e', ESTUS_DUST, 'b', BENZENE, 'y', Items.ENDER_PEARL));

        shapedRecipe(output, RecipeCategory.REDSTONE, Items.BELL, 1, new String[]{"ccc", "clc", "c c"}, Map.of('c', Tags.Items.INGOTS_COPPER, 'l', Items.LIGHTNING_ROD));

        overrideSmelting(output, RecipeCategory.FOOD, NAAN.get(), 0.35F, 5F, MFItemTags.DOUGH, "bread");

        overrideShapeless(output, RecipeCategory.TOOLS, Items.FLINT_AND_STEEL, 1, new Object[]{Items.FLINT, Items.FLINT});
        overrideShapeless(output, RecipeCategory.BUILDING_BLOCKS, Items.PACKED_MUD, 1, new Object[]{SHORT_DRY_GRASS, Items.DIRT});
        overrideShapeless(output, RecipeCategory.MISC, Items.BONE_MEAL, 3, new Object[]{Tags.Items.BONES});
        overrideShapeless(output, RecipeCategory.MISC, Items.SOUL_CAMPFIRE, 1, new Object[]{Items.CAMPFIRE, ItemTags.SOUL_FIRE_BASE_BLOCKS});
        overrideShapeless(output, RecipeCategory.MISC, Items.ENDER_EYE, 16, new Object[]{STABILIZED_ESTUS, DIVINE_FRAGMENT});
        overrideShapeless(output, RecipeCategory.FOOD, Items.COOKIE, 1, new Object[]{MFItemTags.FOOD_CHOCOLATE, Items.SUGAR, MFItemTags.DOUGH});
        overrideShapeless(output, RecipeCategory.FOOD, Items.PUMPKIN_PIE, 1, new Object[]{BAKED_PUMPKIN, BAKED_PUMPKIN, Items.SUGAR, Tags.Items.DRINKS_MILK, MFItemTags.DOUGH});

        overrideShaped(output, RecipeCategory.MISC, Items.SMITHING_TABLE, 1, new String[]{"ss ", "pp ", "pp "}, Map.of('s', STEEL_ALLOY, 'p', ItemTags.PLANKS));
        overrideShaped(output, RecipeCategory.TOOLS, Items.STONE_SWORD, 1, new String[]{" c ", " c ", " s "}, Map.of('c', Tags.Items.INGOTS_COPPER, 's', Items.STICK));
        overrideShaped(output, RecipeCategory.TOOLS, Items.STONE_SHOVEL, 1, new String[]{" c ", " s ", " s "}, Map.of('c', Tags.Items.INGOTS_COPPER, 's', Items.STICK));
        overrideShaped(output, RecipeCategory.TOOLS, Items.STONE_PICKAXE, 1, new String[]{"ccc", " s ", " s "}, Map.of('c', Tags.Items.INGOTS_COPPER, 's', Items.STICK));
        overrideShaped(output, RecipeCategory.TOOLS, Items.STONE_HOE, 1, new String[]{"cc ", " s ", " s "}, Map.of('c', Tags.Items.INGOTS_COPPER, 's', Items.STICK));
        overrideShaped(output, RecipeCategory.TOOLS, Items.STONE_AXE, 1, new String[]{"cc ", "cs ", " s "}, Map.of('c', Tags.Items.INGOTS_COPPER, 's', Items.STICK));
        overrideShaped(output, RecipeCategory.FOOD, Items.CAKE, 1, new String[]{"bmb", "ses", "fff"}, Map.of('b', IPItemTags.SWEET_BERRY, 's', Items.SUGAR, 'e', Tags.Items.EGGS, 'm', Tags.Items.DRINKS_MILK,'f', MFItemTags.FLOUR));
        overrideShaped(output, RecipeCategory.MISC, Items.LODESTONE, 1, new String[]{"sss", "sis", "sss"}, Map.of('s', Items.CHISELED_STONE_BRICKS, 'i', Tags.Items.INGOTS_IRON));
        overrideShaped(output, RecipeCategory.TRANSPORTATION, Items.SADDLE, 1, new String[]{" s ", "sis"}, Map.of('s', Tags.Items.LEATHERS, 'i', Tags.Items.INGOTS_IRON));
        overrideShaped(output, RecipeCategory.DECORATIONS, Items.ITEM_FRAME, 1, new String[]{" s ", "sls", " s "}, Map.of('s', Items.STICK, 'l', Ingredients.SMALL_LEATHERS));
        overrideShaped(output, RecipeCategory.MISC, Items.BOWL, 3, new String[]{"b b", "bbb"}, Map.of('b', Items.BRICK));
        overrideShaped(output, RecipeCategory.MISC, Items.LEATHER, 1, new String[]{"ss ", "ss "}, Map.of('s', Ingredients.SMALL_LEATHERS));
        overrideShaped(output, RecipeCategory.MISC, Items.TORCH, 8, new String[]{"c ", "s "}, Map.of('s', Items.STICK, 'c', ItemTags.COALS));
        overrideShaped(output, RecipeCategory.MISC, Items.CAMPFIRE, 1, new String[]{"ss ", "ll "}, Map.of('s', Items.STICK, 'l', ItemTags.LOGS));
        overrideShaped(output, RecipeCategory.MISC, Items.FURNACE, 1, new String[]{"m m", "p p", "mcm"}, Map.of('m', Items.MUD_BRICKS, 'p', Items.PACKED_MUD, 'c', Items.CAMPFIRE));
        overrideShaped(output, RecipeCategory.MISC, Items.SMOKER, 1, new String[]{"iii", "b b", "bcb"}, Map.of('i', Tags.Items.INGOTS_IRON, 'b', Items.BRICKS, 'c', Items.CAMPFIRE));
        overrideShaped(output, RecipeCategory.MISC, Items.BLAST_FURNACE, 1, new String[]{"p p", "p p", "scs"}, Map.of('p', Items.POLISHED_DEEPSLATE, 's', Items.SMOOTH_STONE, 'c', Items.CAMPFIRE));
    }

    public void discRecipe(RecipeOutput output, ItemLike result, Object ingredient) {
        enclosedRecipe(output, RecipeCategory.TOOLS, result, 1, Items.DISC_FRAGMENT_5, ingredient);
    }

    public void cookingRecipe(RecipeOutput output, ItemLike result, float seconds, Object ingredient) {
        smokingRecipe(output, RecipeCategory.FOOD, result, 0.35F, seconds, ingredient);
        campfireCookingRecipe(output, RecipeCategory.FOOD, result, 0.35F, seconds * 3, ingredient);
    }

    public void cookingRecipe(RecipeOutput output, ItemLike result, Object ingredient) {
        cookingRecipe(output, result, 5F, ingredient);
    }

    public void secretRecipe(RecipeOutput output, RecipeCategory category, ItemLike result, int count, Object[] objects, String... modIds) {
        regRecipe(output, ShapelessRecipeBuilder.shapeless(category, result, count), result, b -> {
            for (Object object : objects) {
                b.requires(toIngredient(object));
                b.group(SecretRecipeItem.GROUP);
            }
        }, modIds, "shapeless/secret", objects);
    }

    public void extraToolsRecipe(RecipeOutput output, ItemLike ingredient, String... modIds) {
        String baseName = RegUtil.path(ingredient).replace("_alloy", "").replace("_ingot", "");
        Item dolabra = CompatUtil.item(modId, baseName + "_dolabra");
        Item mattock = CompatUtil.item(modId, baseName + "_mattock");
        if (dolabra != null) shapedRecipe(output, RecipeCategory.COMBAT, dolabra, 1, new String[]{"###", " s#", " s "}, Map.of('#', ingredient, 's', Items.STICK), modIds);
        if (mattock != null) shapedRecipe(output, RecipeCategory.COMBAT, mattock, 1, new String[]{"## ", " s#", " s "}, Map.of('#', ingredient, 's', Items.STICK), modIds);
    }



    static class Ingredients {
        public static final Ingredient SHORT_GRASS = Ingredient.of(Items.SHORT_GRASS, Items.FERN);
        public static final Ingredient OIL_INGREDIENTS = Ingredient.of(Items.SUNFLOWER, Items.COCOA_BEANS);
        public static final Ingredient COOKED_FISH_INGREDIENTS = Ingredient.of(Items.TROPICAL_FISH, ALASKA_BLACKFISH.get(), ANCHOVY.get(), BLUEGILL.get(), BUJURQUI.get(), CRAPPIE.get(), FLYING_FISH.get(), GUPPY.get(), GURNARD.get(), HERRING.get(), HUMPBACK_WHITEFISH.get(), LAMPREY.get(), MEDITERRANEAN_KILLIFISH.get(), CHARRED_FISH.get(), PIRANHA.get(), RAINBOW_WRASSE.get(), SHAD.get(), SPOONHEAD_SCULPIN.get(), STRIPED_PERCH.get());
        public static final Ingredient COOKED_LARGE_FISH_INGREDIENTS = Ingredient.of(ARAPAIMA.get(), ARMOURED_CATFISH.get(), BASS.get(), BLACK_SEABASS.get(), CARP.get(), CATFISH.get(), ECHO_EEL.get(), EUROPEAN_EEL.get(), FLOUNDER.get(), GAR.get(), MAHI_MAHI.get(), MONKFISH.get(), MUSKELLUNGE.get(), NORTHERN_PIKE.get(), OARFISH.get(), OPAH.get(), PAINTED_MORAY.get(), SIBERIAN_STURGEON.get(), SKATE.get(), STURGEON.get(), BLUE_MARLIN.get(), TUNISIAN_BARB.get(), WALLEYE.get(), WOLFFISH.get());
        public static final Ingredient SMALL_LEATHERS = Ingredient.of(Items.RABBIT_HIDE, FOX_PELT.get(), TATTERED_LEATHER.get());
        public static final Ingredient DIAMOND = Ingredient.of(RAW_DIAMOND.get(), Items.DIAMOND_AXE, Items.DIAMOND_BOOTS, Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_HOE, Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND_LEGGINGS, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_SWORD);
        public static final Ingredient COPPER = Ingredient.of(Items.RAW_COPPER, Items.STONE_AXE, Items.STONE_HOE, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_SWORD);
        public static final Ingredient IRON = Ingredient.of(Items.RAW_IRON, Items.IRON_AXE, Items.IRON_BOOTS, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_HOE, Items.IRON_HORSE_ARMOR, Items.IRON_LEGGINGS, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD);
        public static final Ingredient SILVER = Ingredient.of(RAW_SILVER.get(), SILVER_SWORD.get());
        public static final Ingredient GOLD = Ingredient.of(Items.RAW_GOLD, Items.GOLDEN_AXE, Items.GOLDEN_BOOTS, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_HOE, Items.GOLDEN_HORSE_ARMOR, Items.GOLDEN_LEGGINGS, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_SWORD);
        public static final Ingredient NETHERITE = Ingredient.of(Items.NETHERITE_AXE, Items.NETHERITE_BOOTS, Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_HOE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_SWORD);
    }

}
