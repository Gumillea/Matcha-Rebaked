package com.gumillea.matchaflavoured.common.item;

import com.gumillea.matchaflavoured.core.data.tags.MFBlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class DolabraItem extends DiggerItem {

    public DolabraItem(Tier tier, Properties properties) {
        super(tier, MFBlockTags.MINEABLE_WITH_DOLABRA, properties);
    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility);
    }
}
