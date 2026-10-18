package com.gumillea.matchaflavoured.core.reg;

import com.gumillea.inlandport.core.util.helpers.reg.SoundEventHelper;
import com.gumillea.matchaflavoured.MatchaFlavoured;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MFSoundEvents {
    public static final SoundEventHelper HELPER = new SoundEventHelper(MatchaFlavoured.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> CRETE = HELPER.regRecordEvent("crete");
    public static final DeferredHolder<SoundEvent, SoundEvent> ELEGY_OF_THE_ABBEY = HELPER.regRecordEvent("elegy_of_the_abbey");
    public static final DeferredHolder<SoundEvent, SoundEvent> GOLDEN = HELPER.regRecordEvent("golden");
    public static final DeferredHolder<SoundEvent, SoundEvent> LABYRINTHINE = HELPER.regRecordEvent("labyrinthine");
    public static final DeferredHolder<SoundEvent, SoundEvent> MALACHITE = HELPER.regRecordEvent("malachite");
}
