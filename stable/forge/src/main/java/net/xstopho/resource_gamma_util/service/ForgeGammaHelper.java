package net.xstopho.resource_gamma_util.service;

import net.minecraftforge.fml.loading.FMLLoader;

public class ForgeGammaHelper implements IGammaHelper {
    @Override
    public boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }

    @Override
    public boolean isShaderEnabled() {
        return false;
    }
}
