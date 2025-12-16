package net.xstopho.resource_gamma_util.mixins;

import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.xstopho.resource_gamma_util.GammaConstants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionInstance.class)
public class OptionInstanceMixin<T> {

    @Shadow T value;
    @Shadow @Final Component caption;

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    public void resource_gamma_util$get(CallbackInfoReturnable<T> cir) {
        if (caption.equals(Component.translatable("options.gamma")) && GammaConstants.gammaEnabled) {
            cir.setReturnValue((T) GammaConstants.customGamma);
        }
    }
}
