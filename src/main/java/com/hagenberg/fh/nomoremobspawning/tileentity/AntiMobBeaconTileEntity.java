package com.hagenberg.fh.nomoremobspawning.tileentity;

import com.hagenberg.fh.nomoremobspawning.NoMoreMobSpawing;
import com.hagenberg.fh.nomoremobspawning.common.block.AntiMobBeaconBlock;
import com.hagenberg.fh.nomoremobspawning.core.init.ParticleRegistry;
import com.hagenberg.fh.nomoremobspawning.core.init.TileEntityRegistry;
import config.AMBTileEntityConfig;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.settings.ParticleStatus;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;
import java.util.Random;

public class AntiMobBeaconTileEntity extends TileEntity implements ITickableTileEntity {

    private final long REMOVAL_UPDATE_TICKS = AMBTileEntityConfig.REMOVAL_UPDATE_TICKS.get();
    private final long EFFECT_UPDATE_TICKS = AMBTileEntityConfig.EFFECT_UPDATE_TICKS.get();
    private final int RANGE_MULTIPLIER = AMBTileEntityConfig.RANGE_MULTIPLIER.get();
    private final int RANGE_ADD = AMBTileEntityConfig.RANGE_ADD.get();
    private final int EFFECT_RANGE_ADD = AMBTileEntityConfig.EFFECT_RANGE_ADD.get();
    private final boolean DO_EFFECTS = AMBTileEntityConfig.DO_EFFECTS.get();
    private final boolean REMOVE_BATS = AMBTileEntityConfig.REMOVE_BATS.get();
    private int level = 0;
    private int amountOfRings = 3;
    private int deltaAmountOfRings = 0;
    private long ringUpdateTicks = 100;
    private long ringSpawnOffset = 6;


    public AntiMobBeaconTileEntity() {
        super(TileEntityRegistry.ANTIMOBBEACON_TILE_ENTITY.get());

    }

    @Override
    public void tick() {
        //TODO change level get every tick to maybe when blockstate is changed to improve perf (if optimization is needed)
        level = this.getBlockState().get(AntiMobBeaconBlock.LEVEL);

        if (level > 0) {
            if (this.world.getGameTime() % REMOVAL_UPDATE_TICKS == 0L) {
                this.removeMobs();
            }
            if (this.world.getGameTime() % EFFECT_UPDATE_TICKS == 0L && DO_EFFECTS) {
                this.addEffectToMobs();
            }

            if (this.world.getGameTime() % ringSpawnOffset == 0L && deltaAmountOfRings++ < amountOfRings ){
                spawnParticle(ParticleRegistry.RING_PARTICLE.get(),0.006D);
            }

            if (this.world.getGameTime() % ringUpdateTicks == 0L) {
                deltaAmountOfRings = 0;
            }

            Random random = this.world.getRandom();

            Long randomLong = 12L + Math.round(random.nextDouble()*8);

            if (this.world.getGameTime() % randomLong == 0L) {
                if (Math.round(random.nextDouble() * 4) == 1) {
                    if (level == 3){
                        spawnParticle(ParticleTypes.FLAME, 0.1D);
                    }
                    spawnParticle(ParticleTypes.LAVA,0.005D);
                }
                spawnParticle(ParticleTypes.SMOKE, 0.005D);
                spawnParticle(ParticleTypes.LARGE_SMOKE, 0.005D);


            }
        }
    }

    private void removeMobs() {
        if (!this.world.isRemote) {
            double d0 = (double) (this.level * RANGE_MULTIPLIER + RANGE_ADD);

            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.pos)).grow(d0).expand(0.0D, (double) this.world.getHeight(), 0.0D);
            List<MobEntity> list = this.world.getEntitiesWithinAABB(MobEntity.class, axisalignedbb);

            for (MobEntity mobEntity : list) {
                if (mobEntity instanceof IMob) {
                    mobEntity.remove();
                }
                if (mobEntity instanceof BatEntity && REMOVE_BATS) {
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
                if (mobEntity instanceof IMob) {
                    if (mobEntity instanceof ZombieEntity || mobEntity instanceof AbstractSkeletonEntity || mobEntity instanceof PhantomEntity) {
                        mobEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, j, amplifier, true, true));
                    } else {
                        mobEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, j, amplifier, true, true));
                    }
                }
                if (mobEntity instanceof BatEntity && REMOVE_BATS) {
                    mobEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, j, amplifier, true, true));
                }

            }
        }
    }

    private void spawnParticle(IParticleData particle, double speed){
        Random random = this.world.getRandom();
        this.world.addParticle(particle,
                (double) pos.getX() + 0.5D + random.nextDouble() / 4.0D * (double) (random.nextBoolean() ? 1 : -1),
                (double) pos.getY() + 1.0D,
                (double) pos.getZ() + 0.5D + random.nextDouble() / 4.0D * (double) (random.nextBoolean() ? 1 : -1),
                0.0D, speed, 0.0D);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void remove() {
        super.remove();
    }

}
