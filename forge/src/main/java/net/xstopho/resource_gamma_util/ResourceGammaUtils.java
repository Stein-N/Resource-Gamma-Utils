package net.xstopho.resource_gamma_util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(GammaConstants.MOD_ID)
public class ResourceGammaUtils {

    public ResourceGammaUtils() {}

    @Mod.EventBusSubscriber(modid = GammaConstants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModClientHandler {

        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(GammaConstants.TOGGLE);
        }


    }

    @Mod.EventBusSubscriber(modid = GammaConstants.MOD_ID, value = Dist.CLIENT)
    public static class ForgeClientHandler {

        @SubscribeEvent
        public static void registerClientEvent(TickEvent.ClientTickEvent.Post event) {
            GammaConstants.useHotkey(Minecraft.getInstance());
        }
    }
}
