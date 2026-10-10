package com.gumillea.matchaflavoured.common.item;

import com.gumillea.inlandport.common.item.SimpleHatItem;
import com.gumillea.inlandport.core.util.utils.RegUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EarringItem extends SimpleHatItem {

    private final Holder<MobEffect> effect;

    public EarringItem(Properties properties, @Nullable Holder<MobEffect> effect) {
        super(properties);
        this.effect = effect;
    }

    public static ItemAttributeModifiers createAttributes(Holder<Attribute> attribute, Object amount) {
        return amount instanceof Double ? ItemAttributeModifiers.builder().add(attribute, new AttributeModifier(MatchaFlavoured.loc(RegUtil.path(attribute.value()) + ".earrings"), (double) amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HEAD).build() : ItemAttributeModifiers.builder().add(attribute, new AttributeModifier(MatchaFlavoured.loc(RegUtil.path(attribute.value()) + ".earrings"), (int) amount, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD).build();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof LivingEntity living && effect != null) {
            if (living.getItemBySlot(EquipmentSlot.HEAD) == stack) {

                if (level.getGameTime() % 100 == 0) {
                    living.addEffect(new MobEffectInstance(effect, 120, 0, true, true));
                }
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public Holder<SoundEvent> getEquipSound() {
        return ArmorMaterials.GOLD.value().equipSound();
    }
}
