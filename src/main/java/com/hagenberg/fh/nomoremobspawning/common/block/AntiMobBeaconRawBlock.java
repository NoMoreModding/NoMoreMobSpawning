package com.hagenberg.fh.nomoremobspawning.common.block;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import com.hagenberg.fh.nomoremobspawning.core.init.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

public class AntiMobBeaconRawBlock extends Block {
    public AntiMobBeaconRawBlock (Properties properties){
        super(properties);
    }

    @SuppressWarnings( "deprecation" )
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.inventory.getCurrentItem().getItem() == Items.WATER_BUCKET){
          worldIn.removeBlock(pos , /*BlockRegistry.ANTI_MOB_BEACON_RAW.get()*/ false);

            player.inventory.setInventorySlotContents(player.inventory.currentItem,new ItemStack(Items.BUCKET));
            return ActionResultType.CONSUME;
        }
        return ActionResultType.PASS;
    }
}
