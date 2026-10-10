package com.gumillea.matchaflavoured.common.effect;

import com.gumillea.inlandport.common.effect.SimpleMobEffect;
import com.gumillea.inlandport.core.util.utils.EffectUtil;
import com.gumillea.inlandport.core.util.utils.IPUtil;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class AuraEffect extends SimpleMobEffect {

    public AuraEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x80FF00, "All nearby hostile mobs glow until the effect wears off");
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        amplifier = amplifier + 1;
        AABB range = IPUtil.entityBox(living, 16 * amplifier, 16, 16 * amplifier);
        List<LivingEntity> targets = living.level().getEntitiesOfClass(LivingEntity.class, range, target -> target instanceof Enemy);

        for (LivingEntity target : targets) {
            EffectUtil.add(target, MobEffects.GLOWING, 100);
        }

        return true;
    }


    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295946_, int p_295536_) {
        return p_295946_ % 40 == 0;
    }

}
