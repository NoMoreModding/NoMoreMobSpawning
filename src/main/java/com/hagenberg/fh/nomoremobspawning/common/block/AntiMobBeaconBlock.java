package com.hagenberg.fh.nomoremobspawning.common.block;

import com.hagenberg.fh.nomoremobspawning.core.init.BlockRegistry;
import com.hagenberg.fh.nomoremobspawning.tileentity.AntiMobBeaconTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.world.NoteBlockEvent;

import javax.annotation.Nullable;


import static com.hagenberg.fh.nomoremobspawning.common.block.AntiMobBeaconRawBlock.WHOLE;

public class AntiMobBeaconBlock extends Block {

    private static final int LIGHTLEVEL_FILLED = 15;
    public static final int LIGHTLEVEL_UNFILLED = 10;


    public static final IntegerProperty LEVEL = IntegerProperty.create("level",0,3);


    public AntiMobBeaconBlock (Properties properties){
        super(properties);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AntiMobBeaconTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(handIn);
        if(stack.isEmpty()){
            return ActionResultType.PASS;
        }
        else{
            int i = state.get(LEVEL);
            if(stack.getItem() == Items.LAVA_BUCKET){
                if(i < 3 && !worldIn.isRemote){
                    if (!player.abilities.isCreativeMode){
                        player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                    }
                    this.setLavaLevel(worldIn, pos, state, i +1);
                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                return ActionResultType.CONSUME;
            }
            else if(stack.getItem() == Items.BUCKET){
                if( i > 0 && !worldIn.isRemote){
                    if(!player.isCreative()){
                        stack.shrink(1);
                        if(stack.isEmpty()){
                            player.setHeldItem(handIn, new ItemStack(Items.LAVA_BUCKET));
                        } else if (!player.inventory.addItemStackToInventory(new ItemStack((Items.LAVA_BUCKET)))) {
                            player.dropItem(new ItemStack(Items.LAVA_BUCKET), false);
                        }
                    }

                    this.setLavaLevel(worldIn,pos,state,state.get(LEVEL)-1);
                    worldIn.playSound((PlayerEntity) null,pos,SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.BLOCKS,1.0F, 1.0F);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    public void setLavaLevel(World world, BlockPos pos, BlockState state, int level){
        world.setBlockState(pos, state.with(LEVEL, Integer.valueOf(MathHelper.clamp(level,0,3))));
//        AntiMobBeaconTileEntity antiMobBeaconTileEntity = (AntiMobBeaconTileEntity) world.getTileEntity(pos);
//        antiMobBeaconTileEntity.setLevel(world.getBlockState(pos).get(LEVEL));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return AntiMobBeaconRawBlock.WHOLE;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if(state.get(LEVEL) > 0){
            return LIGHTLEVEL_FILLED;
        }
        return LIGHTLEVEL_UNFILLED;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        if (state.get(LEVEL) != 0 && !worldIn.isRemote()) {
            worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState().with(FlowingFluidBlock.LEVEL, 8-state.get(LEVEL)*2), Constants.BlockFlags.DEFAULT_AND_RERENDER);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state){
        return true;
    }
}
