package com.gumillea.matchaflavoured.core.data.tags;

import com.gumillea.inlandport.core.util.utils.CompatUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class MFBiomeTags {
    public static final TagKey<Biome> IS_FRESHWATER = fishingTag("is_freshwater");
    public static final TagKey<Biome> IS_FRESHWATER_COLD = isFreshwaterTag("cold");
    public static final TagKey<Biome> IS_FRESHWATER_COOL = isFreshwaterTag("cool");
    public static final TagKey<Biome> IS_FRESHWATER_TEMPERATE = isFreshwaterTag("temperate");
    public static final TagKey<Biome> IS_FRESHWATER_ARID = isFreshwaterTag("arid");
    public static final TagKey<Biome> IS_FRESHWATER_TROPICAL = isFreshwaterTag("tropical");

    public static final TagKey<Biome> IS_SALTWATER = fishingTag("is_saltwater");
    public static final TagKey<Biome> IS_SALTWATER_COLD = isSaltwaterTag("cold");
    public static final TagKey<Biome> IS_SALTWATER_COOL = isSaltwaterTag("cool");
    public static final TagKey<Biome> IS_SALTWATER_TEMPERATE = isSaltwaterTag("temperate");
    public static final TagKey<Biome> IS_SALTWATER_WARM = isSaltwaterTag("warm");
    public static final TagKey<Biome> IS_SALTWATER_HOT = isSaltwaterTag("hot");

    public static final TagKey<Biome> IS_BRACKISH_WATER = isSaltwaterTag("is_brackish_water");

    private static TagKey<Biome> tag(String name) {
        return CompatUtil.biomeTag(MatchaFlavoured.MODID, name);
    }

    private static TagKey<Biome> fishingTag(String name) {
        return CompatUtil.biomeTag(MatchaFlavoured.MODID, "fishing/" + name);
    }

    private static TagKey<Biome> isFreshwaterTag(String name) {
        return fishingTag("is_freshwater/" + name);
    }

    private static TagKey<Biome> isSaltwaterTag(String name) {
        return fishingTag("is_saltwater/" + name);
    }
}
