package com.hagenberg.fh.nomoremobspawning.common.block;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import com.hagenberg.fh.nomoremobspawning.core.init.BlockRegistry;
import com.hagenberg.fh.nomoremobspawning.core.init.SoundRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import org.lwjgl.system.CallbackI;

public class AntiMobBeaconRawBlock extends Block {

    private static final VoxelShape BASE = Block.makeCuboidShape(1,0,1,15,5,15);
    private static final VoxelShape UPPER = Block.makeCuboidShape(2,5,2,14,16,14);

    public static final VoxelShape WHOLE = VoxelShapes.or(BASE,UPPER);


    public AntiMobBeaconRawBlock (Properties properties){
        super(properties);
    }

    @SuppressWarnings( "deprecation" )
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.inventory.getCurrentItem().getItem() == Items.WATER_BUCKET){
            if (!worldIn.isRemote()){
                worldIn.setBlockState(pos , BlockRegistry.ANTI_MOB_BEACON.get().getDefaultState());
            }

          if (!player.isCreative()){
              player.inventory.setInventorySlotContents(player.inventory.currentItem,new ItemStack(Items.BUCKET));
          }

            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);

          return ActionResultType.CONSUME;
        }
        if(player.inventory.getCurrentItem().getItem() == Items.LAVA_BUCKET){
            worldIn.playSound((PlayerEntity) null,pos, SoundRegistry.WRONGLYPLACED.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return WHOLE;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return AntiMobBeaconBlock.LIGHTLEVEL_UNFILLED;
    }
}
