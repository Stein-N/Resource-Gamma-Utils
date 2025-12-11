package net.xstopho.resource_gamma_util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("resource_gamma_util")
public class ResourceGammaUtils {

    public ResourceGammaUtils() {}

    @Mod.EventBusSubscriber(modid = GammaConstants.MOD_ID, value = Dist.CLIENT)
    public static class ForgeClientHandler {

        @SubscribeEvent
        public static void registerKeyMapping(RegisterKeyMappingsEvent event) {
            event.register(GammaConstants.TOGGLE);
        }

        @SubscribeEvent
        public static void registerClientEvent(TickEvent.ClientTickEvent.Post event) {
            GammaConstants.useHotkey(Minecraft.getInstance());
        }
    }
}
