package net.xstopho.resource_gamma_utils.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.xstopho.resource_gamma_utils.GammaConstants;
import net.xstopho.resource_gamma_utils.config.GammaConfig;
import net.xstopho.resource_gamma_utils.manager.GammaManager;
import net.xstopho.resource_gamma_utils.manager.MobEffectManager;
import net.xstopho.resource_gamma_utils.util.MessageUtil;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class GammaCommand {

    public static void execute(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal(GammaConstants.NAMESPACE)
                .executes(ctx -> {
                    GammaManager.toggleGamma();
                    return 1;
                })
                .then(argument("value", integer())
                        .executes(ctx -> {
                            GammaManager.setGamma(getInteger(ctx, "value") / 100.0, true, true);
                            return 1;
                        }))
                .then(literal("toggle")
                        .executes(ctx -> {
                            GammaManager.toggleGamma();
                            return 1;
                        }))
                .then(literal("min")
                        .executes(ctx -> {
                            GammaManager.minGamma();
                            return 1;
                        }))
                .then(literal("max")
                        .executes(ctx -> {
                            GammaManager.maxGamma();
                            return 1;
                        }))
                .then(literal("set")
                        .then(argument("value", integer())
                                .executes(ctx -> {
                                    GammaManager.setGamma(getInteger(ctx, "value") / 100.0, true, true);
                                    return 1;
                                })))
                .then(literal("increase")
                        .executes(ctx -> {
                            GammaManager.increaseGamma(0);
                            return 1;
                        })
                        .then(argument("value", integer())
                                .executes(ctx -> {
                                    GammaManager.increaseGamma(getInteger(ctx, "value") / 100.0);
                                    return 1;
                                })))
                .then(literal("decrease")
                        .executes(ctx -> {
                            GammaManager.decreaseGamma(0);
                            return 1;
                        })
                        .then(argument("value", integer())
                                .executes(ctx -> {
                                    GammaManager.decreaseGamma(getInteger(ctx, "value") / 100.0);
                                    return 1;
                                })))
                .then(literal("dynamic")
                        .executes(ctx -> {
                            GammaManager.toggleDynamicGamma();
                            return 1;
                        })
                        .then(literal("toggle")
                                .executes(ctx -> {
                                    GammaManager.toggleDynamicGamma();
                                    return 1;
                                }))
                        .then(literal("enable")
                                .executes(ctx -> {
                                    GammaConfig.setDynamicGammaStatus(true);
                                    MessageUtil.sendMessage(Component.translatable("text.resource_gamma_utils.message.dynamicGammaOn"));
                                    return 1;
                                }))
                        .then(literal("disable")
                                .executes(ctx -> {
                                    GammaConfig.setDynamicGammaStatus(false);
                                    MessageUtil.sendMessage(Component.translatable("text.resource_gamma_utils.message.dynamicGammaOff"));
                                    return 1;
                                })))
                .then(literal("statuseffect")
                        .executes(ctx -> {
                            GammaManager.toggleStatusEffect();
                            return 1;
                        })
                        .then(literal("toggle")
                                .executes(ctx -> {
                                    GammaManager.toggleStatusEffect();
                                    return 1;
                                }))
                        .then(literal("enable")
                                .executes(ctx -> {
                                    GammaConfig.setStatusEffectStatus(true);
                                    MobEffectManager.updateGammaEffect();
                                    MessageUtil.sendMessage(Component.translatable("text.resource_gamma_utils.message.statusEffectGammaOn"));
                                    return 1;
                                }))
                        .then(literal("disable")
                                .executes(ctx -> {
                                    GammaConfig.setStatusEffectStatus(false);
                                    MobEffectManager.updateGammaEffect();
                                    MessageUtil.sendMessage(Component.translatable("text.resource_gamma_utils.message.statusEffectGammaOff"));
                                    return 1;
                                })))
                .then(literal("transition")
                        .executes(ctx -> {
                            GammaManager.toggleSmoothTransition();
                            return 1;
                        })
                        .then(literal("toggle")
                                .executes(ctx -> {
                                    GammaManager.toggleSmoothTransition();
                                    return 1;
                                }))
                        .then(literal("smooth")
                                .executes(ctx -> {
                                    GammaConfig.setSmoothTransitionStatus(true);
                                    MessageUtil.sendMessage(Component.translatable("text.resource_gamma_utils.message.transitionGammaOn"));
                                    return 1;
                                }))
                        .then(literal("none")
                                .executes(ctx -> {
                                    GammaConfig.setSmoothTransitionStatus(false);
                                    MessageUtil.sendMessage(Component.translatable("text.resource_gamma_utils.message.transitionGammaOff"));
                                    return 1;
                                }))));
    }
}
