package com.gumillea.matchaflavoured.common.item;

import com.gumillea.matchaflavoured.core.data.tags.MFBlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

public class MattockItem extends DiggerItem {

    public MattockItem(Tier tier, Properties properties) {
        super(tier, MFBlockTags.MINEABLE_WITH_MATTOCK, properties);
    }

}
