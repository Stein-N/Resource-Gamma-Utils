package net.xstopho.resource_gamma_util.service;

import java.util.ServiceLoader;

public class GammaService {

    public static boolean isModLoaded(String modId) {
        return load(IGammaHelper.class).isModLoaded(modId);
    }

    public static boolean isShaderEnabled() {
        return load(IGammaHelper.class).isShaderEnabled();
    }

    private static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz).findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service: " + clazz.getName()));
    }
}
