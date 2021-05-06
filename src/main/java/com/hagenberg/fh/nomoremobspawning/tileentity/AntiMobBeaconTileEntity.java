package com.hagenberg.fh.nomoremobspawning.tileentity;

import com.hagenberg.fh.nomoremobspawning.common.block.AntiMobBeaconBlock;
import com.hagenberg.fh.nomoremobspawning.core.init.TileEntityRegistry;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class AntiMobBeaconTileEntity extends TileEntity implements ITickableTileEntity {

    public static long REMOVAL_UPDATE_TICKS = 1;
    public static long EFFECT_UPDATE_TICKS = 80;
    public static int RANGE_MULTIPLIER = 10;
    public static int RANGE_ADD = 10;
    public static int EFFECT_RANGE_ADD = 10;
    public static boolean DO_EFFECTS = true;
    public static boolean REMOVE_BATS = true;
    private int level;

    public AntiMobBeaconTileEntity() {
        super(TileEntityRegistry.ANTIMOBBEACON_TILE_ENTITY.get());
    }

    @Override
    public void tick() {

        level = this.getBlockState().get(AntiMobBeaconBlock.LEVEL);

        if (level > 0){
            if (this.world.getGameTime() % REMOVAL_UPDATE_TICKS == 0L){
                this.removeMobs();
            }
            if (this.world.getGameTime() % EFFECT_UPDATE_TICKS == 0L && DO_EFFECTS){
                this.addEffectToMobs();
            }
        }
    }

    private void removeMobs() {
        if (!this.world.isRemote) {
            double d0 = (double) (this.level * RANGE_MULTIPLIER + RANGE_ADD);

            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.pos)).grow(d0).expand(0.0D, (double) this.world.getHeight(), 0.0D);
            List<MobEntity> list = this.world.getEntitiesWithinAABB(MobEntity.class, axisalignedbb);

            for (MobEntity mobEntity : list) {
                if (mobEntity instanceof IMob){
                    mobEntity.remove();
                }
                if (mobEntity instanceof BatEntity && REMOVE_BATS){
                    mobEntity.remove();
                }

            }
        }
    }

    private void addEffectToMobs() {
        if (!this.world.isRemote) {
            double d0 = (double) (this.level * RANGE_MULTIPLIER + RANGE_ADD + EFFECT_RANGE_ADD);

            int amplifier = 0;
            int j = (9 + this.level * 2) * 20;

            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.pos)).grow(d0).expand(0.0D, (double) this.world.getHeight(), 0.0D);
            List<MobEntity> list = this.world.getEntitiesWithinAABB(MobEntity.class, axisalignedbb);

            for (MobEntity mobEntity : list) {
                if (mobEntity instanceof IMob){
                    if (mobEntity instanceof ZombieEntity || mobEntity instanceof AbstractSkeletonEntity || mobEntity instanceof PhantomEntity){
                        mobEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, j, amplifier, true, true));
                    } else {
                        mobEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, j, amplifier, true, true));
                    }
                }
                if (mobEntity instanceof BatEntity && REMOVE_BATS){
                    mobEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, j, amplifier, true, true));
                }

            }
        }
    }


    public void remove() {
        super.remove();
    }

}
