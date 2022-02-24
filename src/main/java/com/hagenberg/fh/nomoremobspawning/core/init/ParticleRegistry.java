package com.hagenberg.fh.nomoremobspawning.core.init;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NoMoreMobSpawing.Mod_ID);

    public static final RegistryObject<BasicParticleType> RING_PARTICLE = PARTICLES.register("ring_particle" , () -> new BasicParticleType(false));
}
