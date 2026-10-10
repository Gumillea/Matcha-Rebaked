package com.gumillea.matchaflavoured.common.item;

import com.gumillea.matchaflavoured.core.reg.MFItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

public class WardingShieldItem extends ShieldItem {

    public WardingShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_43091_, ItemStack p_43092_) {
        return p_43092_.is(MFItems.NAZAR.get());
    }

}
