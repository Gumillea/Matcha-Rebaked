package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.minecraft.network.chat.Style;

import java.util.function.UnaryOperator;

public class MFRarities {
    public static final EnumProxy<Rarity> AMBER = reg("amber", 0xE58904);
    public static final EnumProxy<Rarity> OPAL = reg("opal", 0xAB85AD);
    public static final EnumProxy<Rarity> RUBY = reg("ruby", 0xAD2828);
    public static final EnumProxy<Rarity> TOPAZ = reg("topaz", 0xF7DA81);

    public static EnumProxy<Rarity> reg(String name, int color) {
       return new EnumProxy<>(Rarity.class, -1, MatchaFlavoured.MODID + ":" + name, (UnaryOperator<Style>) style -> style.withColor(color));
    }

}
