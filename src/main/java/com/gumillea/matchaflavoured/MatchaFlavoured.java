package com.gumillea.matchaflavoured;

import com.gumillea.inlandport.core.util.helpers.IPClientHelper;
import com.gumillea.inlandport.core.util.utils.IPUtil;
import com.gumillea.matchaflavoured.core.data.modifiers.MFLootTableModifierProvider;
import com.gumillea.matchaflavoured.core.data.providers.client.MFBlockStateProvider;
import com.gumillea.matchaflavoured.core.data.providers.client.MFItemModelProvider;
import com.gumillea.matchaflavoured.core.data.providers.client.MFLanguageProvider;
import com.gumillea.matchaflavoured.core.data.providers.client.MFSpriteSourceProvider;
import com.gumillea.matchaflavoured.core.data.providers.server.*;
import com.gumillea.matchaflavoured.core.reg.*;
import com.gumillea.matchaflavoured.core.reg.client.MFItemProperties;
import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(MatchaFlavoured.MODID)
public class MatchaFlavoured {
    public static final String MODID = "matchaflavoured";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MatchaFlavoured(IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.STARTUP, MatchaFlavouredConfig.STARTUP_SPEC);
        container.registerConfig(ModConfig.Type.COMMON, MatchaFlavouredConfig.COMMON_SPEC);

        MFBlocks.HELPER.register(bus);
        MFBlockEntityTypes.HELPER.register(bus);
        MFItems.HELPER.register(bus);
        MFEffects.HELPER.register(bus);
        MFAttributes.HELPER.register(bus);
        MFTiers.HELPER.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);
    }

    public static ResourceLocation loc(String path) {
        return IPUtil.loc(MODID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MFItemProperties.registerItemProperties();
        IPClientHelper.setCutout(MFBlocks.BEACON_KINDLING.get());
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean server = event.includeServer();
        MFBlockTagsProvider blockTagsProvider = new MFBlockTagsProvider(output, provider, helper);
        generator.addProvider(server, blockTagsProvider);
        generator.addProvider(server, new MFItemTagsProvider(output, provider, blockTagsProvider.contentsGetter()));
        generator.addProvider(server, new MFBiomeTagsProvider(output, provider, helper));

        generator.addProvider(server, new MFRecipeProvider(output, provider));
        generator.addProvider(server, new MFLootTableProvider(output, provider));
        generator.addProvider(server, new MFDataMapProvider(output, provider));

        generator.addProvider(server, new MFLootTableModifierProvider(output, provider));

        boolean client = event.includeClient();
        generator.addProvider(client, new MFBlockStateProvider(output, helper));
        generator.addProvider(client, new MFItemModelProvider(output, helper));
        generator.addProvider(client, new MFLanguageProvider(output));
        generator.addProvider(client, new MFSpriteSourceProvider(output, provider, helper));

    }
}
