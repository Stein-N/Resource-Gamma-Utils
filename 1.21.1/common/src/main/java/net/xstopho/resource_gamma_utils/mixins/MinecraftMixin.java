package net.xstopho.resource_gamma_utils.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.xstopho.resource_gamma_utils.config.GammaConfig;
import net.xstopho.resource_gamma_utils.config.NightVisionConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow @Final
    public Options options;

    // Mixin to make sure everything is properly and neatly saved when closing the game
    @Inject(method = "close", at = @At("HEAD"))
    private void saveOnClose(CallbackInfo info) {
        double gamma = options.gamma().get();

        if (GammaConfig.isResetOnCloseEnabled()) {
            gamma = GammaConfig.getDefaultStrength();
        }

        if (NightVisionConfig.isResetOnCloseEnabled()) {
            NightVisionConfig.setStatus(false);
        }

        options.gamma().set((Math.round(gamma * 100.0) / 100.0));
        options.save();

    }
}
