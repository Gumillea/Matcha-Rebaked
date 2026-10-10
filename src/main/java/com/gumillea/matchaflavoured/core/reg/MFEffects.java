package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.inlandport.core.util.helpers.reg.EffectHelper;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.effect.AuraEffect;
import com.gumillea.matchaflavoured.common.effect.ExaltationEffect;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MFEffects {
    public static final EffectHelper HELPER = new EffectHelper(MatchaFlavoured.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> AURA = HELPER.reg("aura", AuraEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> EXALTATION = HELPER.reg("exaltation", ExaltationEffect::new);

}
