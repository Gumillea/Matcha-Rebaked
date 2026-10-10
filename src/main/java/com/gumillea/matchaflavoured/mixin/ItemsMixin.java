package com.gumillea.matchaflavoured.mixin;

import com.gumillea.inlandport.common.item.FoodItem;
import com.gumillea.matchaflavoured.common.item.MFFoodItem;
import com.gumillea.matchaflavoured.core.reg.MFItems;
import com.gumillea.matchaflavoured.core.reg.MFTiers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Items.class)
public class ItemsMixin {

    @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=stone_axe")), at = @At(value = "NEW", args = "class=net/minecraft/world/item/AxeItem", ordinal = 0))
    private static AxeItem matchaflavoured$overrideStoneAxe(Tier tier, Item.Properties props) {
        return new AxeItem(MFTiers.COPPER, props.attributes(PickaxeItem.createAttributes(MFTiers.COPPER, 7.0F, -3.2F)));
    }

    @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=stone_pickaxe")), at = @At(value = "NEW", args = "class=net/minecraft/world/item/PickaxeItem", ordinal = 0))
    private static PickaxeItem matchaflavoured$overrideStonePickaxe(Tier tier, Item.Properties props) {
        return new PickaxeItem(MFTiers.COPPER, props.attributes(PickaxeItem.createAttributes(MFTiers.COPPER, 1.0F, -2.8F)));
    }

    @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=stone_hoe")), at = @At(value = "NEW", args = "class=net/minecraft/world/item/HoeItem", ordinal = 0))
    private static HoeItem matchaflavoured$overrideStoneHoe(Tier tier, Item.Properties props) {
        return new HoeItem(MFTiers.COPPER, props.attributes(PickaxeItem.createAttributes(MFTiers.COPPER, -1.0F, -2.0F)));
    }

    @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=stone_shovel")), at = @At(value = "NEW", args = "class=net/minecraft/world/item/ShovelItem", ordinal = 0))
    private static ShovelItem matchaflavoured$overrideStoneShovel(Tier tier, Item.Properties props) {
        return new ShovelItem(MFTiers.COPPER, props.attributes(PickaxeItem.createAttributes(MFTiers.COPPER, 1.5F, -3.0F)));
    }

    @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=stone_sword")), at = @At(value = "NEW", args = "class=net/minecraft/world/item/SwordItem", ordinal = 0))
    private static SwordItem matchaflavoured$overrideStoneSword(Tier tier, Item.Properties props) {
        return new SwordItem(MFTiers.COPPER, props.attributes(PickaxeItem.createAttributes(MFTiers.COPPER, 3, -2.4F)));
    }

    @Redirect(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=popped_chorus_fruit")), at = @At(value = "NEW", args = "class=net/minecraft/world/item/Item", ordinal = 0))
    private static Item matchaflavoured$overridePoppedChorusFruit(Item.Properties props) {
        return new MFFoodItem(props.food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.5F).effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 60, 2), 1.0F).build()), SoundEvents.GENERIC_EAT, 1.6F, 2, FoodItem.Type.MISC);
    }
}


