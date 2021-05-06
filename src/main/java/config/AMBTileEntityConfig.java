package config;

import net.minecraftforge.common.ForgeConfigSpec;

public class AMBTileEntityConfig {
    public static ForgeConfigSpec.LongValue REMOVAL_UPDATE_TICKS;
    public static ForgeConfigSpec.LongValue EFFECT_UPDATE_TICKS;
    public static ForgeConfigSpec.IntValue RANGE_MULTIPLIER;
    public static ForgeConfigSpec.IntValue RANGE_ADD;
    public static ForgeConfigSpec.IntValue EFFECT_RANGE_ADD;
    public static ForgeConfigSpec.BooleanValue DO_EFFECTS;
    public static ForgeConfigSpec.BooleanValue REMOVE_BATS;

    public static void initialize(ForgeConfigSpec.Builder builder){
        builder.comment("AntiMobBeacon Config");

        REMOVAL_UPDATE_TICKS = builder.comment("Dictates how long it takes before an entity is removed")
                .defineInRange("AntiMobBeaconTileEntity.REMOVAL_UPDATE_TICKS",1L,1L,80L);
        EFFECT_UPDATE_TICKS = builder.comment("Dictates how long it takes before an entity is affected by dmg")
                .defineInRange("AntiMobBeaconTileEntity.EFFECT_UPDATE_TICKS",80L,1,300);
        RANGE_MULTIPLIER = builder.comment("Defines how much the range changes when more lava is added")
                .defineInRange("AntiMobBeaconTileEntity.RANGE_MULTIPLIER",10,10,50);
        RANGE_ADD = builder.comment("Defines how much base range the AntiMobBeacon has")
                .defineInRange("AntiMobBeaconTileEntity.RANGE_ADD",10,10,50);
        EFFECT_RANGE_ADD = builder.comment("Defines how large the area around the removal zone is where the effect is applied")
                .defineInRange("AntiMobBeaconTileEntity.EFFECT_RANGE_ADD",10,10,50);
        DO_EFFECTS = builder.comment("Changes whether there is an area outside of the removal zone that hurts enemies instead of removing them")
                .define("AntiMobBeaconTileEntity.DO_EFFECTS",true);
        REMOVE_BATS = builder.comment("Changes whether bats should be removed and affected by dmg in the outer zone or not")
                .define("AntiMobBeaconTileEntity.REMOVE_BATS",false);
    }

}
