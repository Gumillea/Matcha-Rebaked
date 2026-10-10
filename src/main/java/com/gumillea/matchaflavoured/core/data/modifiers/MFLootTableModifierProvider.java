package com.gumillea.matchaflavoured.core.data.modifiers;

import com.gumillea.inlandport.core.data.modifiers.IPLootModifierProvider;
import com.gumillea.inlandport.core.util.utils.IPUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.core.data.tags.MFBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.item.Items.*;
import static com.gumillea.inlandport.core.util.utils.LootTableUtil.*;
import static com.gumillea.matchaflavoured.core.reg.MFItems.*;

public class MFLootTableModifierProvider extends IPLootModifierProvider {

    public MFLootTableModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future, MatchaFlavoured.MODID);
    }

    @Override
    protected void start() {
        injectIntoPlant(SHORT_GRASS, Blocks.SHORT_GRASS, hasTool(ItemTags.HOES));
        injectIntoPlant(TALL_GRASS, Blocks.TALL_GRASS, hasTool(ItemTags.HOES));
        injectIntoPlant(GLOW_LICHEN, Blocks.GLOW_LICHEN, hasTool(ItemTags.HOES));

        inject(DIVINE_FRAGMENT.get(), Blocks.SPAWNER);
        inject(DIVINE_FRAGMENT.get(), BuiltInLootTables.ABANDONED_MINESHAFT, 0.08F);
        inject(DIVINE_FRAGMENT.get(), BuiltInLootTables.WOODLAND_MANSION, 0.15F);

        inject(OBOL.get(), new Object[]{BuiltInLootTables.ABANDONED_MINESHAFT, BuiltInLootTables.SIMPLE_DUNGEON}, 0.2F);

        replaceItem(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_COMMON, GOLD_NUGGET, AMBER.get());
        inject(RUBY.get(), BuiltInLootTables.BASTION_TREASURE, 1F);
        inject(RUBY.get(), new Object[]{BuiltInLootTables.BASTION_BRIDGE, BuiltInLootTables.BASTION_OTHER, BuiltInLootTables.BASTION_HOGLIN_STABLE}, 0.15F);
        inject(OPAL.get(), EntityType.ELDER_GUARDIAN);
        inject(TOPAZ.get(), BuiltInLootTables.ANCIENT_CITY, 0.33F);

        inject(FOX_PELT.get(), EntityType.FOX);
        inject(TATTERED_LEATHER.get(), EntityType.ZOMBIE, 0.25F);
        inject(TATTERED_LEATHER.get(), EntityType.DROWNED, 0.25F);
        inject(OBOL.get(), EntityType.ZOMBIE_VILLAGER);
        inject(OBOL.get(), EntityType.PILLAGER);

        biomeFishing(Biomes.DEEP_DARK, LootPool.lootPool().setRolls(ConstantValue.exactly(1)).setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(SCULK_VEIN).setWeight(70))
                        .add(LootItem.lootTableItem(ECHO_EEL.get()).setWeight(25))
                        .add(LootItem.lootTableItem(ECHO_SHARD).setWeight(5)),
                10, 5, 0, 85);


        biomeFishing(Biomes.LUSH_CAVES, LootPool.lootPool().setRolls(ConstantValue.exactly(1)).setBonusRolls(ConstantValue.exactly(0))
                        .add(LootItem.lootTableItem(AXOLOTL.get()).setWeight(100)),
                10, 5, 65, 20);

        biomeFishing(MFBiomeTags.IS_FRESHWATER_COLD, ALASKA_BLACKFISH.get(), HUMPBACK_WHITEFISH.get(), SPOONHEAD_SCULPIN.get(), NORTHERN_PIKE.get(), SIBERIAN_STURGEON.get());
        biomeFishing(MFBiomeTags.IS_FRESHWATER_COOL, STRIPED_PERCH.get(), CRAPPIE.get(), WALLEYE.get(), GAR.get(), MUSKELLUNGE.get());
        biomeFishing(MFBiomeTags.IS_FRESHWATER_TEMPERATE, BLUEGILL.get(), CRAPPIE.get(), CARP.get(), CATFISH.get(), BASS.get());
        biomeFishing(MFBiomeTags.IS_FRESHWATER_ARID, MEDITERRANEAN_KILLIFISH.get(), FRESHWATER_PUFFERFISH.get(), LAMPREY.get(), TUNISIAN_BARB.get(), EUROPEAN_EEL.get());
        biomeFishing(MFBiomeTags.IS_FRESHWATER_TROPICAL, GUPPY.get(), BUJURQUI.get(), PIRANHA.get(), ARMOURED_CATFISH.get(), ARAPAIMA.get());

        biomeFishing(MFBiomeTags.IS_SALTWATER_COLD, COD, SALMON, HERRING.get(), WOLFFISH.get(), SKATE.get());
        biomeFishing(MFBiomeTags.IS_SALTWATER_COOL, COD, SALMON, BLACK_SEABASS.get(), MONKFISH.get(), OPAH.get());
        biomeFishing(MFBiomeTags.IS_SALTWATER_COOL, ANCHOVY.get(), SHAD.get(), FLYING_FISH.get(), STURGEON.get(), BLUE_MARLIN.get());
        biomeFishing(MFBiomeTags.IS_SALTWATER_WARM, TROPICAL_FISH, RAINBOW_WRASSE.get(), GURNARD.get(), FLOUNDER.get(), BLUE_MARLIN.get());
        biomeFishing(MFBiomeTags.IS_SALTWATER_HOT, TROPICAL_FISH, PUFFERFISH, MAHI_MAHI.get(), PAINTED_MORAY.get(), OARFISH.get());

        biomeFishing(MFBiomeTags.IS_BRACKISH_WATER, GUPPY.get(), BUJURQUI.get(), LAMPREY.get(), FLOUNDER.get(), ARAPAIMA.get());

        replaceItem(Blocks.DIAMOND_ORE, DIAMOND, RAW_DIAMOND.get());
        replaceItem(Blocks.DEEPSLATE_DIAMOND_ORE, DIAMOND, RAW_DIAMOND.get());
        replaceItem(BuiltInLootTables.SIMPLE_DUNGEON, REDSTONE, ESTUS_DUST.get());
        replaceItem(BuiltInLootTables.SIMPLE_DUNGEON, COAL, OBOL.get());
        replaceItem(BuiltInLootTables.SIMPLE_DUNGEON, ROTTEN_FLESH, TATTERED_LEATHER.get());
        replaceItems(BuiltInLootTables.WOODLAND_MANSION, new Item[]{MELON_SEEDS, BEETROOT_SEEDS, PUMPKIN_SEEDS}, TOMATO_SEEDS.get());

    }

    protected void biomeFishing(Object biome, LootPool.Builder builder, int junkWeight, int treasureWeight, int vanillaFishWeight, int biomeFishWeight, ICondition... conditions) {
        ResourceLocation table =  MatchaFlavoured.loc("fishing/fish/" + getLoc(biome).getPath());
        map.put(table, LootTable.lootTable().setRandomSequence(IPUtil.mcLoc("gameplay/fishing/fish")).withPool(builder).build());

        replaceTableByBiome("fishing/fish/" + getLoc(biome).getPath(), BuiltInLootTables.FISHING, biome, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(NestedLootTable.lootTableReference(BuiltInLootTables.FISHING_JUNK).setQuality(-1).setWeight(junkWeight))
                .add(NestedLootTable.lootTableReference(BuiltInLootTables.FISHING_TREASURE).setQuality(2).setWeight(treasureWeight))
                .add(NestedLootTable.lootTableReference(BuiltInLootTables.FISHING_FISH).setQuality(-1).setWeight(vanillaFishWeight))
                .add(NestedLootTable.lootTableReference(ResourceKey.create(Registries.LOOT_TABLE, table)).setQuality(-1).setWeight(biomeFishWeight))), conditions);
    }

    protected void biomeFishing(Object biome, LootPool.Builder builder, ICondition... conditions) {
        biomeFishing(biome, builder, 10, 5, 20, 65, conditions);
    }

    protected void biomeFishing(Object biome, Item commonFishA, Item commonFishB, Item rareFishA, Item rareFishB, Item epicFish, ICondition... conditions) {
        biomeFishing(biome, LootPool.lootPool().setRolls(ConstantValue.exactly(1)).setBonusRolls(ConstantValue.exactly(0))
                .add(LootItem.lootTableItem(commonFishA).setWeight(35))
                .add(LootItem.lootTableItem(commonFishB).setWeight(35))
                .add(LootItem.lootTableItem(rareFishA).setWeight(20))
                .add(LootItem.lootTableItem(rareFishB).setWeight(7))
                .add(LootItem.lootTableItem(epicFish).setWeight(3)), conditions);
    }



}