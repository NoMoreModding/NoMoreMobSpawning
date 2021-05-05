package com.hagenberg.fh.nomoremobspawning.common.block;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import com.hagenberg.fh.nomoremobspawning.core.init.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import org.lwjgl.system.CallbackI;

public class AntiMobBeaconRawBlock extends Block {
    public AntiMobBeaconRawBlock (Properties properties){
        super(properties);
    }

    @SuppressWarnings( "deprecation" )
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.inventory.getCurrentItem().getItem() == Items.WATER_BUCKET){
          worldIn.setBlockState(pos , BlockRegistry.ANTI_MOB_BEACON.get().getDefaultState());
          if (!player.isCreative()){
              player.inventory.setInventorySlotContents(player.inventory.currentItem,new ItemStack(Items.BUCKET));
          }

          //worldIn.playSound(player,pos, , SoundCategory.BLOCKS,0,1);

          return ActionResultType.CONSUME;
        }
        return ActionResultType.PASS;
    }
}
