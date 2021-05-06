package com.hagenberg.fh.nomoremobspawning.core.init;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import com.hagenberg.fh.nomoremobspawning.tileentity.AntiMobBeaconTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegistry {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NoMoreMobSpawing.Mod_ID);

    public static final RegistryObject<TileEntityType<AntiMobBeaconTileEntity>> ANTIMOBBEACON_TILE_ENTITY =
            TILE_ENTITIES.register("antimobbeacon_tile_entity", () -> TileEntityType.Builder.create(
                    AntiMobBeaconTileEntity::new, BlockRegistry.ANTI_MOB_BEACON.get()).build(null)
            );
}

