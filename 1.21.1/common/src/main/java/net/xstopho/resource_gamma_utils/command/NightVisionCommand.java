package net.xstopho.resource_gamma_utils.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.xstopho.resource_gamma_utils.GammaConstants;
import net.xstopho.resource_gamma_utils.config.NightVisionConfig;
import net.xstopho.resource_gamma_utils.manager.MobEffectManager;
import net.xstopho.resource_gamma_utils.manager.NightVisionManager;
import net.xstopho.resource_gamma_utils.util.MessageUtil;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class NightVisionCommand {

    public static void execute(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal(GammaConstants.NAMESPACE)
                .executes(ctx -> {
                    NightVisionManager.toggleNightVision();
                    return 1;
                })
                .then(literal("enable")
                        .executes(ctx -> {
                            NightVisionManager.enableNightVision();
                            return 1;
                        }))
                .then(literal("disable")
                        .executes(ctx -> {
                            NightVisionManager.disableNightVision();
                            return 1;
                        }))
                .then(argument("value", integer())
                        .executes(ctx -> {
                            NightVisionManager.enableAndOrSetNightVision(getInteger(ctx, "value"));
                            return 1;
                        }))
                .then(literal("toggle")
                        .executes(ctx -> {
                            NightVisionManager.toggleNightVision();
                            return 1;
                        }))
                .then(literal("set")
                        .then(argument("value", integer())
                                .executes(ctx -> {
                                    NightVisionManager.enableAndOrSetNightVision(getInteger(ctx, "value"));
                                    return 1;
                                })))
                .then(literal("increase")
                        .executes(ctx -> {
                            NightVisionManager.increaseNightVision(0);
                            return 1;
                        })
                        .then(argument("value", integer())
                                .executes(ctx -> {
                                    NightVisionManager.increaseNightVision(getInteger(ctx, "value"));
                                    return 1;
                                })))
                .then(literal("decrease")
                        .executes(ctx -> {
                            NightVisionManager.decreaseNightVision(0);
                            return 1;
                        })
                        .then(argument("value", integer())
                                .executes(ctx -> {
                                    NightVisionManager.decreaseNightVision(getInteger(ctx, "value"));
                                    return 1;
                                })))
                .then(literal("dynamic")
                        .executes(ctx -> {
                            NightVisionManager.toggleDynamicNightVision();
                            return 1;
                        })
                        .then(literal("toggle")
                                .executes(ctx -> {
                                    NightVisionManager.toggleDynamicNightVision();
                                    return 1;
                                }))
                        .then(literal("enable")
                                .executes(ctx -> {
                                    NightVisionConfig.setDynamicNightVisionStatus(true);
                                    MessageUtil.sendMessage(Component.translatable("text.gammautils.message.dynamicNightVisionOn"));
                                    return 1;
                                }))
                        .then(literal("disable")
                                .executes(ctx -> {
                                    NightVisionConfig.setDynamicNightVisionStatus(false);
                                    MessageUtil.sendMessage(Component.translatable("text.gammautils.message.dynamicNightVisionOff"));
                                    return 1;
                                })))
                .then(literal("statuseffect")
                        .executes(ctx -> {
                            NightVisionManager.toggleStatusEffect();
                            return 1;
                        })
                        .then(literal("toggle")
                                .executes(ctx -> {
                                    NightVisionManager.toggleStatusEffect();
                                    return 1;
                                }))
                        .then(literal("enable")
                                .executes(ctx -> {
                                    NightVisionConfig.setStatusEffectStatus(true);
                                    MobEffectManager.updateNightVisionEffect();
                                    MessageUtil.sendMessage(Component.translatable("text.gammautils.message.statusEffectNightVisionOn"));
                                    return 1;
                                }))
                        .then(literal("disable")
                                .executes(ctx -> {
                                    NightVisionConfig.setStatusEffectStatus(false);
                                    MobEffectManager.updateNightVisionEffect();
                                    MessageUtil.sendMessage(Component.translatable("text.gammautils.message.statusEffectNightVisionOff"));
                                    return 1;
                                })))
                .then(literal("transition")
                        .executes(ctx -> {
                            NightVisionManager.toggleSmoothTransition();
                            return 1;
                        })
                        .then(literal("toggle")
                                .executes(ctx -> {
                                    NightVisionManager.toggleSmoothTransition();
                                    return 1;
                                }))
                        .then(literal("smooth")
                                .executes(ctx -> {
                                    NightVisionConfig.setSmoothTransitionStatus(true);
                                    MessageUtil.sendMessage(Component.translatable("text.gammautils.message.transitionNightVisionOn"));
                                    return 1;
                                }))
                        .then(literal("none")
                                .executes(ctx -> {
                                    NightVisionConfig.setSmoothTransitionStatus(false);
                                    MessageUtil.sendMessage(Component.translatable("text.gammautils.message.transitionNightVisionOff"));
                                    return 1;
                                }))));
    }
}
