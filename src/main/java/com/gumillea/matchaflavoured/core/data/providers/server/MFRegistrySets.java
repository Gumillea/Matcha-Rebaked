package com.gumillea.matchaflavoured.core.data.providers.server;

import com.gumillea.matchaflavoured.MatchaFlavoured;
import com.gumillea.matchaflavoured.core.reg.MFJukeboxSongs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class MFRegistrySets extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.JUKEBOX_SONG, MFJukeboxSongs::bootstrap);

    public MFRegistrySets(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(MatchaFlavoured.MODID));
    }
}