package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.inlandport.core.util.helpers.reg.SoundEventHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.JukeboxSong;

import static com.gumillea.matchaflavoured.core.reg.MFItems.*;
import static com.gumillea.matchaflavoured.core.reg.MFSoundEvents.*;

public class MFJukeboxSongs {

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        SoundEventHelper.regSong(context, MUSIC_DISC_GOLDEN, GOLDEN, 188, 1);
        SoundEventHelper.regSong(context, MUSIC_DISC_CRETE, CRETE, 82, 0);
        SoundEventHelper.regSong(context, MUSIC_DISC_MALACHITE, MALACHITE, 91, 0);
        SoundEventHelper.regSong(context, MUSIC_DISC_ELEGY_OF_THE_ABBEY, ELEGY_OF_THE_ABBEY, 215, 0);
        SoundEventHelper.regSong(context, MUSIC_DISC_LABYRINTHINE, LABYRINTHINE, 324, 1);
    }
}
