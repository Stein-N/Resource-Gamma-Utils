package net.xstopho.resource_gamma_util.mixins;

import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionInstance.class)
public class OptionInstanceMixin<T> {

    @Shadow @Final @Mutable T value;
    @Shadow @Final Component caption;

    @Unique T backupValue;

    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    public void resource_gamma_util$set(T value, CallbackInfo ci) {
        if (caption.equals(Component.translatable("options.gamma"))) {
            if (active(this.value)) {
                this.value = this.backupValue;
                this.backupValue = null;
            } else {
                this.backupValue = this.value;
                this.value = value;
            }
            ci.cancel();
        }
    }

    @Unique
    private static boolean active(Object value) {
        double optionValue = (double) value;
        return optionValue == 1500;
    }
}
