package net.xstopho.resource_gamma_util;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class ResourceGammaUtils implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(GammaConstants.TOGGLE);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
           if (GammaConstants.TOGGLE.consumeClick()) {
               client.options.gamma().set(1500.00);
           }
        });
    }
}
