package com.gumillea.matchaflavoured.core.data.providers.client;

import com.gumillea.inlandport.core.data.providers.client.IPItemModelProvider;
import com.gumillea.inlandport.core.util.utils.RegUtil;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.core.reg.MFBlocks;
import com.gumillea.matchaflavoured.core.reg.client.MFItemProperties;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MFItemModelProvider extends IPItemModelProvider {

    public MFItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, MatchaFlavoured.MODID, helper);
    }

    @Override
    public void overrideModels() {
        addItem(MFBlocks.BEACON_KINDLING.get().asItem());
        addSecretItems();
    }

    public void addSecretItems() {
        for(Item item : MFItemProperties.SECRET_ITEMS) {
            ResourceLocation baseName = RegUtil.key(item);
            String path = baseName.getPath();

            ItemModelBuilder builder = withExistingParent(path, "item/generated").texture("layer0", modLoc("item/" + path));
            builder.override().predicate(MFItemProperties.SECRET, 1).model(getExistingFile(modLoc("item/secret_meal"))).end();
        }

    }
}
