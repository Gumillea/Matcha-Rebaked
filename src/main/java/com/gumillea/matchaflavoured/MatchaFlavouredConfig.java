package com.gumillea.matchaflavoured;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class MatchaFlavouredConfig {
    public static final ModConfigSpec STARTUP_SPEC;
    public static final MatchaFlavouredConfig.Startup STARTUP;

    public static final ModConfigSpec COMMON_SPEC;
    public static final MatchaFlavouredConfig.Common COMMON;

    public static final ModConfigSpec CLIENT_SPEC;
    public static final MatchaFlavouredConfig.Client CLIENT;

    static {
        final Pair<MatchaFlavouredConfig.Startup, ModConfigSpec> startupSpecPair = new ModConfigSpec.Builder().configure(MatchaFlavouredConfig.Startup::new);
        STARTUP_SPEC = startupSpecPair.getRight();
        STARTUP = startupSpecPair.getLeft();

        final Pair<MatchaFlavouredConfig.Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(MatchaFlavouredConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();

        Pair<MatchaFlavouredConfig.Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(MatchaFlavouredConfig.Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }

    public static class Startup {
        public static ModConfigSpec.IntValue DEFINE_HUNGER_SYSTEM;
        Startup(ModConfigSpec.Builder builder) {
            builder.push("Game");
            DEFINE_HUNGER_SYSTEM = builder.comment("Defines the usage modes for placeable food (0 - Can only be eaten as an item; 1 - Can be eaten as an item, or placed as a block while sneaking; 2 - Can only be placed as a block).").defineInRange("placeableFoodSetting", 0, 0, 2);
            builder.pop();
        }
    }

    public static class Common {
        public static ModConfigSpec.BooleanValue ENABLE_NATURAL_REGEN;
        public static ModConfigSpec.BooleanValue ENABLE_KEEP_INVENTORY_ALWAYS;
        public static ModConfigSpec.DoubleValue CRYSTAL_HEART_MAXIMUM;
        public static ModConfigSpec.DoubleValue FOOD_REGEN_MAXIMUM;
        Common(ModConfigSpec.Builder builder) {
            builder.push("GameRule");
            ENABLE_NATURAL_REGEN = builder.comment("Allows the ending effect of Exuberant to be changed to restore health.").define("naturalRegen", false);
            ENABLE_KEEP_INVENTORY_ALWAYS = builder.comment("Allows the ending effect of Exuberant to be changed to restore health.").define("keepInventoryAlways", true);
            builder.pop();
            builder.push("Balance");
            CRYSTAL_HEART_MAXIMUM = builder.comment("Allows the ending effect of Exuberant to be changed to restore health.").defineInRange("crystalHeartMaximum", 20.0D, 0, Double.MAX_VALUE);
            FOOD_REGEN_MAXIMUM = builder.comment("Allows the ending effect of Exuberant to be changed to restore health.").defineInRange("foodRegenMaximum", 20.0D, 0, Double.MAX_VALUE);
            builder.pop();
        }
    }

    public static class Client {

        Client(ModConfigSpec.Builder builder) {
        }
    }
}
