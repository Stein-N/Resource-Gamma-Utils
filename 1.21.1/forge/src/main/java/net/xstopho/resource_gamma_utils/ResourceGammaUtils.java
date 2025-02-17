package net.xstopho.resource_gamma_utils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GammaConstants.MOD_ID)
public class ResourceGammaUtils {

    public ResourceGammaUtils(FMLJavaModLoadingContext context) {
        GammaConstants.commonInit();
    }
}
