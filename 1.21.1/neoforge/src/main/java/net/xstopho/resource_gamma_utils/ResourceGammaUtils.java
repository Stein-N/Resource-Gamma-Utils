package net.xstopho.resource_gamma_utils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(GammaConstants.MOD_ID)
public class ResourceGammaUtils {

    public ResourceGammaUtils(IEventBus eventBus) {
        GammaConstants.commonInit();
    }
}
