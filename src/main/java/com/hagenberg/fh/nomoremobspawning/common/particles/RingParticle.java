package com.hagenberg.fh.nomoremobspawning.common.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class RingParticle extends SpriteTexturedParticle {

    protected RingParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);

        this.setSize(0.15f,0.15f);
        this.motionX *= 0;
        this.motionY = 0.03f;
        this.motionZ *= 0;
        this.maxAge = 50;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if(this.maxAge-- <= 0) {
            this.setExpired();
        } else {
            this.move(this.motionX,this.motionY,this.motionZ);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {

            this.spriteSet = sprite;
        }

        @Nullable
        @Override
        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            RingParticle ringParticle = new RingParticle(worldIn,x,y,z,xSpeed,ySpeed,zSpeed);

            ringParticle.setColor(1.0f,1.0f,1.0f);
            ringParticle.selectSpriteWithAge(spriteSet);
            return ringParticle;
        }
    }
}
