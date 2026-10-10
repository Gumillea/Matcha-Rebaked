package com.gumillea.matchaflavoured.core.data.providers.client;

import com.gumillea.inlandport.core.data.providers.client.IPLanguageProvider;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.common.block.entity.SackBlockEntity;
import com.gumillea.matchaflavoured.common.item.SecretRecipeItem;

import net.minecraft.data.PackOutput;

import static com.gumillea.matchaflavoured.core.reg.MFItems.*;
import static com.gumillea.matchaflavoured.core.reg.MFBlocks.*;

public class MFLanguageProvider extends IPLanguageProvider {

    public MFLanguageProvider(PackOutput output) {
        super(output, MatchaFlavoured.MODID);
    }

    @Override
    public void addManualTranslations() {
        skipItem(GNOCCHI_RECIPE.get(), SWEET_BERRY_TOAST_RECIPE.get(), WARPED_FUNGUS_STROGANOFF_RECIPE.get(), CHORUS_MOCHI_RECIPE.get());

        add(BUNUELO.get(), "Buñuelo");
        add(OBOL_BLOCK.get(), "Block of Obol");

        add("item.matchaflavoured.cooking_recipe", "Cooking Recipe");
        add("item.matchaflavoured.secret_food", "Secret Food");
        add(SackBlockEntity.KEY, "Sack");
        add(SecretRecipeItem.KEY, "Recipe Unlocked: ");
    }

}
