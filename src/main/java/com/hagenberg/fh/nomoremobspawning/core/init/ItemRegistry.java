package com.hagenberg.fh.nomoremobspawning.core.init;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NoMoreMobSpawing.Mod_ID);


    public static final RegistryObject<BlockItem> ANTI_MOB_BEACON_RAW = ITEMS.register("anti_mob_beacon_raw",()-> new BlockItem(BlockRegistry.ANTI_MOB_BEACON_RAW.get(),new Item.Properties().group(ItemGroup.DECORATIONS)));

}
