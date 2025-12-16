package net.xstopho.resource_gamma_util.service;

import net.neoforged.fml.ModList;

public class NeoforgeGammaHelper implements IGammaHelper {
    @Override
    public boolean isShaderActive() {
        if (ModList.get().isLoaded("iris")) {
            return net.irisshaders.iris.api.v0.IrisApi.getInstance().isShaderPackInUse();
        }
        return false;
    }
}
