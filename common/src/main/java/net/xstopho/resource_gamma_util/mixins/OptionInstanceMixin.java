package net.xstopho.resource_gamma_util.mixins;

import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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
            if (backupValue == null) {
                this.backupValue = this.value;
                this.value = value;
            } else {
                this.value = this.backupValue;
                this.backupValue = null;
            }
            ci.cancel();
        }
    }
}
