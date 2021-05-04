package com.hagenberg.fh.nomoremobspawning.core.init;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import com.hagenberg.fh.nomoremobspawning.common.block.AntiMobBeaconBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NoMoreMobSpawing.Mod_ID);

    public static final RegistryObject<Block> ANTI_MOB_BEACON =
            BLOCKS.register("anti_mob_beacon", () -> new AntiMobBeaconBlock(
                    AbstractBlock.Properties.create(Material.IRON)));

}
