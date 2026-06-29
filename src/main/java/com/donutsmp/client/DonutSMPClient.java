package com.donutsmp.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.donutsmp.client.config.ScoreboardConfig;
import com.donutsmp.client.screen.ScoreboardConfigScreen;

@Environment(EnvType.CLIENT)
public class DonutSMPClient implements ClientModInitializer {
    
    public static KeyBinding openConfigKey;

    @Override
    public void onInitializeClient() {
        openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.donutsmp_mod.config",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_P,
            "category.donutsmp_mod.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openConfigKey.wasPressed()) {
                client.setScreen(new ScoreboardConfigScreen(null));
            }
        });

        ScoreboardConfig.load();
    }
}