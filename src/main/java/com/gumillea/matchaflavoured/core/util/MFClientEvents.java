package com.gumillea.matchaflavoured.core.util;

import com.gumillea.inlandport.core.util.helpers.AutoDataGeneHelper;
import com.gumillea.inlandport.core.util.utils.RegUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.MatchaFlavouredConfig;
import com.gumillea.matchaflavoured.common.client.WardingShieldRender;
import com.gumillea.matchaflavoured.common.item.MFFoodItem;
import com.gumillea.matchaflavoured.common.item.WardingShieldItem;
import com.gumillea.matchaflavoured.core.reg.MFEffects;
import com.gumillea.matchaflavoured.core.reg.MFItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import snownee.jade.impl.Tooltip;
import snownee.jade.impl.config.WailaConfig;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@EventBusSubscriber(modid = MatchaFlavoured.MODID, value = Dist.CLIENT)
public class MFClientEvents {

    @SubscribeEvent
    public static void onRenderGuiLayer(RenderGuiLayerEvent.Pre event) {
        if (MatchaFlavouredConfig.Startup.DEFINE_HUNGER_SYSTEM.get() == 0 && event.getName().equals(VanillaGuiLayers.FOOD_LEVEL)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            private WardingShieldRender renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    Minecraft mc = Minecraft.getInstance();
                    renderer = new WardingShieldRender(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels());
                }
                return this.renderer;
            }
        }, MFItems.WARDING_SHIELD.get());
    }

    @SubscribeEvent
    public static void onEffectItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();
        if (!(stack.getItem() instanceof MFFoodItem) && stack.getComponents().has(DataComponents.FOOD) && MatchaFlavouredConfig.Startup.DEFINE_HUNGER_SYSTEM.get() == 0) {
            appendHoverText(stack, tooltip);
        }
    }

    public static void appendHoverText(ItemStack stack, List<Component> components) {
        FoodProperties food = stack.get(DataComponents.FOOD);

        if (food != null) {
            float hAmount = Math.round(food.nutrition() * food.saturation());

            if (hAmount <= 0) return;

            float hearts = Math.round(hAmount / 10.0F);

            if (hearts > 20) hearts = 20;
            if (hearts < 1) hearts = 1;

            MutableComponent tooltip = Component.literal(countingNormalHearts(hearts / 2.0F)).withStyle(ChatFormatting.RED);

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

            components.add(tooltip);
        }

        MFClientEvents.addEffectTip(stack, components::add);
    }

    private static String countingNormalHearts(float hearts) {
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

    private static String countingEffectHearts(int amp) {
        int hearts = amp * 2;
        if (hearts > 5) {
            return "❤x" + hearts;
        }

        return "❤".repeat(hearts);
    }

    private static String format(float amount) {
        if (amount == (int) amount) {
            return String.valueOf((int) amount);
        }

        return String.valueOf(amount);
    }

    public static void addEffectTip(ItemStack stack, Consumer<Component> consumer) {
        FoodProperties properties = stack.get(DataComponents.FOOD);
        if (properties != null && !properties.effects().isEmpty()) {
            for(FoodProperties.PossibleEffect possibleEffect : properties.effects()) {
                MobEffectInstance effect = possibleEffect.effect();
                Holder<MobEffect> holder = effect.getEffect();
                float probability = possibleEffect.probability();
                MutableComponent component;
                int color = 0x5555FF;

                if (MAP.containsKey(holder)) {
                    EffectToolTips toolTips =  MAP.get(holder);

                    component = Component.literal(toolTips.getPrefix());
                    component.append(Component.translatable(effect.getDescriptionId()));

                    color = toolTips.getColor();

                } else {
                    component = Component.translatable(effect.getDescriptionId()).withStyle(holder.value().getCategory().getTooltipFormatting());
                }

                if (holder.is(MobEffects.ABSORPTION) || holder.is(MobEffects.HEALTH_BOOST)) continue;

                int amp = effect.getAmplifier();
                if (amp > 0) {
                    component = Component.translatable("potion.withAmplifier", component, amp < 10 ? Component.translatable("potion.potency." + amp) : Component.literal("X"));
                }

                if (!effect.endsWithin(20)) {
                    component = Component.translatable("potion.withDuration", component, MobEffectUtil.formatDuration(effect, 1.0F, 20.0F));
                }

                if (probability < 1.0F) {
                    component.append(Component.literal(" (" + Math.round(probability * 100.0F) + "%)"));
                }

                consumer.accept(component.withColor(color));
            }

        }
    }

    public static final Map<Holder<MobEffect>, EffectToolTips> MAP;

    static {
        MAP = Map.ofEntries(
                Map.entry(MobEffects.LEVITATION, new EffectToolTips("🕴", 0xae6cbc)),
                Map.entry(MobEffects.WEAKNESS, new EffectToolTips("🥀", 0xb6a09a)),
                Map.entry(MobEffects.POISON, new EffectToolTips("💀", 0x48a53b)),
                Map.entry(MobEffects.HUNGER, new EffectToolTips("😭", 0x008080)),
                Map.entry(MobEffects.CONFUSION, new EffectToolTips("🤢", 0x6F8564)),
                Map.entry(MobEffects.WITHER, new EffectToolTips("💔", 0xFAFAFA)),

                Map.entry(MobEffects.REGENERATION, new EffectToolTips("✝", 0xe85e8f)),
                Map.entry(MobEffects.DAMAGE_RESISTANCE, new EffectToolTips("🛡", 0x8888ff)),
                Map.entry(MobEffects.DAMAGE_BOOST, new EffectToolTips("🗡", 0xE65F33)),
                Map.entry(MobEffects.FIRE_RESISTANCE, new EffectToolTips("🔥", 0xffaa00)),
                Map.entry(MobEffects.NIGHT_VISION, new EffectToolTips("👁", 0x55ff55)),
                Map.entry(MobEffects.INVISIBILITY, new EffectToolTips("👻", 0x92b7ce)),
                Map.entry(MobEffects.DIG_SPEED, new EffectToolTips("⛏", 0xffff55)),
                Map.entry(MobEffects.MOVEMENT_SPEED, new EffectToolTips("👢", 0x37cafb)),
                Map.entry(MobEffects.WATER_BREATHING, new EffectToolTips("🫧", 0x7ec9e7)),
                Map.entry(MobEffects.CONDUIT_POWER, new EffectToolTips("🐟", 0x7ec9e7)),
                Map.entry(MobEffects.LUCK, new EffectToolTips("🍀", 0x55FF55)),
                Map.entry(MFEffects.AURA, new EffectToolTips("☽", 0x0FFFA3)),
                Map.entry(MFEffects.EXALTATION, new EffectToolTips("☀", 0x88e6c4))
        );
    }

    public record EffectToolTips(String prefix, int color) {

        public String getPrefix() {
            return prefix + " ";
        }

        public int getColor() {
            return color;
        }

    }
}
