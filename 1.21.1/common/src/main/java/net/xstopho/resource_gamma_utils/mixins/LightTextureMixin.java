package net.xstopho.resource_gamma_utils.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.LightTexture;
import net.xstopho.resource_gamma_utils.config.NightVisionConfig;
import net.xstopho.resource_gamma_utils.manager.GammaManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightTexture.class)
public class LightTextureMixin {

    // Mixin needed to allow negative gamma
    @ModifyExpressionValue(method = "updateLightTexture", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 0))
    private float gamma_utils$updateLightTexture(float original) {
        float gamma = (float) GammaManager.getGamma();
        if (gamma < 0) return gamma;

        return original;
    }

    // Mixin to allow Night Vision without Status Effect
    @ModifyExpressionValue(method = "updateLightTexture", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/core/Holder;)Z", ordinal = 0))
    private boolean hasNightVision(boolean original) {
        return NightVisionConfig.isEnabled() || original;
    }
}
