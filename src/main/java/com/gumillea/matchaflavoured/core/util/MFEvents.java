package com.gumillea.matchaflavoured.core.util;

import com.gumillea.inlandport.core.util.helpers.AutoDataGeneHelper;
import com.gumillea.inlandport.core.util.utils.*;
import com.gumillea.inlandport.test.reg.IPAttributes;
import com.gumillea.inlandport.test.reg.IPNetworkHandler;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.MatchaFlavouredConfig;
import com.gumillea.matchaflavoured.common.effect.ExaltationEffect;
import com.gumillea.matchaflavoured.common.item.CrystalHeartItem;
import com.gumillea.matchaflavoured.common.item.SecretRecipeItem;
import com.gumillea.matchaflavoured.core.data.tags.MFItemTags;
import com.gumillea.matchaflavoured.core.reg.MFAttributes;
import com.gumillea.matchaflavoured.core.reg.MFEffects;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import java.nio.file.Path;
import java.util.*;

import static com.gumillea.inlandport.core.util.helpers.reg.CreativeTabHelper.*;
import static com.gumillea.inlandport.core.util.utils.EventUtil.trade;
import static com.gumillea.matchaflavoured.core.reg.MFItems.*;
import static com.gumillea.matchaflavoured.core.reg.MFBlocks.*;

@EventBusSubscriber(modid = MatchaFlavoured.MODID)
public class MFEvents {

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        var server = event.getServer();
        GameRules gameRules = server.getGameRules();

        if (MatchaFlavouredConfig.Common.ENABLE_KEEP_INVENTORY_ALWAYS.get() && !gameRules.getRule(GameRules.RULE_KEEPINVENTORY).get()) {
            gameRules.getRule(GameRules.RULE_KEEPINVENTORY).set(true, server);
        }

        if (!MatchaFlavouredConfig.Common.ENABLE_NATURAL_REGEN.get() && gameRules.getRule(GameRules.RULE_KEEPINVENTORY).get()) {
            gameRules.getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, server);
        }
    }

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            PackLocationInfo info = new PackLocationInfo("matchaflavoured", Component.literal("Matcha Flavoured"), PackSource.BUILT_IN, Optional.empty());
            Path resourcePath = ModList.get().getModFileById(MatchaFlavoured.MODID).getFile().findResource("resourcepacks/matchaflavoured");
            Pack pack = Pack.readMetaAndCreate(info, new PathPackResources.PathResourcesSupplier(resourcePath), PackType.CLIENT_RESOURCES, new PackSelectionConfig(false, Pack.Position.TOP, false));
            event.addRepositorySource(consumer -> consumer.accept(pack));
        }
    }

    @SubscribeEvent
    public static void addAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, MFAttributes.FOOD_REGEN);
        event.getTypes().stream().filter(entityType -> !entityType.is(EntityTypeTags.UNDEAD)).forEach(type -> event.add(type, MFAttributes.WARDING_POWER));
    }


    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        MinecraftServer server = event.getPlayerList().getServer();
        RecipeManager manager = server.getRecipeManager();
        RegistryAccess access = server.registryAccess();

        manager.replaceRecipes(manager.getRecipes().stream().filter(holder -> !shouldRemoved(holder, access)).toList());
    }

    private static boolean shouldRemoved(RecipeHolder<?> holder, RegistryAccess access) {
        if (holder.value() instanceof SmeltingRecipe smelt) {
            ItemStack result = smelt.getResultItem(access);

            if (Arrays.stream(smelt.getIngredients().getFirst().getItems()).anyMatch(stack -> stack.is(Tags.Items.ORES) && !stack.is(MFItemTags.SMELTING_WHITELIST))) {
                return true;
            }

            if (result.is(MFItemTags.SMELTING_WHITELIST)) return false;

            return result.is(Tags.Items.INGOTS) || result.is(Tags.Items.FOODS_COOKED_FISH) || result.is(Tags.Items.FOODS_COOKED_MEAT);
        }

        if (holder.value() instanceof BlastingRecipe blast) {
            ItemStack result = blast.getResultItem(access);
            if (result.is(Tags.Items.NUGGETS) && !result.is(MFItemTags.SMELTING_WHITELIST)) return true;

            return Arrays.stream(blast.getIngredients().getFirst().getItems()).anyMatch(stack -> stack.is(Tags.Items.ORES) && !stack.is(MFItemTags.BLASTING_WHITELIST));
        }

        return false;
    }

    @SubscribeEvent
    public static void onModifyComponents(ModifyDefaultComponentsEvent event) {
        event.modify(Items.DRIED_KELP, builder -> builder.set(DataComponents.FOOD, new FoodProperties.Builder().nutrition(1).saturationModifier(0.6F).alwaysEdible().fast().effect(() ->new MobEffectInstance(MobEffects.WATER_BREATHING, 200), 1.0F).build()));

        if (MatchaFlavouredConfig.Startup.DEFINE_HUNGER_SYSTEM.get() == 0) {
            List<Item> foods = BuiltInRegistries.ITEM.stream().filter(item -> !AutoDataGeneHelper.isSame(RegUtil.key(item), MatchaFlavoured.MODID) && item.components().has(DataComponents.FOOD)).toList();
            for (Item food : foods) {
                FoodProperties properties = food.components().get(DataComponents.FOOD);
                if (properties != null) {
                    event.modify(food, builder -> builder.set(DataComponents.FOOD, new FoodProperties(properties.nutrition(), properties.saturation(), true, properties.eatSeconds(), properties.usingConvertsTo(), properties.effects())));
                }
            }
        }
    }

    @SubscribeEvent
    public static void buildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        autoInsert(event, MatchaFlavoured.MODID, () ->
                tab(event, add(CreativeModeTabs.BUILDING_BLOCKS, () -> {
                            insertAfter(event, Items.EMERALD_BLOCK, OBOL_BLOCK.get().asItem());
                        }),

                        add(CreativeModeTabs.NATURAL_BLOCKS, () -> {
                            insertAfter(event, Items.DEEPSLATE_LAPIS_ORE, QUARTZ_ORE.get().asItem(), DEEPSLATE_QUARTZ_ORE.get().asItem());
                            insertAfter(event, Items.DEEPSLATE_GOLD_ORE, SILVER_ORE.get().asItem(), DEEPSLATE_SILVER_ORE.get().asItem());
                            insertBefore(event, Items.ANCIENT_DEBRIS, SULFUR_ORE.get().asItem());
                            insertAfter(event, Items.HAY_BLOCK, STRAW_BALE.get().asItem());
                            insertAfter(event, Items.LILY_OF_THE_VALLEY, HYACINTH.get().asItem());
                            insertAfter(event, Items.TORCHFLOWER, CYAN_ROSE.get().asItem());
                        }),

                        add(CreativeModeTabs.FUNCTIONAL_BLOCKS, () -> {
                            insertAfter(event, Items.SOUL_TORCH, PHOSPHOR_TORCH.get().asItem());
                            insertAfter(event, Items.CAMPFIRE, BEACON_KINDLING.get().asItem());
                            insertBefore(event, Items.SHULKER_BOX, SACK.get().asItem());
                            insertBefore(event, Items.BEACON, WARDING_STONE.get().asItem());
                        }),

                        add(CreativeModeTabs.TOOLS_AND_UTILITIES, () -> {
                            insertBefore(event, Items.FLINT_AND_STEEL, TINDER.get());
                            insertBefore(event, Items.SHEARS, COPPER_SHEARS.get());
                            insertAfter(event, Items.SHEARS, STEEL_SHEARS.get());
                            insertAfter(event, Items.IRON_HOE, STEEL_SHOVEL.get(), STEEL_PICKAXE.get(), STEEL_AXE.get(), STEEL_HOE.get());
                            insertAfter(event, Items.DIAMOND_HOE, SHAKUDO_SHOVEL.get(), SHAKUDO_PICKAXE.get(), SHAKUDO_AXE.get(), SHAKUDO_HOE.get(), HEPATIZON_SHOVEL.get(), HEPATIZON_PICKAXE.get(), HEPATIZON_AXE.get(), HEPATIZON_HOE.get(), ELECTRUM_SHOVEL.get(), ELECTRUM_PICKAXE.get(), ELECTRUM_AXE.get(), ELECTRUM_HOE.get());
                            insertAfter(event, Items.LEAD, AMNESTIC.get(), CRYSTAL_HEART.get(), Items.BUNDLE);
                            insertAfter(event, Items.FISHING_ROD, TADPOLE.get(), FROG.get(), AXOLOTL.get());
                            insertAfter(event, Items.WRITABLE_BOOK, APPLICATION.get(), CHORUS_MOCHI_RECIPE.get(), SWEET_BERRY_TOAST_RECIPE.get(), GNOCCHI_RECIPE.get(), WARPED_FUNGUS_STROGANOFF_RECIPE.get());
                        }),

                        add(CreativeModeTabs.COMBAT, () -> {
                            insertAfter(event, Items.IRON_SWORD, STEEL_SWORD.get());
                            insertAfter(event, Items.DIAMOND_SWORD, SHAKUDO_SWORD.get(), HEPATIZON_SWORD.get(), ELECTRUM_SWORD.get());
                            insertAfter(event, Items.IRON_AXE, STEEL_AXE.get());
                            insertAfter(event, Items.DIAMOND_AXE, SHAKUDO_AXE.get(), HEPATIZON_AXE.get(), ELECTRUM_AXE.get());
                            insertBefore(event, Items.SHIELD, NAZAR.get(), WOODEN_CROSS.get());
                            insertAfter(event, Items.SHIELD, WARDING_SHIELD.get());
                            insertBefore(event, Items.TURTLE_HELMET, HEPATIZON_LAUREL.get(), RUBY_CIRCLET.get(), NAZAR_CIRCLET.get());
                            insertAfter(event, Items.TURTLE_HELMET, LAPIS_EARRINGS.get(), AMETHYST_EARRINGS.get(), EMERALD_EARRINGS.get(), DIAMOND_EARRINGS.get() ,AMBER_EARRINGS.get(), OPAL_EARRINGS.get(), TOPAZ_EARRINGS.get());
                            insertAfter(event, Items.IRON_SWORD, WARDING_SWORD.get(), SILVER_SWORD.get());
                            insertArmors(event, Items.LEATHER_BOOTS, COPPER_ARMORS.get());
                            insertArmors(event, Items.IRON_BOOTS, STEEL_ARMORS.get());
                            insertArmors(event, Items.GOLDEN_BOOTS, HEPATIZON_ARMORS.get());
                            insertArmors(event, Items.GOLDEN_BOOTS, SHAKUDO_ARMORS.get());
                            insertArmors(event, Items.DIAMOND_BOOTS, ELECTRUM_ARMORS.get());
                        }),

                        add(CreativeModeTabs.FOOD_AND_DRINKS, () -> {
                            insertAfter(event, Items.APPLE, BAKED_APPLE.get());
                            insertAfter(event, Items.GOLDEN_APPLE, BAKED_GOLDEN_APPLE.get());
                            insertAfter(event, Items.MELON_SLICE, GRILLED_MELON.get(), BAKED_PUMPKIN.get());
                            insertAfter(event, Items.SWEET_BERRIES, SWEET_BERRY_MASH.get());
                            insertAfter(event, Items.GLOW_BERRIES, GLOW_BERRY_MASH.get());
                            insertAfter(event, Items.CHORUS_FRUIT, Items.POPPED_CHORUS_FRUIT);
                            insertAfter(event, Items.CARROT, STEAMED_CARROTS.get());
                            insertAfter(event, Items.GOLDEN_CARROT, STEAMED_GOLDEN_CARROTS.get());
                            insertAfter(event, Items.BEETROOT, TOMATOES.get(), GRILLED_TOMATOES.get());
                            insertBefore(event, Items.DRIED_KELP, BRAISED_WARPED_FUNGUS.get(), BRAISED_CRIMSON_FUNGUS.get(), BRAISED_RED_MUSHROOM.get(), BRAISED_BROWN_MUSHROOM.get());
                            insertAfter(event, Items.DRIED_KELP, FRIED_EGG.get());
                            insertAfter(event, Items.TROPICAL_FISH, HUMPBACK_WHITEFISH.get(), ALASKA_BLACKFISH.get(), SPOONHEAD_SCULPIN.get(), NORTHERN_PIKE.get(), SIBERIAN_STURGEON.get(), STRIPED_PERCH.get(), CRAPPIE.get(), WALLEYE.get(), GAR.get(), MUSKELLUNGE.get(), BLUEGILL.get(), CARP.get(), CATFISH.get(), BASS.get(), MEDITERRANEAN_KILLIFISH.get(), FRESHWATER_PUFFERFISH.get(), LAMPREY.get(), TUNISIAN_BARB.get(), EUROPEAN_EEL.get(), GUPPY.get(), BUJURQUI.get(), PIRANHA.get(), ARMOURED_CATFISH.get(), ARAPAIMA.get(), HERRING.get(), WOLFFISH.get(), SKATE.get(), BLACK_SEABASS.get(), MONKFISH.get(), OPAH.get(), ANCHOVY.get(), SHAD.get(), FLYING_FISH.get(), STURGEON.get(), BLUE_MARLIN.get(), RAINBOW_WRASSE.get(), GURNARD.get(), FLOUNDER.get(), MAHI_MAHI.get(), PAINTED_MORAY.get(), OARFISH.get(), CREAKING_FISH.get(), ECHO_EEL.get(), COOKED_FISH.get(), COOKED_LARGE_FISH.get());
                            insertBefore(event, Items.BREAD, NAAN.get(), ICE_CREAM.get(), CHOCOLATE.get(), CHEESE.get());
                            insertAfter(event, Items.BREAD, BROWNIE.get(), BUNUELO.get(), BRUSCHETTA.get(), CHEESE_PIZZA.get(), MEAT_PIZZA.get(), MUSHROOM_PIZZA.get(), WARPED_PIZZA.get());
                            insertBefore(event, Items.PUMPKIN_PIE, SWEET_BERRY_TOAST.get(), HONIED_FRENCH_TOAST.get(), FRENCH_TOAST.get());
                            insertAfter(event, Items.PUMPKIN_PIE, APPLE_EMPANADA.get(), GOLDEN_APPLE_EMPANADA.get(), CARROT_CUPCAKE.get(), GOLDEN_CARROT_CUPCAKE.get(), SWEET_BERRY_DANISH.get(), GLOW_BERRY_CRUMBLE.get(), CHORUS_MOCHI.get());
                            insertBefore(event, Items.MUSHROOM_STEW, PICKLED_WARPED_FUNGUS.get(), PICKLED_CRIMSON_FUNGUS.get(), PICKLED_RED_MUSHROOMS.get(), PICKLED_BROWN_MUSHROOMS.get(), PICKLED_TOMATOES.get(), PICKLED_POTATOES.get(), PICKLED_GOLDEN_CARROTS.get(), PICKLED_CARROTS.get(), CANNED_GOLDEN_APPLES.get(), CANNED_APPLES.get());
                        }),

                        add(CreativeModeTabs.INGREDIENTS, () -> {
                            insertAfter(event, Items.CHARCOAL, SULFUR_CHUNK.get());
                            insertAfter(event, Items.RAW_GOLD, RAW_SILVER.get(), RAW_DIAMOND.get());
                            insertAfter(event, Items.GOLD_INGOT, SILVER_INGOT.get());
                            insertAfter(event, Items.NETHERITE_SCRAP, CARBON_RICH_IRON.get());
                            insertBefore(event, Items.NETHERITE_INGOT,  SHAKUDO_ALLOY.get(), ELECTRUM_ALLOY.get(), HEPATIZON_ALLOY.get(),STEEL_ALLOY.get());
                            insertAfter(event, Items.AMETHYST_SHARD, AMBER.get(), OPAL.get(), RUBY.get(), TOPAZ.get());
                            insertAfter(event, Items.WHEAT, FLOUR.get(), FLOUR_BAG.get(), DOUGH.get(), SEITAN.get(), OIL.get(), CURRY_STOCK.get(), GREEN_CURRY_STOCK.get(), PANEER_MAKHANI_STOCK.get(), PUMPKIN_CURRY_STOCK.get(), RAMEN_STOCK.get());
                            insertBefore(event, Items.LEATHER, TATTERED_LEATHER.get());
                            insertAfter(event, Items.RABBIT_HIDE, FOX_PELT.get());
                            insertBefore(event, Items.STICK, OBOL.get());
                            insertBefore(event, Items.BONE, FISH_BONES.get());
                            insertBefore(event, Items.WHEAT, STRAW.get(), TALL_DRY_GRASS.get(), SHORT_DRY_GRASS.get());
                            insertBefore(event, Items.SUGAR, MOLASSES.get());
                            insertAfter(event, Items.PAPER, BLUEPRINT.get());
                            insertBefore(event, Items.DRAGON_BREATH, BENZENE.get());
                            insertAfter(event, Items.GUNPOWDER, ESTUS_DUST.get(), STABILIZED_ESTUS.get());
                            insertAfter(event, Items.HEART_OF_THE_SEA, DIVINE_FRAGMENT.get(), DIVINE_FAVOR.get(), NAZAR.get());
                        })
                )
        );
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            AttributeInstance inst = player.getAttribute(Attributes.MAX_HEALTH);
            if (inst == null) return;

            AttributeModifier existing = inst.getModifier(CrystalHeartItem.CRYSTAL_HEART_BOOST);
            if (existing != null) {
                double hearts = existing.amount();
                inst.removeModifier(CrystalHeartItem.CRYSTAL_HEART_BOOST);

                if (hearts > 2) {
                    inst.addPermanentModifier(new AttributeModifier(CrystalHeartItem.CRYSTAL_HEART_BOOST, hearts - 2, AttributeModifier.Operation.ADD_VALUE));
                }

                EntityUtil.playSound(player, SoundEvents.GLASS_BREAK);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        ServerPlayer oldPlayer = (ServerPlayer) event.getOriginal();
        ServerPlayer newPlayer = (ServerPlayer) event.getEntity();

        if (event.isWasDeath()) {
            AttributeInstance oldHearts = newPlayer.getAttribute(Attributes.MAX_HEALTH);
            AttributeInstance newHearts = oldPlayer.getAttribute(Attributes.MAX_HEALTH);

            if (newHearts != null && oldHearts != null) {
                AttributeModifier existing = newHearts.getModifier(CrystalHeartItem.CRYSTAL_HEART_BOOST);
                if (existing != null) {
                    oldHearts.removeModifier(CrystalHeartItem.CRYSTAL_HEART_BOOST);
                    oldHearts.addPermanentModifier(existing);
                    newPlayer.heal(newPlayer.getMaxHealth());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        LivingEntity target = event.getEntity();
        Level level = target.level();
        Entity killer = event.getSource().getEntity();

        if (killer instanceof Player player && target instanceof Monster) {
            double warding = AttrUtil.has(player, MFAttributes.WARDING_POWER) ? MFAttributes.getWardingPower(player) : 0;
            float chance = target.getType().is(EntityTypeTags.UNDEAD) ? (float) Math.min(0.1 * warding + 0.3, 1) : 0.3F;
            if (IPUtil.random(level, chance)) {
                event.getDrops().add(new ItemEntity(target.level(), target.getX(), target.getY(), target.getZ(), RegUtil.stack(ESTUS_DUST.get())));
            }
        }
    }


    @SubscribeEvent
    public static void onItemUsed(LivingEntityUseItemEvent.Finish event) {
        ItemStack stack = event.getItem();
        Entity user = event.getEntity();

        if (!(user instanceof ServerPlayer player)) return;

        tryApplyFoodRegen(stack, player);

        if (stack.is(ItemTags.FISHES)) {
            ItemHandlerHelper.giveItemToPlayer(player, RegUtil.stack(FISH_BONES.get()));
        }
    }

    public static void tryApplyFoodRegen(ItemStack stack, Player player) {
        if (stack.getItem().components().has(DataComponents.FOOD)) {
            FoodProperties food = stack.getItem().components().get(DataComponents.FOOD);
            if (MatchaFlavouredConfig.Startup.DEFINE_HUNGER_SYSTEM.get() != 2) {
                if (AttrUtil.has(player, MFAttributes.FOOD_REGEN)) {
                    double current = player.getAttribute(MFAttributes.FOOD_REGEN).getBaseValue();
                    player.getAttribute(MFAttributes.FOOD_REGEN).setBaseValue(current + toHealingAmount(food));
                }
            }
        }
    }

    public static float toHealingAmount(FoodProperties food) {
        float hAmount = Math.round(food.nutrition() * food.saturation());

        if (hAmount <= 0) return 0;

        float hearts = Math.round(hAmount / 10.0F);

        if (hearts > 20) hearts = 20;
        if (hearts < 1) hearts = 1;

        return hearts;
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        ItemStack stack = event.getItemStack();
        RandomSource random =  level.random;
        InteractionHand hand = event.getHand();
        BlockPos pos = event.getPos();

        EventUtil.useItem(event, s -> s.is(Items.WHEAT), () -> {
                    EntityUtil.playSound(player, SoundEvents.AZALEA_BREAK);

                    for (int i = 0; i < 8; i++) {
                        double movement = 0.3D + random.nextDouble() * 0.1D;
                        double d = (random.nextDouble() - 0.5D) * movement;

                        if (level instanceof ServerLevel server) {
                            server.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), player.getX(), player.getEyeY() - 0.2D, player.getZ(), 0, d, movement, d, 0.5);
                        }
                    }

                    ItemHandlerHelper.giveItemToPlayer(player, RegUtil.stack(STRAW.get()));
                    ItemHandlerHelper.giveItemToPlayer(player, RegUtil.stack(Items.WHEAT_SEEDS, random.nextInt(1, 3)));

                    stack.consume(1, player);
                }
        );
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        EventUtil.entityInteract(event, entity -> entity instanceof Villager villager && villager.getVillagerData().getProfession() != VillagerProfession.NONE && villager.getVillagerData().getProfession() != VillagerProfession.NITWIT, s -> s.is(AMNESTIC.get()), GameEvent.ENTITY_INTERACT, () -> {
            ((Villager)target).setVillagerData(((Villager)target).getVillagerData().setProfession(VillagerProfession.NONE));
            EntityUtil.playSound(target, SoundEvents.ELDER_GUARDIAN_CURSE);
            stack.consume(1, player);
        });
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        Holder<Attribute> wp = MFAttributes.WARDING_POWER;
        Entity entity = event.getEntity();
        Level level = entity.level();

        if (level.isClientSide()) return;

        if (level.getDifficulty() == Difficulty.PEACEFUL) return;

        if (entity instanceof LivingEntity living && AttrUtil.has(living, wp)) {
            double power = MFAttributes.getWardingPower(living);

            if (power > 0 && living.tickCount % 30 == 0) {
                double range = 2 * power;
                wardingEffect(level, entity.blockPosition(), power, range, 2, range);
            }

        }


    }

    public static void wardingEffect(Level level, BlockPos pos, double power, double x, double y, double z) {
        AABB range = IPUtil.box(x, y, z).move(pos);
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, range, target -> target.getType().is(EntityTypeTags.UNDEAD));

        for (LivingEntity target : targets) {
            target.hurt(level.damageSources().magic(), (float) power);

            switch (target) {
                case WitherBoss ignored -> EffectUtil.add(target, MobEffects.HEAL, 1, (int) Math.max(power - 1, 1));
                case WitherSkeleton ignored -> {
                    EffectUtil.add(target, MobEffects.HEAL, 1);
                    EffectUtil.add(target, MobEffects.WEAKNESS, 60);
                }
                default -> EffectUtil.add(target, MobEffects.MOVEMENT_SLOWDOWN, 60, (int) (power - 1));
            }

            if (level instanceof ServerLevel server) {
                server.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, target.getX(), target.getY() + 1.5, target.getZ(), 5, 0.25, 0.5, 0.25, 0.05);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        Holder<Attribute> fr = MFAttributes.FOOD_REGEN;

        if (AttrUtil.has(player, fr)) {
            double power = MFAttributes.getCrystalHeart(player);

            if (player.getAttribute(fr) == null )return;

            if (power > 0 && player.tickCount % 30 == 0) {
                if (player.getHealth() < player.getMaxHealth()) {
                    player.heal(1.0F);
                    player.getAttribute(fr).setBaseValue(Math.max(0, player.getAttribute(fr).getBaseValue() - 1.0D));
                }
            }
        }

        if (level.getDifficulty() == Difficulty.PEACEFUL) return;

        if (!level.isClientSide() && MatchaFlavouredConfig.Startup.DEFINE_HUNGER_SYSTEM.get() == 0) {
            FoodData foodData = player.getFoodData();

            if (player.tickCount % 600 == 0) {
                foodData.setFoodLevel(20);
                foodData.setSaturation(5.0F);
            }

        }
    }

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Remove event) {
        if (event.getEffect().is(MFEffects.EXALTATION)) {
            LivingEntity living = event.getEntity();
            AttributeInstance drAttr = living.getAttribute(IPAttributes.DEBUFF_RESISTANCE);
            AttributeInstance asAttr = living.getAttribute(Attributes.ATTACK_SPEED);

            if (drAttr != null) drAttr.removeModifier(ExaltationEffect.DEBUFF_RESISTANCE);
            if (asAttr != null) asAttr.removeModifier(ExaltationEffect.ATTACK_SPEED);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onVillagerTrades(VillagerTradesEvent event) {
        EventUtil.addTrades(event, VillagerProfession.BUTCHER, 3,
                trade(12, RegUtil.stack(GNOCCHI_RECIPE.get()), 4, 1),
                trade(12, RegUtil.stack(SWEET_BERRY_TOAST_RECIPE.get()), 4, 1)
        );

        EventUtil.addTrades(event, VillagerProfession.BUTCHER, 5,
                trade(12, RegUtil.stack(WARPED_FUNGUS_STROGANOFF_RECIPE.get()), 4, 1),
                trade(12, RegUtil.stack(CHORUS_MOCHI_RECIPE.get()), 4, 1)
        );


        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

        for (int level = 0; level <= 5; level++) {
            List<VillagerTrades.ItemListing> list = trades.get(level);
            if (list == null) continue;

            list.replaceAll(MFEvents::emeraldToObol);
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onWandererTrades(WandererTradesEvent event) {
        EventUtil.addTrades(event, false,
                trade(4, RegUtil.stack(APPLICATION.get()), 16, 1),
                trade(5, RegUtil.stack(APPLICATION.get()), 16, 1),
                trade(6, RegUtil.stack(APPLICATION.get()), 16, 1),
                trade(7, RegUtil.stack(APPLICATION.get()), 16, 1),
                trade(4, RegUtil.stack(APPLICATION.get()), 16, 1),
                trade(5, RegUtil.stack(APPLICATION.get()), 16, 1),
                trade(6, RegUtil.stack(APPLICATION.get()), 16, 1),
                trade(7, RegUtil.stack(APPLICATION.get()), 16, 1)
        );

        EventUtil.addTrades(event, true,
                trade(16, RegUtil.stack(GNOCCHI_RECIPE.get()), 1, 1),
                trade(16, RegUtil.stack(SWEET_BERRY_TOAST_RECIPE.get()), 1, 1),
                trade(16, RegUtil.stack(WARPED_FUNGUS_STROGANOFF_RECIPE.get()), 1, 1),
                trade(16, RegUtil.stack(CHORUS_MOCHI_RECIPE.get()), 1, 1)
        );

        event.getGenericTrades().replaceAll(MFEvents::emeraldToObol);
        event.getRareTrades().replaceAll(MFEvents::emeraldToObol);
    }

    private static VillagerTrades.ItemListing emeraldToObol(VillagerTrades.ItemListing list) {
        return (entity, random) -> {
            MerchantOffer oldOffer = list.getOffer(entity, random);
            if (oldOffer == null) return null;

            ItemCost costA = oldOffer.getItemCostA();
            Optional<ItemCost> costB = oldOffer.getItemCostB();
            ItemStack result = oldOffer.getResult().copy();

            ItemCost newCostA = replace(costA);
            Optional<ItemCost> newCostB = costB.map(MFEvents::replace);

            ItemStack newResult = result.is(Items.EMERALD) ? new ItemStack(OBOL.get(), result.getCount()) : result;

            return new MerchantOffer(newCostA, newCostB, newResult, oldOffer.getUses(), oldOffer.getMaxUses(), oldOffer.getXp(), oldOffer.getPriceMultiplier(), oldOffer.getDemand());
        };
    }

    private static ItemCost replace(ItemCost oldCost) {
        return oldCost.item().is(Tags.Items.GEMS_EMERALD) ? new ItemCost(OBOL, oldCost.count(), oldCost.components(), oldCost.itemStack()) : oldCost;
    }

}
