package com.gumillea.matchaflavoured.common.item;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.core.reg.MFAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class WardingPowerItem extends Item implements Equipable {

    private final int amount;

    public WardingPowerItem(Properties properties, int amount) {
        super(properties.stacksTo(1).attributes(createAttributes(amount)));
        this.amount = amount;
    }

    public static ItemAttributeModifiers createAttributes(int amount) {
        return ItemAttributeModifiers.builder().add(MFAttributes.WARDING_POWER, new AttributeModifier(MatchaFlavoured.loc("base_warding_power_offhand"), amount, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.OFFHAND).build();
    }

    public int getPower() {
        return amount;
    }

    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.OFFHAND;
    }

}
