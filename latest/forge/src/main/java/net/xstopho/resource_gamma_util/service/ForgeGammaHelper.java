package net.xstopho.resource_gamma_util.service;

import net.minecraftforge.fml.ModList;

public class ForgeGammaHelper implements IGammaHelper {

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isShaderEnabled() {
        return false;
    }
}
