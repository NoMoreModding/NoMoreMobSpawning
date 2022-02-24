package com.hagenberg.fh.nomoremobspawning.utility;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import com.hagenberg.fh.nomoremobspawning.common.particles.RingParticle;
import com.hagenberg.fh.nomoremobspawning.core.init.ParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NoMoreMobSpawing.Mod_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticle(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ParticleRegistry.RING_PARTICLE.get(), RingParticle.Factory::new);
    }
}
