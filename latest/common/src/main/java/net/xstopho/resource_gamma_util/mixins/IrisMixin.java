package net.xstopho.resource_gamma_util.mixins;

import net.irisshaders.iris.Iris;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.xstopho.resource_gamma_util.GammaConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Iris.class)
public class IrisMixin {

    @Inject(method = "toggleShaders", at = @At("HEAD"))
    private static void resource_gamma_util$toggleShader(Minecraft minecraft, boolean enabled, CallbackInfo ci) {
        LocalPlayer p = minecraft.player;

        if (p != null) {
            if (enabled && GammaConstants.gammaEnabled) {
                GammaConstants.gammaEnabled = false;
                p.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
            } else if (!enabled && p.hasEffect(MobEffects.NIGHT_VISION)) {
                GammaConstants.gammaEnabled = true;
                p.removeEffect(MobEffects.NIGHT_VISION);
            }
        }
    }
}
