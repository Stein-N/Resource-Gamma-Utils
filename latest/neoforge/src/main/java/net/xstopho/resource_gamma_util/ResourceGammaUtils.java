package net.xstopho.resource_gamma_util;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@Mod(GammaConstants.MOD_ID)
public class ResourceGammaUtils {

    public ResourceGammaUtils(IEventBus eventBus) {}

    @EventBusSubscriber(modid = GammaConstants.MOD_ID, value = Dist.CLIENT)
    public static class ModClientHandler {

        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(GammaConstants.TOGGLE);
        }

        @SubscribeEvent
        public static void registerClientEvent(ClientTickEvent.Post event) {
            GammaConstants.useHotkey(Minecraft.getInstance());
        }
    }
}
