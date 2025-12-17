package net.xstopho.resource_gamma_util.service;

import net.irisshaders.iris.api.v0.IrisApi;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoforgeGammaHelper implements IGammaHelper {
    @Override
    public boolean isModLoaded(String modId) {
        return FMLLoader.getLoadingModList().getModFileById(modId) != null;
    }

    @Override
    public boolean isShaderEnabled() {
        if (isModLoaded("iris")) {
            return IrisApi.getInstance().getConfig().areShadersEnabled();
        }
        return false;
    }
}
