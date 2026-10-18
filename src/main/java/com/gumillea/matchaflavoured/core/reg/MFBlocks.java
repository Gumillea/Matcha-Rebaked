package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.inlandport.common.block.crop.Age3Block;
import com.gumillea.inlandport.common.block.family.Variant;
import com.gumillea.inlandport.core.api.record.RegConditions;
import com.gumillea.inlandport.core.util.helpers.reg.BlockHelper;
import com.gumillea.inlandport.core.util.helpers.reg.RegHelper;
import com.gumillea.inlandport.core.util.utils.VariantUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.block.*;
import com.gumillea.matchaflavoured.common.item.ClayFetishItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.gumillea.matchaflavoured.core.reg.MFItems.*;

public class MFBlocks {
    public static final BlockHelper HELPER = new BlockHelper(MatchaFlavoured.MODID);

    public static final DeferredHolder<Block, Block> SULFUR = HELPER.regStoneFamily("sulfur", Properties.SULFUR, Variant.except(Variant.PILLAR, VariantUtil.Prefixes.CRACKED, VariantUtil.Prefixes.MOSSY, VariantUtil.Suffixes.TILES), null);

    public static final DeferredHolder<Block, Block> QUARTZ_ORE = HELPER.reg("quartz_ore", () ->  new DropExperienceBlock(UniformInt.of(2, 5), Properties.ORE));
    public static final DeferredHolder<Block, Block> DEEPSLATE_QUARTZ_ORE = HELPER.reg("deepslate_quartz_ore", () ->  new DropExperienceBlock(UniformInt.of(2, 5), Properties.DEEPSLATE_ORE));
    public static final DeferredHolder<Block, Block> SILVER_ORE = HELPER.reg("silver_ore", () ->  new DropExperienceBlock(ConstantInt.of(0), Properties.ORE));
    public static final DeferredHolder<Block, Block> DEEPSLATE_SILVER_ORE = HELPER.reg("deepslate_silver_ore", () ->  new DropExperienceBlock(ConstantInt.of(0), Properties.DEEPSLATE_ORE));
    public static final DeferredHolder<Block, Block> SULFUR_ORE = HELPER.reg("sulfur_ore", () ->  new DropExperienceBlock(ConstantInt.of(0), Properties.ORE.mapColor(MapColor.COLOR_YELLOW)));

    public static final DeferredHolder<Block, Block> STRAW_BALE = HELPER.reg("straw_bale", () -> new HayBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.SAND)), new RegConditions(STRAW));
    public static final DeferredHolder<Block, Block> OBOL_BLOCK = HELPER.reg("obol_block", () -> new Block(Properties.OBOL_BLOCK), new RegConditions(OBOL));

    public static final DeferredHolder<Block, Block> WARDING_STONE = HELPER.reg("warding_stone", () -> new WardingStoneBlock(Properties.OBOL_BLOCK), new RegConditions(NAZAR));
    public static final DeferredHolder<Block, Block> PHOSPHOR_TORCH = HELPER.regTorch("phosphor", () -> new PhosphorTorchBlock(Properties.TORCH), () -> new PhosphorWallTorchBlock(Properties.TORCH));

    public static final DeferredHolder<Block, Block> BEACON_KINDLING = HELPER.reg("beacon_kindling", () -> new BeaconCampfireBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).lightLevel((state) -> 15).noOcclusion()), new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Block, Block> SACK = HELPER.regShulkerBoxLikeBlock("sack", () -> new SackBlock(null, Properties.SACK), new Item.Properties().stacksTo(1).component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
    public static final DeferredHolder<Block, Block> CLAY_FETISH_REJOICE = regFetish("clay_fetish_rejoice", "clear");
    public static final DeferredHolder<Block, Block> CLAY_FETISH_LAMENT = regFetish("clay_fetish_lament", "rain");

    public static final DeferredHolder<Block, Block> TOMATOES_CROP = HELPER.regWithoutItem("tomatoes_crop", () -> new Age3Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), 3, MFItems.TOMATO_SEEDS));
    public static final DeferredHolder<Block, Block> CYAN_ROSE = HELPER.reg("cyan_rose", () -> new FlowerBlock(MobEffects.ABSORPTION, 5.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID)));
    public static final DeferredHolder<Block, Block> HYACINTH = HELPER.reg("hyacinth", () -> new FlowerBlock(MobEffects.WIND_CHARGED, 10.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_TULIP)));


    public static DeferredHolder<Block, Block> regFetish(String name, String weather) {
        DeferredHolder<Block, Block> block = HELPER.regWithoutItem(name, () -> new ClayFetishBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)).changesWeather(weather), null);
        RegHelper.reg(HELPER.itemReg, name, () -> new ClayFetishItem(block.value(), new Item.Properties()), () -> new Item(new Item.Properties()), null);
        return block;
    }

    static class Properties {
        public static final BlockBehaviour.Properties SULFUR = BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).mapColor(MapColor.COLOR_YELLOW);
        public static final BlockBehaviour.Properties TORCH = BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_TORCH).mapColor(MapColor.COLOR_CYAN);
        public static final BlockBehaviour.Properties OBOL_BLOCK = BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).mapColor(MapColor.COLOR_BROWN);
        public static final BlockBehaviour.Properties ORE = BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE);
        public static final BlockBehaviour.Properties DEEPSLATE_ORE = BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE);
        public static final BlockBehaviour.Properties SACK = BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL);
    }

}
