package com.donutsmp.client.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import com.donutsmp.client.config.ScoreboardConfig;

public class ScoreboardRenderer {

    public static void render(DrawContext context) {
        if (!ScoreboardConfig.enabled || ScoreboardConfig.lines.isEmpty()) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;

        int x = ScoreboardConfig.x;
        int y = ScoreboardConfig.y;
        float scale = ScoreboardConfig.scale;

        // Draw semi-transparent background
        int width = 150;
        int height = (ScoreboardConfig.lines.size() + 1) * 10 + 10;

        context.fill(x - 2, y - 2, x + width, y + height, 0x80000000);
        context.drawBorder(x - 2, y - 2, width + 4, height + 4, 0xFFFFFFFF);

        // Draw title
        context.drawTextWithShadow(textRenderer, Text.literal(ScoreboardConfig.title), 
            x + 5, y + 3, 0xFFFFFF);

        // Draw lines
        int lineY = y + 15;
        for (String line : ScoreboardConfig.lines) {
            context.drawTextWithShadow(textRenderer, Text.literal(line), 
                x + 5, lineY, 0xFFFFFF);
            lineY += 12;
        }
    }
}