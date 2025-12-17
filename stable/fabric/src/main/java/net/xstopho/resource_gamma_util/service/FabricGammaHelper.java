package net.xstopho.resource_gamma_util.service;

import net.fabricmc.loader.api.FabricLoader;
import net.irisshaders.iris.api.v0.IrisApi;

public class FabricGammaHelper implements IGammaHelper {

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isShaderEnabled() {
        if (isModLoaded("iris")) {
            return IrisApi.getInstance().getConfig().areShadersEnabled();
        }
        return false;
    }
}
