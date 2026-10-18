package com.gumillea.matchaflavoured.core.data.tags;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static com.gumillea.inlandport.core.util.utils.CompatUtil.blockTag;

public class MFBlockTags {
    public static final TagKey<Block> MINEABLE_WITH_DOLABRA =  blockTag(MatchaFlavoured.MODID, "mineable/dolabra");
    public static final TagKey<Block> MINEABLE_WITH_MATTOCK =  blockTag(MatchaFlavoured.MODID, "mineable/mattock");
}
