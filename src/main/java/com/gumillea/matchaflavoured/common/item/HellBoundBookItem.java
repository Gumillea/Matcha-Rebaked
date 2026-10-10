package com.gumillea.matchaflavoured.common.item;

import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class HellBoundBookItem extends EnchantedBookItem {

    public HellBoundBookItem(Properties properties) {
        super(properties);
    }

    public static ItemStack createForEnchantment(EnchantmentInstance... enchantments) {
        ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK);
        for (EnchantmentInstance inst : enchantments) {
            itemstack.enchant(inst.enchantment, inst.level);
        }
        return itemstack;
    }

}
