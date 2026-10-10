package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.inlandport.core.util.helpers.reg.AttrHelper;
import com.gumillea.inlandport.core.util.utils.AttrUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MFAttributes {
    public static final AttrHelper HELPER = new AttrHelper(MatchaFlavoured.MODID);

    public static final DeferredHolder<Attribute, Attribute> WARDING_POWER = HELPER.regRanged("warding_power", 0, 0, 1024.0);
    public static final DeferredHolder<Attribute, Attribute> FOOD_REGEN = HELPER.regRanged("food_regen", 0, 0, 20.0);

    public static double getWardingPower(LivingEntity living) {
        return AttrUtil.get(living, WARDING_POWER);
    }

    public static double getCrystalHeart(LivingEntity living) {
        return AttrUtil.get(living, FOOD_REGEN);
    }
}
