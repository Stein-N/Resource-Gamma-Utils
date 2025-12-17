package net.xstopho.resource_gamma_util.service;

import net.irisshaders.iris.api.v0.IrisApi;
import net.neoforged.fml.ModList;

public class NeoforgeGammaHelper implements IGammaHelper {
    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isShaderEnabled() {
        if (isModLoaded("iris")) {
            return IrisApi.getInstance().getConfig().areShadersEnabled();
        }
        return false;
    }
}
