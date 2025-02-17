package net.xstopho.resource_gamma_utils.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.xstopho.resource_gamma_utils.config.GammaConfig;
import net.xstopho.resource_gamma_utils.config.NightVisionConfig;
import net.xstopho.resource_gamma_utils.effect.GammaEffect;

public class MobEffectManager {
    private static final Minecraft client = Minecraft.getInstance();
    public static final Holder.Direct<MobEffect> BRIGHT = new Holder.Direct<>(new GammaEffect("bright", MobEffectCategory.BENEFICIAL, 0));
    public static final Holder.Direct<MobEffect> DIM = new Holder.Direct<>(new GammaEffect("dim", MobEffectCategory.HARMFUL, 0));

    public static void updateAllEffects() {
        updateNightVisionEffect();
        updateGammaEffect();
    }

    public static void updateNightVisionEffect() {
        LocalPlayer player = client.player;
        if (player == null) return;

        if (NightVisionConfig.isEnabled() && NightVisionConfig.isStatusEffectEnabled()) {
            addPermanentEffect(player, MobEffects.NIGHT_VISION);
            return;
        }

        player.removeEffect(MobEffects.NIGHT_VISION);
    }

    public static void updateGammaEffect() {
        LocalPlayer player = client.player;
        if (player == null) return;

        if (GammaConfig.isStatusEffectEnabled()) {
            int gamma = GammaManager.getGammaPercentage();
            if (gamma > 100 && !player.hasEffect(BRIGHT)) {
                player.removeEffect(DIM);
                addPermanentEffect(player, BRIGHT);
                return;
            }

            if (gamma < 0 && !player.hasEffect(DIM)) {
                player.removeEffect(BRIGHT);
                addPermanentEffect(player, DIM);
                return;
            }
        }
        player.removeEffect(BRIGHT);
        player.removeEffect(DIM);
    }

    private static void addPermanentEffect(LocalPlayer player, Holder<MobEffect> effect) {
        MobEffectInstance mobEffect = new MobEffectInstance(effect, -1);
        player.addEffect(mobEffect);
    }
}
