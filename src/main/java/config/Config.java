package config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class Config {
    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec config;

    static {
        AMBTileEntityConfig.initialize(builder);

        config = builder.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String name){
        final CommentedFileConfig fileConfig = CommentedFileConfig.builder(new File(name)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        fileConfig.load();
        config.setConfig(fileConfig);
    }
}
