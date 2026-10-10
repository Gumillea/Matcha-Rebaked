package com.gumillea.matchaflavoured.core.reg.client;

import com.gumillea.inlandport.core.util.utils.IPUtil;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.item.SecretRecipeItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.fml.loading.FMLEnvironment;

import java.util.List;

import static com.gumillea.matchaflavoured.core.reg.MFItems.*;

public class MFItemProperties {

    public final static ResourceLocation SECRET = MatchaFlavoured.loc("secret");

    public static List<Item> SECRET_ITEMS = List.of(
            CHORUS_MOCHI.get(),
            WARPED_FUNGUS_STROGANOFF.get(),
            SWEET_BERRY_TOAST.get(),
            GNOCCHI.get()
    );

    public static void registerItemProperties() {
        if (!FMLEnvironment.dist.isClient()) return;
        ItemProperties.register(WARDING_SHIELD.get(), IPUtil.mcLoc("blocking"), (stack, level, entity, s) -> (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) ? 1.0F : 0.0F);

        for (Item item : SECRET_ITEMS) {
            ItemProperties.register(item, SECRET, (stack, level, entity, s) -> (entity != null && !SecretRecipeItem.hasTag(entity, stack.getItem())) ? 1.0F : 0.0F);
        }

    }
}
