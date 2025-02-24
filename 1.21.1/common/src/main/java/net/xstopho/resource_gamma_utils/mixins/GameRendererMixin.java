package net.xstopho.resource_gamma_utils.mixins;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.xstopho.resource_gamma_utils.config.NightVisionConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    // Mixin to adjust the night vision strength
    @Inject(method = "getNightVisionScale", at = @At("HEAD"), cancellable = true)
    private static void gamma_utils$getNightVisionScale(LivingEntity entity, float partialTick, CallbackInfoReturnable<Float> cir) {
        if (NightVisionConfig.isEnabled()) {
            cir.setReturnValue((float) (NightVisionConfig.getStrength() / 100f));
        }
    }
}
