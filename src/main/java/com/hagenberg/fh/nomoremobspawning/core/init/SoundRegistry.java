package com.hagenberg.fh.nomoremobspawning.core.init;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDEVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NoMoreMobSpawing.Mod_ID);

    public static final RegistryObject<SoundEvent> WRONGLYPLACED = SOUNDEVENTS.register("wrongly_placed_lava",()-> new SoundEvent(new ResourceLocation("nomoremobspawning","wrongly_placed_lava")));

}
