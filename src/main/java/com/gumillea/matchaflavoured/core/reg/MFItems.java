package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.inlandport.common.item.FoodItem;
import com.gumillea.inlandport.core.api.record.RegConditions;
import com.gumillea.inlandport.core.util.helpers.reg.ItemHelper;
import com.gumillea.inlandport.test.reg.IPAttributes;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.MatchaFlavouredConfig;
import com.gumillea.matchaflavoured.common.item.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.gumillea.matchaflavoured.core.reg.MFTiers.*;

public class MFItems {
    public static final ItemHelper HELPER = new ItemHelper(MatchaFlavoured.MODID);

    //fishing
    public static final DeferredHolder<Item, Item> HUMPBACK_WHITEFISH = regFood("humpback_whitefish", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> ALASKA_BLACKFISH = regFood("alaska_blackfish", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> SPOONHEAD_SCULPIN = regFood("spoonhead_sculpin", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> NORTHERN_PIKE = regFood("northern_pike", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> SIBERIAN_STURGEON = regFood("siberian_sturgeon", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> STRIPED_PERCH = regFood("striped_perch", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> CRAPPIE = regFood("crappie", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> WALLEYE = regFood("walleye", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> GAR = regFood("gar", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> MUSKELLUNGE = regFood("muskellunge", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> BLUEGILL = regFood("bluegill", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> CARP = regFood("carp", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> CATFISH = regFood("catfish", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> BASS = regFood("bass", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> MEDITERRANEAN_KILLIFISH = regFood("mediterranean_killifish", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> FRESHWATER_PUFFERFISH = regFood("freshwater_pufferfish", new Item.Properties().food(Foods.PUFFERFISH), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> LAMPREY = regFood("lamprey", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> TUNISIAN_BARB = regFood("tunisian_barb", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> EUROPEAN_EEL = regFood("european_eel", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> GUPPY = regFood("guppy", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> BUJURQUI = regFood("bujurqui", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> PIRANHA = regFood("piranha", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> ARMOURED_CATFISH = regFood("armoured_catfish", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> ARAPAIMA = regFood("arapaima", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> HERRING = regFood("herring", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> WOLFFISH = regFood("wolffish", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> SKATE = regFood("skate", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> BLACK_SEABASS = regFood("black_seabass", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> MONKFISH = regFood("monkfish", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> OPAH = regFood("opah", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> ANCHOVY = regFood("anchovy", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> SHAD = regFood("shad", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> FLYING_FISH = regFood("flying_fish", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> STURGEON = regFood("sturgeon", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> BLUE_MARLIN = regFood("blue_marlin", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> RAINBOW_WRASSE = regFood("rainbow_wrasse", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> GURNARD = regFood("gurnard", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> FLOUNDER = regFood("flounder", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> MAHI_MAHI = regFood("mahi_mahi", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> PAINTED_MORAY = regFood("painted_moray", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> OARFISH = regFood("oarfish", new Item.Properties().food(Foods.SALMON), 2, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> CREAKING_FISH = regFood("creaking_fish", new Item.Properties().food(Foods.COD), 1, FoodItem.Type.RAW_FISH);
    public static final DeferredHolder<Item, Item> ECHO_EEL = regFood("echo_eel", new Item.Properties().food(Foods.COD), 2, FoodItem.Type.RAW_FISH);

    public static final DeferredHolder<Item, Item> TADPOLE = HELPER.reg("tadpole", () -> new SpawnEggLikeItem(EntityType.TADPOLE, new Item.Properties()));
    public static final DeferredHolder<Item, Item> FROG = HELPER.reg("frog", () -> new SpawnEggLikeItem(EntityType.FROG, new Item.Properties()));
    public static final DeferredHolder<Item, Item> AXOLOTL = HELPER.reg("axolotl", () -> new SpawnEggLikeItem(EntityType.AXOLOTL, new Item.Properties()));

    //cooking
    public static final DeferredHolder<Item, Item> BAKED_APPLE = regFood("baked_apple", new Item.Properties().food(MFFoods.BAKED_APPLE), 4, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> BAKED_GOLDEN_APPLE = regFood("baked_golden_apple", new Item.Properties().food(MFFoods.BAKED_GOLDEN_APPLE), 8, FoodItem.Type.MAGIC);
    public static final DeferredHolder<Item, Item> GRILLED_MELON = regFood("grilled_melon", new Item.Properties().food(MFFoods.GRILLED_MELON), 2, FoodItem.Type.MISC, MFItemCond.TOMATOES);
    public static final DeferredHolder<Item, Item> BAKED_PUMPKIN = regFood("baked_pumpkin", new Item.Properties().food(MFFoods.BAKED_PUMPKIN), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> SWEET_BERRY_MASH = regFood("sweet_berry_mash", new Item.Properties().food(MFFoods.SWEET_BERRY_MASH), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> GLOW_BERRY_MASH = regFood("glow_berry_mash", new Item.Properties().food(MFFoods.GLOW_BERRY_MASH), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> STEAMED_CARROTS = regFood("steamed_carrots", new Item.Properties().food(MFFoods.STEAMED_CARROTS), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> STEAMED_GOLDEN_CARROTS = regFood("steamed_golden_carrots", new Item.Properties().food(MFFoods.STEAMED_GOLDEN_CARROTS), 2, FoodItem.Type.MAGIC);
    public static final DeferredHolder<Item, Item> NOPALES = regFood("nopales", new Item.Properties().food(MFFoods.NOPALES), 4, FoodItem.Type.MISC);

    public static final DeferredHolder<Item, Item> TOMATOES = regFood("tomatoes", new Item.Properties().food(MFFoods.TOMATOES), 2, FoodItem.Type.VEGETABLE);
    public static final DeferredHolder<Item, Item> TOMATO_SEEDS = HELPER.reg("tomato_seeds", () -> new ItemNameBlockItem(MFBlocks.TOMATOES_CROP.get(), new Item.Properties()), MFItemCond.TOMATOES);
    public static final DeferredHolder<Item, Item> GRILLED_TOMATOES = regFood("grilled_tomatoes", new Item.Properties().food(MFFoods.GRILLED_TOMATOES), 4, FoodItem.Type.MISC, MFItemCond.TOMATOES);

    public static final DeferredHolder<Item, Item> BRAISED_BROWN_MUSHROOM = regFood("braised_brown_mushroom", new Item.Properties().food(MFFoods.BRAISED_MUSHROOM), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> BRAISED_RED_MUSHROOM = regFood("braised_red_mushroom", new Item.Properties().food(MFFoods.BRAISED_RED_MUSHROOM), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> BRAISED_CRIMSON_FUNGUS = regFood("braised_crimson_fungus", new Item.Properties().food(MFFoods.BRAISED_CRIMSON_FUNGUS), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> BRAISED_WARPED_FUNGUS = regFood("braised_warped_fungus", new Item.Properties().food(MFFoods.BRAISED_WARPED_FUNGUS), 2, FoodItem.Type.MISC);

    public static final DeferredHolder<Item, Item> FRIED_EGG = regFood("fried_egg", new Item.Properties().food(MFFoods.FRIED_EGG), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> COOKED_FISH = regFood("cooked_fish", new Item.Properties().food(MFFoods.COOKED_FISH), 4, FoodItem.Type.COOKED_FISH);
    public static final DeferredHolder<Item, Item> COOKED_LARGE_FISH = regFood("cooked_large_fish", new Item.Properties().food(MFFoods.COOKED_LARGE_FISH), 5, FoodItem.Type.COOKED_FISH);
    public static final DeferredHolder<Item, Item> CHARRED_FISH = regFood("charred_fish", new Item.Properties().food(MFFoods.CHARRED_FISH), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> CHARRED_MEAT = regFood("charred_meat", new Item.Properties().food(MFFoods.CHARRED_MEAT), 2, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> CHARRED_POTATO = regFood("charred_potato", new Item.Properties().food(MFFoods.CHARRED_POTATO), 1, FoodItem.Type.MISC);

    public static final DeferredHolder<Item, Item> CHOCOLATE = regFood("chocolate", new Item.Properties().food(MFFoods.CHOCOLATE), 2, FoodItem.Type.SNACK);
    public static final DeferredHolder<Item, Item> CHEESE = regFood("cheese", new Item.Properties().food(MFFoods.CHEESE), 3, FoodItem.Type.MISC);

    public static final DeferredHolder<Item, Item> NAAN = regFood("naan", new Item.Properties().food(MFFoods.NAAN), 4, FoodItem.Type.MISC);
    public static final DeferredHolder<Item, Item> BROWNIE = regFood("brownie", new Item.Properties().food(MFFoods.BROWNIE), 6, FoodItem.Type.DESSERT);

    public static final DeferredHolder<Item, Item> FRENCH_TOAST = regFood("french_toast", new Item.Properties().food(MFFoods.FRENCH_TOAST), 10, FoodItem.Type.SNACK);
    public static final DeferredHolder<Item, Item> HONIED_FRENCH_TOAST = regFood("honied_french_toast", new Item.Properties().food(MFFoods.HONIED_FRENCH_TOAST), 10, FoodItem.Type.SNACK);
    public static final DeferredHolder<Item, Item> SWEET_BERRY_TOAST = regSecretFood("sweet_berry_toast", new Item.Properties().food(MFFoods.SWEET_BERRY_TOAST), 10, FoodItem.Type.SNACK, new RegConditions(Items.SWEET_BERRIES));

    public static final DeferredHolder<Item, Item> PUPUSA = regFood("pupusa", new Item.Properties().food(MFFoods.PUPUSA), 3, FoodItem.Type.SNACK);
    public static final DeferredHolder<Item, Item> BUNUELO = regFood("bunuelo", new Item.Properties().food(MFFoods.BUNUELO), 6, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> LATKE = regFood("latke", new Item.Properties().food(MFFoods.LATKE), 12, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> FISH_AND_CHIPS = regFood("fish_and_chips", new Item.Properties().food(MFFoods.FISH_AND_CHIPS), 14, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> LUMPIA = regFood("lumpia", new Item.Properties().food(MFFoods.LUMPIA), 6, FoodItem.Type.SNACK);
    public static final DeferredHolder<Item, Item> GIMMARI = regFood("gimmari", new Item.Properties().food(MFFoods.GIMMARI), 6, FoodItem.Type.SNACK);

    public static final DeferredHolder<Item, Item> BRUSCHETTA = regFood("bruschetta", new Item.Properties().food(MFFoods.BRUSCHETTA), 8, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> CHEESE_PIZZA = regFood("cheese_pizza", new Item.Properties().food(MFFoods.CHEESE_PIZZA), 9, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> MEAT_PIZZA = regFood("meat_pizza", new Item.Properties().food(MFFoods.MEAT_PIZZA), 10, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> MUSHROOM_PIZZA = regFood("mushroom_pizza", new Item.Properties().food(MFFoods.MUSHROOM_PIZZA), 8, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> WARPED_PIZZA = regFood("warped_pizza", new Item.Properties().food(MFFoods.WARPED_PIZZA), 8, FoodItem.Type.MEAL);

    public static final DeferredHolder<Item, Item> PUMPKIN_EMPANADA = regFood("pumpkin_empanada", new Item.Properties().food(MFFoods.PUMPKIN_EMPANADA), 8, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> APPLE_EMPANADA = regFood("apple_empanada", new Item.Properties().food(MFFoods.APPLE_EMPANADA), 8, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> GOLDEN_APPLE_EMPANADA = regFood("golden_apple_empanada", new Item.Properties().food(MFFoods.GOLDEN_APPLE_EMPANADA).rarity(Rarity.UNCOMMON), 8, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> SWEET_BERRY_DANISH = regFood("sweet_berry_danish", new Item.Properties().food(MFFoods.SWEET_BERRY_DANISH), 8, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> GLOW_BERRY_CRUMBLE = regFood("glow_berry_crumble", new Item.Properties().food(MFFoods.GLOW_BERRY_CRUMBLE), 8, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> CARROT_CUPCAKE = regFood("carrot_cupcake", new Item.Properties().food(MFFoods.CARROT_CUPCAKE), 8, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> GOLDEN_CARROT_CUPCAKE = regFood("golden_carrot_cupcake", new Item.Properties().food(MFFoods.GOLDEN_CARROT_CUPCAKE).rarity(Rarity.UNCOMMON), 8, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> CHORUS_MOCHI = regSecretFood("chorus_mochi", new Item.Properties().food(MFFoods.CHORUS_MOCHI), 4, FoodItem.Type.DESSERT, new RegConditions(Items.POPPED_CHORUS_FRUIT));

    public static final DeferredHolder<Item, Item> ICE_CREAM = regFood("ice_cream", new Item.Properties().food(MFFoods.ICE_CREAM), 8, FoodItem.Type.DESSERT);
    public static final DeferredHolder<Item, Item> PUERQUITO = regFood("puerquito", new Item.Properties().food(MFFoods.PUERQUITO), 2, FoodItem.Type.MISC);

    public static final DeferredHolder<Item, Item> CURRY_STOCK = HELPER.reg("curry_stock", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> CURRY = regBowlFood("curry", new Item.Properties().food(MFFoods.CURRY), 20, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> PANEER_MAKHANI_STOCK = HELPER.reg("paneer_makhani_stock", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> PANEER_MAKHANI = regBowlFood("paneer_makhani", new Item.Properties().food(MFFoods.PANEER_MAKHANI), 20, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> PUMPKIN_CURRY_STOCK = HELPER.reg("pumpkin_curry_stock", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> PUMPKIN_CURRY = regBowlFood("pumpkin_curry", new Item.Properties().food(MFFoods.PUMPKIN_CURRY), 20, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> GREEN_CURRY_STOCK = HELPER.reg("green_curry_stock", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> GREEN_CURRY = regBowlFood("green_curry", new Item.Properties().food(MFFoods.GREEN_CURRY), 20, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> RAMEN_STOCK = HELPER.reg("ramen_stock", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> RAMEN = regBowlFood("ramen", new Item.Properties().food(MFFoods.RAMEN), 20, FoodItem.Type.MEAL);

    public static final DeferredHolder<Item, Item> APPLE_CINNAMON_OATMEAL = regBowlFood("apple_cinnamon_oatmeal", new Item.Properties().food(MFFoods.APPLE_CINNAMON_OATMEAL), 10, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> BOKGUK = regBowlFood("bokguk", new Item.Properties().food(MFFoods.BOKGUK), 12, FoodItem.Type.SOUP);
    public static final DeferredHolder<Item, Item> STROGANOFF = regBowlFood("stroganoff", new Item.Properties().food(MFFoods.STROGANOFF), 16, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> RED_MUSHROOM_STROGANOFF = regBowlFood("red_mushroom_stroganoff", new Item.Properties().food(MFFoods.RED_MUSHROOM_STROGANOFF), 12, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> CRIMSON_FUNGUS_STROGANOFF = regBowlFood("crimson_fungus_stroganoff", new Item.Properties().food(MFFoods.CRIMSON_FUNGUS_STROGANOFF), 12, FoodItem.Type.MEAL);
    public static final DeferredHolder<Item, Item> WARPED_FUNGUS_STROGANOFF = regSecretFood("warped_fungus_stroganoff", new Item.Properties().food(MFFoods.WARPED_FUNGUS_STROGANOFF).craftRemainder(Items.BOWL), 12, FoodItem.Type.MEAL, new RegConditions(Items.WARPED_FUNGUS));
    public static final DeferredHolder<Item, Item> GNOCCHI = regSecretFood("gnocchi", new Item.Properties().food(MFFoods.GNOCCHI), 16, FoodItem.Type.MEAL, new RegConditions(Items.POTATO));
    public static final DeferredHolder<Item, Item> KONTOMIRE_STEW = regBowlFood("kontomire_stew", new Item.Properties().food(MFFoods.KONTOMIRE_STEW), 12, FoodItem.Type.SOUP);
    public static final DeferredHolder<Item, Item> PAD_THAI = regBowlFood("pad_thai", new Item.Properties().food(MFFoods.PAD_THAI), 10, FoodItem.Type.MEAL);

    public static final DeferredHolder<Item, Item> HONEY_GINGER_TEA = regDrink("honey_ginger_tea", new Item.Properties().food(MFFoods.HONEY_GINGER_TEA), 2);
    public static final DeferredHolder<Item, Item> MEAD = regDrink("mead", new Item.Properties().food(MFFoods.MEAD), 6);
    public static final DeferredHolder<Item, Item> BUBBLING_MEAD = regDrink("bubbling_mead", new Item.Properties().food(MFFoods.BUBBLING_MEAD), 0);

    public static final DeferredHolder<Item, Item> GLOW_BERRY_JAM = regSyrup("glow_berry_jam", new Item.Properties().food(MFFoods.GLOW_BERRY_JAM), 4);
    public static final DeferredHolder<Item, Item> SWEET_BERRY_JAM = regSyrup("sweet_berry_jam", new Item.Properties().food(MFFoods.SWEET_BERRY_JAM), 4);
    public static final DeferredHolder<Item, Item> PUMPKIN_JAM = regSyrup("pumpkin_jam", new Item.Properties().food(MFFoods.PUMPKIN_JAM), 4);
    public static final DeferredHolder<Item, Item> RIND_JAM = regSyrup("rind_jam", new Item.Properties().food(MFFoods.RIND_JAM), 4);

    public static final DeferredHolder<Item, Item> MELON_SORBET = regContainerFood("melon_sorbet", new Item.Properties().food(MFFoods.MELON_SORBET).craftRemainder(Items.GLASS_BOTTLE), SoundEvents.GENERIC_EAT, 4, FoodItem.Type.SNACK, null);

    public static final DeferredHolder<Item, Item> CANNED_APPLES = regCannedFood("canned_apples", new Item.Properties().food(MFFoods.CANNED_APPLES), 6);
    public static final DeferredHolder<Item, Item> CANNED_GOLDEN_APPLES = regCannedFood("canned_golden_apples", new Item.Properties().food(MFFoods.CANNED_GOLDEN_APPLES), 8);
    public static final DeferredHolder<Item, Item> PICKLED_CARROTS = regCannedFood("pickled_carrots", new Item.Properties().food(MFFoods.PICKLED_CARROTS), 4);
    public static final DeferredHolder<Item, Item> PICKLED_GOLDEN_CARROTS = regCannedFood("pickled_golden_carrots", new Item.Properties().food(MFFoods.PICKLED_GOLDEN_CARROTS), 4);
    public static final DeferredHolder<Item, Item> PICKLED_POTATOES = regCannedFood("pickled_potatoes", new Item.Properties().food(MFFoods.PICKLED_POTATOES), 10);
    public static final DeferredHolder<Item, Item> PICKLED_TOMATOES = regCannedFood("pickled_tomatoes", new Item.Properties().food(MFFoods.PICKLED_TOMATOES), 6);
    public static final DeferredHolder<Item, Item> PICKLED_BROWN_MUSHROOMS = regCannedFood("pickled_brown_mushrooms", new Item.Properties().food(MFFoods.PICKLED_BROWN_MUSHROOMS), 6);
    public static final DeferredHolder<Item, Item> PICKLED_RED_MUSHROOMS = regCannedFood("pickled_red_mushrooms", new Item.Properties().food(MFFoods.PICKLED_RED_MUSHROOMS), 3);
    public static final DeferredHolder<Item, Item> PICKLED_CRIMSON_FUNGUS = regCannedFood("pickled_crimson_fungus", new Item.Properties().food(MFFoods.PICKLED_CRIMSON_FUNGUS), 3);
    public static final DeferredHolder<Item, Item> PICKLED_WARPED_FUNGUS = regCannedFood("pickled_warped_fungus", new Item.Properties().food(MFFoods.PICKLED_WARPED_FUNGUS), 3);

    public static final DeferredHolder<Item, Item> FLOUR = HELPER.reg("flour", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FLOUR_BAG = HELPER.reg("flour_bag", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> DOUGH = HELPER.reg("dough", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> SEITAN = HELPER.reg("seitan", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> OIL = HELPER.reg("oil", () -> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
    public static final DeferredHolder<Item, Item> MOLASSES = HELPER.reg("molasses", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> MILK_BOTTLE = HELPER.regDrink("milk_bottle", new Item.Properties().food(MFFoods.MILK_BOTTLE));

    //ing
    public static final DeferredHolder<Item, Item> SHORT_DRY_GRASS = HELPER.reg("short_dry_grass", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TALL_DRY_GRASS = HELPER.reg("tall_dry_grass", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STRAW = HELPER.reg("straw", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> FISH_BONES = HELPER.reg("fish_bones", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> FOX_PELT = HELPER.reg("fox_pelt", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> TATTERED_LEATHER = HELPER.reg("tattered_leather", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> SULFUR_CHUNK = HELPER.reg("sulfur_chunk", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> PHOSPHOR = HELPER.reg("phosphor", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> BENZENE = HELPER.reg("benzene", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> RAW_DIAMOND = HELPER.reg("raw_diamond", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> RAW_SILVER = HELPER.reg("raw_silver", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> SILVER_INGOT = HELPER.reg("silver_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CARBON_RICH_IRON = HELPER.reg("carbon_rich_iron", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STEEL_ALLOY = HELPER.reg("steel_alloy", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> HEPATIZON_ALLOY = HELPER.reg("hepatizon_alloy", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ELECTRUM_ALLOY = HELPER.reg("electrum_alloy", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> SHAKUDO_ALLOY = HELPER.reg("shakudo_alloy", () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> AMBER = HELPER.reg("amber", () -> new Item(new Item.Properties().rarity(MFRarities.AMBER.getValue())));
    public static final DeferredHolder<Item, Item> OPAL = HELPER.reg("opal", () -> new Item(new Item.Properties().rarity(MFRarities.OPAL.getValue())));
    public static final DeferredHolder<Item, Item> RUBY = HELPER.reg("ruby", () -> new Item(new Item.Properties().rarity(MFRarities.RUBY.getValue())));
    public static final DeferredHolder<Item, Item> TOPAZ = HELPER.reg("topaz", () -> new Item(new Item.Properties().rarity(MFRarities.TOPAZ.getValue())));

    public static final DeferredHolder<Item, Item> RAW_ESTUS = HELPER.reg("raw_estus", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ESTUS_DUST = HELPER.reg("estus_dust", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> STABILIZED_ESTUS = HELPER.reg("stabilized_estus", () -> new Item(new Item.Properties().rarity(Rarity.RARE).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredHolder<Item, Item> DIVINE_FRAGMENT = HELPER.reg("divine_fragment", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> DIVINE_FAVOR = HELPER.reg("divine_favor", () -> new Item(new Item.Properties().rarity(Rarity.RARE).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredHolder<Item, Item> CRYSTAL_HEART = HELPER.reg("crystal_heart", () -> new CrystalHeartItem(new Item.Properties().rarity(Rarity.RARE).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredHolder<Item, Item> BLUEPRINT = HELPER.reg("blueprint", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CHORUS_MOCHI_RECIPE = HELPER.reg("chorus_mochi_recipe", () -> new SecretRecipeItem(new Item.Properties(), CHORUS_MOCHI, SecretRecipeItem.CHORUS_MOCHI));
    public static final DeferredHolder<Item, Item> SWEET_BERRY_TOAST_RECIPE = HELPER.reg("sweet_berry_toast_recipe", () -> new SecretRecipeItem(new Item.Properties(), SWEET_BERRY_TOAST, SecretRecipeItem.SWEET_BERRY_TOAST));
    public static final DeferredHolder<Item, Item> GNOCCHI_RECIPE = HELPER.reg("gnocchi_recipe", () -> new SecretRecipeItem(new Item.Properties(), GNOCCHI, SecretRecipeItem.GNOCCHI));
    public static final DeferredHolder<Item, Item> WARPED_FUNGUS_STROGANOFF_RECIPE = HELPER.reg("warped_fungus_stroganoff_recipe", () -> new SecretRecipeItem(new Item.Properties(), WARPED_FUNGUS_STROGANOFF, SecretRecipeItem.WARPED_FUNGUS_STROGANOFF));

    //tool
    public static final DeferredHolder<Item, Item> COPPER_SHEARS = HELPER.reg("copper_shears", () -> new ShearsItem(new Item.Properties().durability(119)));
    public static final DeferredHolder<Item, Item> STEEL_SHEARS = HELPER.reg("steel_shears", () -> new ShearsItem(new Item.Properties().durability(899)));

    public static final DeferredHolder<Item, Item> COPPER_COMPASS = HELPER.reg("copper_compass", () -> new CompassItem(new Item.Properties()));
    public static final DeferredHolder<Item, Item> GOLDEN_COMPASS = HELPER.reg("golden_compass", () -> new CompassItem(new Item.Properties()));

    public static final DeferredHolder<Item, Item> TINDER = HELPER.reg("tinder", () -> new FlintAndSteelItem(new Item.Properties().durability(1).setNoRepair()));

    public static final DeferredHolder<Item, Item> AMNESTIC = HELPER.reg("amnestic", () -> new Item(new Item.Properties()));

    //combat
    public static final DeferredHolder<Item, Item> WOODEN_CROSS = HELPER.reg("wooden_cross", () -> new WardingPowerItem(new Item.Properties(), 1));
    public static final DeferredHolder<Item, Item> NAZAR = HELPER.reg("nazar", () -> new WardingPowerItem(new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true), 2));

    public static final DeferredHolder<Item, Item> LAPIS_EARRINGS = HELPER.reg("lapis_earrings", () -> new EarringItem(new Item.Properties().attributes(EarringItem.createAttributes(IPAttributes.MANA_INFUSION,1)), null));
    public static final DeferredHolder<Item, Item> AMETHYST_EARRINGS = HELPER.reg("amethyst_earrings", () -> new EarringItem(new Item.Properties().attributes(EarringItem.createAttributes(IPAttributes.MANA_RESISTANCE,0.2D)), null));
    public static final DeferredHolder<Item, Item> EMERALD_EARRINGS = HELPER.reg("emerald_earrings", () -> new EarringItem(new Item.Properties().attributes(EarringItem.createAttributes(Attributes.LUCK,1)), null));
    public static final DeferredHolder<Item, Item> DIAMOND_EARRINGS = HELPER.reg("diamond_earrings", () -> new EarringItem(new Item.Properties().attributes(EarringItem.createAttributes(Attributes.ARMOR,2)), null));

   public static final DeferredHolder<Item, Item> AMBER_EARRINGS = HELPER.reg("amber_earrings", () -> new EarringItem(new Item.Properties().rarity(MFRarities.AMBER.getValue()), MobEffects.REGENERATION));
    public static final DeferredHolder<Item, Item> OPAL_EARRINGS = HELPER.reg("opal_earrings", () -> new EarringItem(new Item.Properties().rarity(MFRarities.OPAL.getValue()), MobEffects.DOLPHINS_GRACE));
    public static final DeferredHolder<Item, Item> TOPAZ_EARRINGS = HELPER.reg("topaz_earrings", () -> new EarringItem(new Item.Properties().rarity(MFRarities.TOPAZ.getValue()), MobEffects.DIG_SPEED));

    public static final DeferredHolder<Item, Item> NAZAR_CIRCLET = HELPER.reg("nazar_circlet", () -> new EarringItem(new Item.Properties().attributes(EarringItem.createAttributes(MFAttributes.WARDING_POWER,1)), null));
    public static final DeferredHolder<Item, Item> RUBY_CIRCLET = HELPER.reg("ruby_circlet", () -> new EarringItem(new Item.Properties().rarity(MFRarities.RUBY.getValue()), MobEffects.FIRE_RESISTANCE));
    public static final DeferredHolder<Item, Item> HEPATIZON_LAUREL = HELPER.reg("hepatizon_laurel", () -> new EarringItem(new Item.Properties().attributes(EarringItem.createAttributes(Attributes.MOVEMENT_SPEED,0.1)), null));

    public static final DeferredHolder<Item, Item> WARDING_SWORD = HELPER.reg("warding_sword", () -> new WardingSwordItem(MFTiers.NAZAR, new Item.Properties().attributes(WardingSwordItem.createAttributes(MFTiers.NAZAR, 3, -2.4F, 1))));
    public static final DeferredHolder<Item, Item> SILVER_SWORD = HELPER.reg("silver_sword", () -> new WardingSwordItem(MFTiers.SILVER, new Item.Properties().attributes(WardingSwordItem.createAttributes(MFTiers.SILVER, 3, -2.4F, 2))));
    public static final DeferredHolder<Item, Item> WARDING_SHIELD = HELPER.reg("warding_shield", () -> new WardingShieldItem(new Item.Properties().rarity(Rarity.UNCOMMON).attributes(WardingPowerItem.createAttributes(1)).durability(504)));

    public static final DeferredHolder<Item, Item> COPPER_ARMORS = HELPER.regArmors("copper", new Item.Properties(), COPPER_ARMOR, new int[] {121, 176, 165, 143}, MFItemCond.COPPER);

    public static final DeferredHolder<Item, Item> STEEL_SWORD = HELPER.regSwordItem("steel_sword", new Item.Properties(), MFTiers.STEEL, 3, -2.4F, MFItemCond.STEEL);
    public static final DeferredHolder<Item, Item> STEEL_AXE = HELPER.regAxeItem("steel_axe", new Item.Properties(), MFTiers.STEEL, 6, -3.1F, MFItemCond.STEEL);
    public static final DeferredHolder<Item, Item> STEEL_PICKAXE = HELPER.regPickaxeItem("steel_pickaxe", new Item.Properties(), MFTiers.STEEL, 1, -2.8F, MFItemCond.STEEL);
    public static final DeferredHolder<Item, Item> STEEL_SHOVEL = HELPER.regShovelItem("steel_shovel", new Item.Properties(), MFTiers.STEEL, 1, -3.0F, MFItemCond.STEEL);
    public static final DeferredHolder<Item, Item> STEEL_HOE = HELPER.regHoeItem("steel_hoe", new Item.Properties(), MFTiers.STEEL, -2, -1.0F, MFItemCond.STEEL);
    public static final DeferredHolder<Item, Item> STEEL_ARMORS = HELPER.regArmors("steel", new Item.Properties(), STEEL_ARMOR, new int[] {1000, 1000, 1000, 1000}, MFItemCond.STEEL);

    public static final DeferredHolder<Item, Item> HEPATIZON_SWORD = HELPER.regSwordItem("hepatizon_sword", new Item.Properties(), MFTiers.HEPATIZON, 3, -2.4F, MFItemCond.HEPATIZON);
    public static final DeferredHolder<Item, Item> HEPATIZON_AXE = HELPER.regAxeItem("hepatizon_axe", new Item.Properties(), MFTiers.HEPATIZON, 6, -3.0F, MFItemCond.HEPATIZON);
    public static final DeferredHolder<Item, Item> HEPATIZON_PICKAXE = HELPER.regPickaxeItem("hepatizon_pickaxe", new Item.Properties(), MFTiers.HEPATIZON, 1, -2.8F, MFItemCond.HEPATIZON);
    public static final DeferredHolder<Item, Item> HEPATIZON_SHOVEL = HELPER.regShovelItem("hepatizon_shovel", new Item.Properties(), MFTiers.HEPATIZON, 1, -3.0F, MFItemCond.HEPATIZON);
    public static final DeferredHolder<Item, Item> HEPATIZON_HOE = HELPER.regHoeItem("hepatizon_hoe", new Item.Properties(), MFTiers.HEPATIZON, -2, -1.0F, MFItemCond.HEPATIZON);
    public static final DeferredHolder<Item, Item> HEPATIZON_ARMORS = HELPER.regArmors("hepatizon", new Item.Properties(), HEPATIZON_ARMOR, new int[] {3000, 3000, 3000, 3000}, builder -> builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(MatchaFlavoured.loc("hepatizon.movement_speed"), 0.01, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.ARMOR), MFItemCond.HEPATIZON);

    public static final DeferredHolder<Item, Item> ELECTRUM_SWORD = HELPER.regSwordItem("electrum_sword", new Item.Properties(), MFTiers.ELECTRUM, 3, -2.0F, MFItemCond.ELECTRUM);
    public static final DeferredHolder<Item, Item> ELECTRUM_AXE = HELPER.regAxeItem("electrum_axe", new Item.Properties(), MFTiers.ELECTRUM, 6, -3.0F, MFItemCond.ELECTRUM);
    public static final DeferredHolder<Item, Item> ELECTRUM_PICKAXE = HELPER.regPickaxeItem("electrum_pickaxe", new Item.Properties(), MFTiers.ELECTRUM, 1, -2.8F, MFItemCond.ELECTRUM);
    public static final DeferredHolder<Item, Item> ELECTRUM_SHOVEL = HELPER.regShovelItem("electrum_shovel", new Item.Properties(), MFTiers.ELECTRUM, 1, -3.0F, MFItemCond.ELECTRUM);
    public static final DeferredHolder<Item, Item> ELECTRUM_HOE = HELPER.regHoeItem("electrum_hoe", new Item.Properties(), MFTiers.ELECTRUM, -1, 0.0F, MFItemCond.ELECTRUM);
    public static final DeferredHolder<Item, Item> ELECTRUM_ARMORS = HELPER.regArmors("electrum", new Item.Properties(), ELECTRUM_ARMOR, new int[] {5000, 5000, 5000, 5000}, builder -> builder.add(MFAttributes.WARDING_POWER, new AttributeModifier(MatchaFlavoured.loc("electrum.warding_power"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ARMOR), MFItemCond.ELECTRUM);

    public static final DeferredHolder<Item, Item> SHAKUDO_SWORD = HELPER.regSwordItem("shakudo_sword", new Item.Properties(), MFTiers.SHAKUDO, 3, -2.4F, MFItemCond.SHAKUDO);
    public static final DeferredHolder<Item, Item> SHAKUDO_AXE = HELPER.regAxeItem("shakudo_axe", new Item.Properties(), MFTiers.SHAKUDO, 6, -3.1F, MFItemCond.SHAKUDO);
    public static final DeferredHolder<Item, Item> SHAKUDO_PICKAXE = HELPER.regPickaxeItem("shakudo_pickaxe", new Item.Properties(), MFTiers.SHAKUDO, 1, -2.8F, MFItemCond.SHAKUDO);
    public static final DeferredHolder<Item, Item> SHAKUDO_SHOVEL = HELPER.regShovelItem("shakudo_shovel", new Item.Properties(), MFTiers.SHAKUDO, 1, -3.0F, MFItemCond.SHAKUDO);
    public static final DeferredHolder<Item, Item> SHAKUDO_HOE = HELPER.regHoeItem("shakudo_hoe", new Item.Properties(), MFTiers.SHAKUDO, -2, -1.0F, MFItemCond.SHAKUDO);
    public static final DeferredHolder<Item, Item> SHAKUDO_ARMORS = HELPER.regArmors("shakudo", new Item.Properties(), SHAKUDO_ARMOR, new int[] {3000, 3000, 3000, 3000}, builder -> builder.add(IPAttributes.MANA_RESISTANCE, new AttributeModifier(MatchaFlavoured.loc("shakudo.mana_resistance"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.ARMOR), MFItemCond.SHAKUDO);

    //trading
    public static final DeferredHolder<Item, Item> OBOL = HELPER.reg("obol", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> APPLICATION = HELPER.reg("application", () -> new SpawnEggLikeItem(EntityType.VILLAGER, new Item.Properties()));

    public static DeferredHolder<Item, Item> regFood(String name, Item.Properties properties, SoundEvent sound, float amount, FoodItem.Type type, RegConditions conditions) {
        return HELPER.reg(name, () -> new MFFoodItem(properties, sound, FoodItem.getDefaultDuration(), amount, type), conditions);
    }

    public static DeferredHolder<Item, Item> regFood(String name, Item.Properties properties, SoundEvent sound, float amount, FoodItem.Type type) {
        return regFood(name, properties, sound, amount, type, null);
    }

    public static DeferredHolder<Item, Item> regFood(String name, Item.Properties properties, float amount, FoodItem.Type type, RegConditions conditions) {
        return regFood(name, properties, SoundEvents.GENERIC_EAT, amount, type, conditions);
    }

    public static DeferredHolder<Item, Item> regFood(String name, Item.Properties properties, float amount, FoodItem.Type type) {
        return regFood(name, properties, SoundEvents.GENERIC_EAT, amount, type, null);
    }

    public static DeferredHolder<Item, Item> regContainerFood(String name, Item.Properties properties, SoundEvent sound, float amount, FoodItem.Type type, RegConditions conditions) {
        return HELPER.reg(name, () -> new MFContainerFoodItem(properties.stacksTo(16), sound, amount, type), conditions);
    }

    public static DeferredHolder<Item, Item> regBowlFood(String name, Item.Properties properties, float amount, FoodItem.Type type, RegConditions conditions) {
        return regContainerFood(name, properties.craftRemainder(Items.BOWL), SoundEvents.GENERIC_EAT, amount, type, conditions);
    }

    public static DeferredHolder<Item, Item> regBowlFood(String name, Item.Properties properties, float amount, FoodItem.Type type) {
        return regBowlFood(name, properties, amount, type, null);
    }

    public static DeferredHolder<Item, Item> regDrink(String name, Item.Properties properties, float amount, RegConditions conditions) {
        return regContainerFood(name, properties.craftRemainder(Items.GLASS_BOTTLE), SoundEvents.GENERIC_DRINK, amount, FoodItem.Type.DRINK, conditions);
    }

    public static DeferredHolder<Item, Item> regDrink(String name, Item.Properties properties, float amount) {
        return regDrink(name, properties, amount, null);
    }

    public static DeferredHolder<Item, Item> regSyrup(String name, Item.Properties properties, float amount, RegConditions conditions) {
        return regContainerFood(name, properties.craftRemainder(Items.GLASS_BOTTLE), SoundEvents.HONEY_DRINK, amount, FoodItem.Type.SYRUP, conditions);
    }

    public static DeferredHolder<Item, Item> regSyrup(String name, Item.Properties properties, float amount) {
        return regSyrup(name, properties, amount, null);
    }

    public static DeferredHolder<Item, Item> regCannedFood(String name, Item.Properties properties, float amount, RegConditions conditions) {
        return regContainerFood(name, properties.craftRemainder(Items.GLASS_BOTTLE), SoundEvents.GENERIC_EAT, amount, FoodItem.Type.SNACK, conditions);
    }

    public static DeferredHolder<Item, Item> regCannedFood(String name, Item.Properties properties, float amount) {
        return regCannedFood(name, properties, amount, null);
    }

    public static DeferredHolder<Item, Item> regSecretFood(String name, Item.Properties properties, float amount, FoodItem.Type type, RegConditions conditions) {
        return HELPER.reg(name, () -> new SecretFoodItem(properties, amount, type), conditions);
    }

    static class MFItemCond {
        public static final RegConditions TOMATOES = new RegConditions(MFItems.TOMATOES);

        public static final RegConditions COPPER = new RegConditions(Items.COPPER_INGOT);
        public static final RegConditions STEEL = new RegConditions(MFItems.STEEL_ALLOY);
        public static final RegConditions HEPATIZON = new RegConditions(MFItems.HEPATIZON_ALLOY);
        public static final RegConditions ELECTRUM = new RegConditions(MFItems.ELECTRUM_ALLOY);
        public static final RegConditions SHAKUDO = new RegConditions(MFItems.SHAKUDO_ALLOY);
    }

    public static class MFFoods {
        public static final FoodProperties MILK_BOTTLE = props(0, 0).build();
        public static final FoodProperties ICE_CREAM = props(6, 0.6F).build();
        public static final FoodProperties PUERQUITO = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0F).build();

        public static final FoodProperties BAKED_APPLE = props(4, 0.6F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200), 1.0F).build();
        public static final FoodProperties BAKED_GOLDEN_APPLE = props(8, 1.2F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400), 1.0F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 1200), 1.0F).build();
        public static final FoodProperties GRILLED_MELON = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200), 1.0F).build();
        public static final FoodProperties BAKED_PUMPKIN = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600), 1.0F).build();
        public static final FoodProperties SWEET_BERRY_MASH = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200), 1.0F).build();
        public static final FoodProperties GLOW_BERRY_MASH = props(2, 0.4F).effect(() -> new MobEffectInstance(MFEffects.AURA, 60), 1.0F).build();
        public static final FoodProperties STEAMED_CARROTS = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 200), 1.0F).build();
        public static final FoodProperties STEAMED_GOLDEN_CARROTS = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1200), 1.0F).build();
        public static final FoodProperties NOPALES = props(4, 0.6F).build();

        public static final FoodProperties TOMATOES = props(2, 0.4F).build();
        public static final FoodProperties GRILLED_TOMATOES = props(4, 0.6F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600), 1.0F).build();

        public static final FoodProperties BRAISED_MUSHROOM = props(2, 0.4F).build();
        public static final FoodProperties BRAISED_RED_MUSHROOM = props(2, 0.4F).build();
        public static final FoodProperties BRAISED_CRIMSON_FUNGUS = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 600), 1.0F).build();
        public static final FoodProperties BRAISED_WARPED_FUNGUS = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 600), 1.0F).build();

        public static final FoodProperties FRIED_EGG = props(2, 0.4F).build();
        public static final FoodProperties COOKED_FISH = props(4, 0.8F).build();
        public static final FoodProperties COOKED_LARGE_FISH = props(5, 0.8F).build();
        public static final FoodProperties CHARRED_FISH = props(2, 0.4F).build();
        public static final FoodProperties CHARRED_MEAT = props(2, 0.4F).build();
        public static final FoodProperties CHARRED_POTATO = props(1, 0.2F).build();

        public static final FoodProperties CHOCOLATE = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600), 1.0F).build();
        public static final FoodProperties CHEESE = props(3, 0.6F).build();

        public static final FoodProperties BRUSCHETTA = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3000, 1), 1.0F).build();
        public static final FoodProperties CHEESE_PIZZA = props(9, 1.0F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600), 1.0F).build();
        public static final FoodProperties MEAT_PIZZA = props(10, 1.0F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600), 1.0F).build();
        public static final FoodProperties MUSHROOM_PIZZA = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600), 1.0F).build();
        public static final FoodProperties WARPED_PIZZA = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 6000), 1.0F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600), 1.0F).build();
        public static final FoodProperties PUPUSA = props(3, 0.4F).build();
        public static final FoodProperties SWEET_BERRY_DANISH = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600, 1), 1.0F).build();
        public static final FoodProperties GLOW_BERRY_CRUMBLE = props(8, 0.8F).effect(() -> new MobEffectInstance(MFEffects.AURA, 1200, 1), 1.0F).build();

        public static final FoodProperties FRENCH_TOAST = props(8, 0.8F).build();
        public static final FoodProperties HONIED_FRENCH_TOAST = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 1), 1.0F).build();
        public static final FoodProperties SWEET_BERRY_TOAST = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 9600, 1), 1.0F).effect(() -> new MobEffectInstance(MFEffects.EXALTATION, 1200), 1.0F).build();

        public static final FoodProperties LATKE = props(9, 1.0F).build();
        public static final FoodProperties FISH_AND_CHIPS = props(10, 1.0F).build();
        public static final FoodProperties LUMPIA = props(5, 0.8F).effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 6000), 1.0F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 6000), 1.0F).build();
        public static final FoodProperties GIMMARI = props(5, 0.8F).effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 12000), 1.0F).build();

        public static final FoodProperties NAAN = props(4, 0.6F).build();
        public static final FoodProperties PUMPKIN_EMPANADA = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9600), 1.0F).build();
        public static final FoodProperties APPLE_EMPANADA = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 3600), 1.0F).build();
        public static final FoodProperties GOLDEN_APPLE_EMPANADA = props(8, 1.0F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 900, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 4800, 1), 1.0F).build();
        public static final FoodProperties BROWNIE = props(6, 0.8F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 1), 1.0F).build();
        public static final FoodProperties BUNUELO = props(6, 0.8F).effect(() -> new MobEffectInstance(MobEffects.LUCK, 3600), 1.0F).build();
        public static final FoodProperties CARROT_CUPCAKE = props(8, 0.8F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 12000), 1.0F).build();
        public static final FoodProperties GOLDEN_CARROT_CUPCAKE = props(8, 1.0F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 24000), 1.0F).build();

        public static final FoodProperties CHORUS_MOCHI = props(4, 0.5F).effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 20, 29), 1.0F).effect(() -> new MobEffectInstance(MFEffects.EXALTATION, 300), 1.0F).build();

        public static final FoodProperties CURRY = props(20, 1.2F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 36000), 1.0F).build();
        public static final FoodProperties PANEER_MAKHANI = props(20, 1.2F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 12000), 1.0F).build();
        public static final FoodProperties PUMPKIN_CURRY = props(20, 1.2F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 24000), 1.0F).build();
        public static final FoodProperties GREEN_CURRY = props(20, 1.2F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 36000, 1), 1.0F).build();
        public static final FoodProperties RAMEN = props(20, 1.2F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 36000, 1), 1.0F).build();

        public static final FoodProperties APPLE_CINNAMON_OATMEAL = props(10, 1.0F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 12000), 1.0F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 2400), 1.0F).build();
        public static final FoodProperties BOKGUK = props(12, 1.0F).effect(() -> new MobEffectInstance(MobEffects.CONDUIT_POWER, 9600), 1.0F).build();
        public static final FoodProperties STROGANOFF = props(16, 1.2F).build();
        public static final FoodProperties RED_MUSHROOM_STROGANOFF = props(12, 1.0F).build();
        public static final FoodProperties CRIMSON_FUNGUS_STROGANOFF = props(12, 1.0F).effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 6000), 1.0F).build();
        public static final FoodProperties WARPED_FUNGUS_STROGANOFF = props(12, 1.0F).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 12000), 1.0F).effect(() -> new MobEffectInstance(MFEffects.EXALTATION, 1200), 1.0F).usingConvertsTo(Items.BOWL).build();
        public static final FoodProperties GNOCCHI = props(16, 1.2F).effect(() -> new MobEffectInstance(MFEffects.EXALTATION, 1200), 1.0F).usingConvertsTo(Items.BOWL).build();
        public static final FoodProperties KONTOMIRE_STEW = props(12, 1.0F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000), 1.0F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 1200), 1.0F).build();
        public static final FoodProperties PAD_THAI = props(10, 1.0F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 12000), 1.0F).build();

        public static final FoodProperties HONEY_GINGER_TEA = props(2, 0.4F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600), 1.0F).build();
        public static final FoodProperties MEAD = props(6, 0.8F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000), 1.0F).build();
        public static final FoodProperties BUBBLING_MEAD = props(6, 0.0F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000), 1.0F).build();

        public static final FoodProperties GLOW_BERRY_JAM = props(4, 0.6F).effect(() -> new MobEffectInstance(MFEffects.AURA, 600), 1.0F).build();
        public static final FoodProperties SWEET_BERRY_JAM = props(4, 0.6F).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 3600), 1.0F).build();
        public static final FoodProperties PUMPKIN_JAM = props(4, 0.6F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600), 1.0F).build();
        public static final FoodProperties RIND_JAM = props(4, 0.6F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000), 1.0F).build();
        public static final FoodProperties MELON_SORBET = props(6, 0.6F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000), 1.0F).build();

        public static final FoodProperties CANNED_APPLES = props(6, 0.8F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 1200), 1.0F).build();
        public static final FoodProperties CANNED_GOLDEN_APPLES = props(8, 1.0F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1), 1.0F).build();
        public static final FoodProperties PICKLED_CARROTS = props(4, 0.6F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 6000), 1.0F).build();
        public static final FoodProperties PICKLED_GOLDEN_CARROTS = props(4, 0.8F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 12000), 1.0F).build();
        public static final FoodProperties PICKLED_POTATOES = props(10, 1.0F).build();
        public static final FoodProperties PICKLED_TOMATOES = props(6, 0.8F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000), 1.0F).build();
        public static final FoodProperties PICKLED_BROWN_MUSHROOMS = props(6, 0.8F).build();
        public static final FoodProperties PICKLED_RED_MUSHROOMS = props(3, 0.4F).build();
        public static final FoodProperties PICKLED_CRIMSON_FUNGUS = props(3, 0.4F).effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 6000), 1.0F).build();
        public static final FoodProperties PICKLED_WARPED_FUNGUS = props(3, 0.4F).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 6000), 1.0F).build();

        public static FoodProperties.Builder props(int nutrition, float saturation) {
            boolean matcha = MatchaFlavouredConfig.Startup.DEFINE_HUNGER_SYSTEM.get() == 0;
            return matcha ? new FoodProperties.Builder().alwaysEdible() : new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation);
        }
    }

}
