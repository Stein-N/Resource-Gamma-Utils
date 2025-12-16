package net.xstopho.resource_gamma_util.service;

import net.fabricmc.loader.api.FabricLoader;

public class FabricGammaHelper implements IGammaHelper {
    @Override
    public boolean isShaderActive() {
        if (FabricLoader.getInstance().isModLoaded("iris")) {
            return net.irisshaders.iris.api.v0.IrisApi.getInstance().getConfig().areShadersEnabled();
        }

        return false;
    }
}
