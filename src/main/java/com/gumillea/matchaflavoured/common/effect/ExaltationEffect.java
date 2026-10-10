package com.gumillea.matchaflavoured.common.effect;

import com.gumillea.inlandport.common.effect.SimpleMobEffect;
import com.gumillea.inlandport.core.util.utils.AttrUtil;
import com.gumillea.inlandport.test.reg.IPAttributes;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static com.gumillea.matchaflavoured.common.item.CrystalHeartItem.CRYSTAL_HEART_BOOST;

public class ExaltationEffect extends SimpleMobEffect {

    public static final ResourceLocation DEBUFF_RESISTANCE = MatchaFlavoured.loc("effect.exaltation.debuff_resistance");
    public static final ResourceLocation ATTACK_SPEED = MatchaFlavoured.loc("effect.exaltation.attack_speed");

    public ExaltationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x88e6c4, "Increases debuff resistance and attack speed based on the maximum health gained through Crystal Hearts. Restores 2.5% health per second.");
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        addAttributeModifiers(living);
        return true;
    }

    private void addAttributeModifiers(LivingEntity living) {
        AttributeInstance dr_attr = living.getAttribute(IPAttributes.DEBUFF_RESISTANCE);
        AttributeInstance as_attr = living.getAttribute(Attributes.ATTACK_SPEED);

        Holder<Attribute> mh = Attributes.MAX_HEALTH;
        if (AttrUtil.has(living, mh)) {
            AttributeInstance inst = living.getAttribute(mh);
            AttributeModifier chb = inst.getModifier(CRYSTAL_HEART_BOOST);
            double hearts = chb != null ? chb.amount() : 0;

            if (dr_attr != null) {
                dr_attr.removeModifier(DEBUFF_RESISTANCE);
                dr_attr.addTransientModifier(new AttributeModifier(DEBUFF_RESISTANCE, hearts * 0.015D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }

            if (as_attr != null) {
                as_attr.removeModifier(ATTACK_SPEED);
                as_attr.addTransientModifier(new AttributeModifier(ATTACK_SPEED, hearts * 0.015D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }

            living.heal(living.getMaxHealth() * 0.05F);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}
