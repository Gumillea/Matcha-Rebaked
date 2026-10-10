package com.gumillea.matchaflavoured.common.item;

import com.gumillea.inlandport.common.item.FoodItem;
import com.gumillea.inlandport.core.util.utils.AttrUtil;
import com.gumillea.matchaflavoured.MatchaFlavouredConfig;
import com.gumillea.matchaflavoured.core.reg.MFAttributes;
import com.gumillea.matchaflavoured.core.util.MFClientEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.level.NoteBlockEvent;

import java.util.List;

public class MFFoodItem extends FoodItem {

    private final float hAmount;

    public MFFoodItem(Properties properties, SoundEvent sound, Object duration, float hAmount, Type type) {
        super(properties, sound, duration, type, null, null, hAmount);
        this.hAmount = hAmount;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if (MatchaFlavouredConfig.Startup.DEFINE_HUNGER_SYSTEM.get() != 2 && entity instanceof Player player) {
            if (AttrUtil.has(player, MFAttributes.FOOD_REGEN)) {
                double current = player.getAttribute(MFAttributes.FOOD_REGEN).getBaseValue();
                player.getAttribute(MFAttributes.FOOD_REGEN).setBaseValue(current + hAmount);
            }
        }

        return super.finishUsingItem(itemStack, level, entity);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag flag) {

        if (MatchaFlavouredConfig.Startup.DEFINE_HUNGER_SYSTEM.get() != 1 && hAmount > 0) {
            float hearts = hAmount / 2.0F;
            MutableComponent tooltip = Component.literal(countingNormalHearts(hearts)).withStyle(ChatFormatting.RED);

            FoodProperties food = stack.get(DataComponents.FOOD);
            if (food != null) {
                for (FoodProperties.PossibleEffect possibleEffect : food.effects()) {
                    MobEffectInstance inst = possibleEffect.effect();

                    if (inst.is(MobEffects.ABSORPTION)) {
                        int blueHearts = inst.getAmplifier() + 1;
                        tooltip.append(Component.literal(" + ").withStyle(ChatFormatting.WHITE));
                        tooltip.append(Component.literal(countingEffectHearts(blueHearts)).withStyle(ChatFormatting.AQUA));
                        if (!inst.endsWithin(20)) {
                            tooltip.append(Component.literal(" (").withStyle(ChatFormatting.AQUA));
                            tooltip.append(MobEffectUtil.formatDuration(inst, 1.0F, 20.0F).copy().withStyle(ChatFormatting.AQUA));
                            tooltip.append(Component.literal(")").withStyle(ChatFormatting.AQUA));
                        }
                    }

                    if (inst.is(MobEffects.HEALTH_BOOST)) {
                        int greyHearts = inst.getAmplifier() + 1;
                        tooltip.append(Component.literal(" + ").withStyle(ChatFormatting.WHITE));
                        tooltip.append(Component.literal(countingEffectHearts(greyHearts)).withStyle(ChatFormatting.GRAY));
                        if (!inst.endsWithin(20)) {
                            tooltip.append(Component.literal(" (").withStyle(ChatFormatting.GRAY));
                            tooltip.append(MobEffectUtil.formatDuration(inst, 1.0F, 20.0F).copy().withStyle(ChatFormatting.GRAY));
                            tooltip.append(Component.literal(")").withStyle(ChatFormatting.GRAY));
                        }
                    }
                }
            }

            components.add(tooltip);
        }

        MFClientEvents.addEffectTip(stack, components::add);
    }

    private String countingNormalHearts(float hearts) {
        if (hearts > 5) {
            return "❤x" + format(hearts);
        }

        int fullHearts = (int) hearts;
        String text = "❤".repeat(fullHearts);

        if (hearts - fullHearts >= 0.5F) {
            text += "❣";
        }

        return text;
    }

    private String countingEffectHearts(int amp) {
        int hearts = amp * 2;
        if (hearts > 5) {
            return "❤x" + hearts;
        }

        return "❤".repeat(hearts);
    }

    private String format(float amount) {
        if (amount == (int) amount) {
            return String.valueOf((int) amount);
        }

        return String.valueOf(amount);
    }

}
