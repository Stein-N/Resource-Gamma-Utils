package net.xstopho.resource_gamma_utils;

import net.xstopho.resource_gamma_utils.config.GammaConfig;
import net.xstopho.resource_gamma_utils.config.NightVisionConfig;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GammaConstants {
    public static final String MOD_ID = "resource_gamma_utils";
    public static final String MOD_NAME = "Resource Gamma Utils";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final String NAMESPACE = "rcgammautils";

    public static void commonInit() {
        ConfigRegistry.register(GammaConfig.class, MOD_ID);
        ConfigRegistry.register(NightVisionConfig.class, MOD_ID);
    }
}
