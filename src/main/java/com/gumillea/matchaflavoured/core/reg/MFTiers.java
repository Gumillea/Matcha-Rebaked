package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.inlandport.core.util.helpers.reg.ArmorMaterialHelper;
import com.gumillea.inlandport.core.util.helpers.reg.EffectHelper;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.item.WardingPowerItem;
import com.gumillea.matchaflavoured.common.item.WardingShieldItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static net.minecraft.world.item.Item.*;

public class MFTiers {
    public static final ArmorMaterialHelper HELPER = new ArmorMaterialHelper(MatchaFlavoured.MODID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> COPPER_ARMOR = HELPER.reg("copper", new int[] {2, 4, 3, 1, 10}, 8, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(Tags.Items.INGOTS_COPPER), 0, 0);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STEEL_ARMOR = HELPER.reg("steel", new int[] {2, 7, 5, 2, 10}, 16, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(Tags.Items.INGOTS_IRON), 1, 0);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> HEPATIZON_ARMOR = HELPER.reg("hepatizon", new int[] {3, 8, 6, 3, 10}, 18, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(Items.COPPER_BLOCK), 0, 0);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SHAKUDO_ARMOR = HELPER.reg("shakudo", new int[] {2, 6, 4, 1, 10}, 18, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(Items.COPPER_BLOCK), 0, 0);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ELECTRUM_ARMOR = HELPER.reg("electrum", new int[] {3, 8, 6, 3, 10}, 18, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(Items.COPPER_BLOCK), 0, 0);

    public static final Tier COPPER = new SimpleTier(BlockTags.INCORRECT_FOR_STONE_TOOL, 190, 5F, 1F, 13, () -> Ingredient.of(Tags.Items.INGOTS_COPPER));
    public static final Tier SILVER = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6F, 2F, 18, () -> Ingredient.of(MFItems.NAZAR.get(), Items.LAPIS_BLOCK));
    public static final Tier NAZAR = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6F, 2F, 20, () -> Ingredient.of(MFItems.NAZAR.get(), Items.LAPIS_BLOCK));

    public static final Tier STEEL = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 1249, 7F, 2F, 16, () -> Ingredient.of(Tags.Items.INGOTS_IRON));
    public static final Tier HEPATIZON = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 1000, 20F, 2.5F, 18, () -> Ingredient.of(Items.COPPER_BLOCK));
    public static final Tier ELECTRUM = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 2342, 12F, 3F, 20, () -> Ingredient.of(Items.DIAMOND_BLOCK));
    public static final Tier SHAKUDO = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 1000, 9F, 2F, 18, () -> Ingredient.of(Items.COPPER_BLOCK));

    public static ItemAttributeModifiers.Builder baseAttrs(Tier tier, int damage, float speed) {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, damage + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
    }

    public static ItemAttributeModifiers createWardingAttrs(Tier tier, int damage, float speed, int warding) {
        return baseAttrs(tier, damage, speed).add(MFAttributes.WARDING_POWER, new AttributeModifier(MatchaFlavoured.loc("base_warding_power"), warding, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

}
