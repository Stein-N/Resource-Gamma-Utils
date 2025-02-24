package net.xstopho.resource_gamma_utils.mixins;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.xstopho.resource_gamma_utils.manager.GammaManager;
import net.xstopho.resource_gamma_utils.manager.MobEffectManager;
import net.xstopho.resource_gamma_utils.manager.NightVisionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    // Mixin to enable client side status effects on world load etc
    @Inject(method = "handlePlayerAbilities", at = @At("TAIL"))
    private void gamma_utils$handlePlayerAbilities(ClientboundPlayerAbilitiesPacket packet, CallbackInfo info) {
        MobEffectManager.updateAllEffects();
        GammaManager.setDimensionPreference();
        NightVisionManager.setDimensionPreference();
    }

    // Mixin to make sure the client side status effects remain enabled after death
    @Inject(method = "handleRespawn", at = @At("TAIL"))
    private void gamma_utils$handleRespawn(ClientboundRespawnPacket packet, CallbackInfo info) {

    }
}
