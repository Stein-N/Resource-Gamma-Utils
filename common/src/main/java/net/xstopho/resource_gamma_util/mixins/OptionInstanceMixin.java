package net.xstopho.resource_gamma_util.mixins;

import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionInstance.class)
public class OptionInstanceMixin<T> {

    @Shadow @Final T value;
    @Shadow @Final Component caption;

    @Unique T backupValue;

    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    public void resource_gamma_util$set(T value, CallbackInfo ci) {
        if (caption.equals(Component.translatable("options.gamma"))) {
            if (this.value.equals(1500.00)) {
                this.value = this.backupValue;
            } else {
                this.backupValue = this.value;
                this.value = value;
            }
            ci.cancel();
        }
    }
}
