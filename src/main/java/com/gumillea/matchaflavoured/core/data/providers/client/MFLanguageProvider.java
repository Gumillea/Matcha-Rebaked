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

        addJukeboxSong(MUSIC_DISC_GOLDEN, "Ciren - Golden");
        addJukeboxSong(MUSIC_DISC_CRETE, "DeBlezyBestie - Crete");
        addJukeboxSong(MUSIC_DISC_MALACHITE, "DeBlezyBestie - Malachite");
        addJukeboxSong(MUSIC_DISC_ELEGY_OF_THE_ABBEY, "DeBlezyBestie - Elegy of The Abby");
        addJukeboxSong(MUSIC_DISC_LABYRINTHINE, "Lana Raine - Labyrinthine");

        addSmithingTemplate(GENERIC_UPGRADE_SMITHING_TEMPLATE.get(), "Generic Upgrade", "Base-metal Equipment", "Any Alloy", "Add armor weapon or tool", "Add Alloy");

        add("item.matchaflavoured.cooking_recipe", "Cooking Recipe");
        add("item.matchaflavoured.secret_food", "Secret Food");
        add("block.matchaflavoured.clay_fetish", "Clay Fetish");
        add("block.matchaflavoured.clay_fetish.lament", "Lament");
        add("block.matchaflavoured.clay_fetish.rejoice", "Rejoice");
        add(SackBlockEntity.KEY, "Sack");
        add(SecretRecipeItem.KEY, "Recipe Unlocked: ");
    }

}
