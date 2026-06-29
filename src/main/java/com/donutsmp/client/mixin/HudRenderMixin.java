package com.donutsmp.client.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.donutsmp.client.config.ScoreboardConfig;
import com.donutsmp.client.renderer.ScoreboardRenderer;

@Mixin(InGameHud.class)
public class HudRenderMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void renderScoreboard(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (ScoreboardConfig.enabled) {
            ScoreboardRenderer.render(context);
        }
    }
}