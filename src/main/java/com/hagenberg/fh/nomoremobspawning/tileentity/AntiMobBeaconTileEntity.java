package com.hagenberg.fh.nomoremobspawning.tileentity;

import com.hagenberg.fh.nomoremobspawning.common.block.AntiMobBeaconBlock;
import com.hagenberg.fh.nomoremobspawning.core.init.TileEntityRegistry;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class AntiMobBeaconTileEntity extends TileEntity implements ITickableTileEntity {


    private int level;

    public AntiMobBeaconTileEntity() {
        super(TileEntityRegistry.ANTIMOBBEACON_TILE_ENTTY.get());
    }

    @Override
    public void tick() {

        level = this.getBlockState().get(AntiMobBeaconBlock.LEVEL);

        if (this.world.getGameTime() % 80L == 0L && level > 0){
            this.addEffectsToMobs();
        }
    }

    private void addEffectsToMobs(){
        if (!this.world.isRemote) {
            double d0 = (double)(this.level * 10 + 10);
            int i = 0;

            int j = (9 + this.level * 2) * 20;

            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.pos)).grow(d0).expand(0.0D, (double)this.world.getHeight(), 0.0D);
            List<MobEntity> list = this.world.getEntitiesWithinAABB(MobEntity.class, axisalignedbb);

            for(MobEntity mobentity : list) {
                mobentity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, j, i, true, true));
            }

        }
    }

    public void remove() {
        super.remove();
    }

}
