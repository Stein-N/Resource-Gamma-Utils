package net.xstopho.resource_gamma_util.service;

import java.util.ServiceLoader;

public class GammaService {

    public static boolean isShaderActive() {
        return load(IGammaHelper.class).isShaderActive();
    }

    private static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz).findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service: " + clazz.getName()));
    }
}
