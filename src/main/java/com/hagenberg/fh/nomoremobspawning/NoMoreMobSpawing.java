package com.hagenberg.fh.nomoremobspawning;

import com.hagenberg.fh.nomoremobspawning.core.init.BlockRegistry;
import com.hagenberg.fh.nomoremobspawning.core.init.ItemRegistry;
import com.hagenberg.fh.nomoremobspawning.core.init.SoundRegistry;
import com.hagenberg.fh.nomoremobspawning.core.init.TileEntityRegistry;
import config.Config;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NoMoreMobSpawing.Mod_ID)
public class NoMoreMobSpawing
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String Mod_ID = "nomoremobspawning";


    public NoMoreMobSpawing() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.config);

        Config.loadConfig(Config.config, FMLPaths.CONFIGDIR.get().resolve("nomoremobspawning-config.toml").toString());
        LOGGER.error(FMLPaths.CONFIGDIR.get().toString());
        // Register the setup method for modloading
       bus.addListener(this::setup);

        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        SoundRegistry.SOUNDEVENTS.register(bus);
        TileEntityRegistry.TILE_ENTITIES.register(bus);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

}
