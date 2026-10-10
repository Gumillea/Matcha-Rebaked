package com.gumillea.matchaflavoured.common.item;

import com.gumillea.inlandport.core.api.record.IPEntityTagPacket;
import com.gumillea.inlandport.core.util.utils.EntityUtil;
import com.gumillea.inlandport.core.util.utils.IPUtil;
import com.gumillea.inlandport.core.util.utils.RegUtil;
import com.gumillea.inlandport.test.reg.IPNetworkHandler;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Supplier;

public class SecretRecipeItem extends Item {

    public static final int CHORUS_MOCHI = 0xae6cbc;
    public static final int SWEET_BERRY_TOAST = 0xe85e8f;
    public static final int GNOCCHI = 0xe1bc62;
    public static final int WARPED_FUNGUS_STROGANOFF = 0x3cc6c8;
    public static final String KEY = "message." + MatchaFlavoured.MODID + ".recipe_unlocked";
    public static final String GROUP = MatchaFlavoured.MODID + ":secret";

    private final Supplier<Item> dish;
    private final int color;

    public SecretRecipeItem(Properties properties, Supplier<Item> dish, int color) {
        super(properties);
        this.dish = dish;
        this.color = color;
    }

    private String unlockTag() {
        return IPEntityTagPacket.tag(MatchaFlavoured.MODID, RegUtil.path(dish.get()));
    }

    public static String getTag(Item item) {
        return IPEntityTagPacket.tag(MatchaFlavoured.MODID, RegUtil.path(item));
    }

    public static boolean hasTag(LivingEntity living, Item item) {
        return living.getTags().contains(getTag(item));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        String path = RegUtil.path(dish.get());
        if (player.getTags().contains(unlockTag())) return InteractionResultHolder.fail(stack);

        if (!level.isClientSide) {
            RecipeHolder<?> recipe = level.getRecipeManager().byKey(IPUtil.loc(RegUtil.key(dish.get()).getNamespace(), "shapeless/secret/" + path)).orElse(null);
            if (recipe != null) {
                player.addTag(unlockTag());
                player.awardRecipes(List.of(recipe));
                EntityUtil.playSound(player, SoundEvents.PLAYER_LEVELUP);

                MutableComponent component = Component.translatable(KEY);
                component.append(Component.translatable(dish.get().getDescriptionId()).withColor(color));

                player.displayClientMessage(component, true);

                if (player instanceof ServerPlayer serverPlayer) {
                    IPNetworkHandler.sendEntityTagPacket(serverPlayer, unlockTag());
                }

                stack.consume(1, player);
            } else {
                return InteractionResultHolder.fail(stack);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable("item.matchaflavoured.cooking_recipe");
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag flag) {
        MutableComponent tooltip = Component.translatable(dish.get().getDescriptionId()).withColor(color);
        components.add(tooltip);
    }

}
