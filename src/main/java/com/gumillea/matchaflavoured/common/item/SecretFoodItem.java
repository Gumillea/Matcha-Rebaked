package com.gumillea.matchaflavoured.common.item;

import com.gumillea.inlandport.common.item.FoodItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.loading.FMLEnvironment;

import java.util.List;

public class SecretFoodItem extends MFFoodItem {

    public SecretFoodItem(Properties properties, float hAmount, Type type) {
        super(properties, SoundEvents.GENERIC_EAT, FoodItem.getDefaultDuration(), hAmount, type);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();

        if (SecretRecipeItem.hasTag(player, item)) super.use(level, player, hand);

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (FMLEnvironment.dist.isClient()) {
            Player player = Minecraft.getInstance().player;
            if (player != null && SecretRecipeItem.hasTag(player, stack.getItem())) {
                return Component.translatable(getDescriptionId(stack));
            }
        }
        return Component.translatable("item.matchaflavoured.secret_food");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        if (FMLEnvironment.dist.isClient()) {
            Player player = Minecraft.getInstance().player;
            if (player != null && SecretRecipeItem.hasTag(player, stack.getItem())) {
                super.appendHoverText(stack, context, components, flag);
            }
        }
    }

}
