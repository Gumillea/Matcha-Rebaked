package com.gumillea.matchaflavoured.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class ClayFetishItem extends BlockItem {

    public ClayFetishItem(Block block, Properties properties) {
        super(block, properties.rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable("block.matchaflavoured.clay_fetish");
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag flag) {
        MutableComponent tooltip = Component.translatable(this.getDescriptionId().replace("clay_fetish_", "clay_fetish.")).withStyle(ChatFormatting.GRAY);
        components.add(tooltip);
    }

}
