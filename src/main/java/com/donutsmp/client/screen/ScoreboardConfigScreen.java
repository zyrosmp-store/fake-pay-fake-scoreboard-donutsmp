package com.donutsmp.client.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import com.donutsmp.client.config.ScoreboardConfig;

public class ScoreboardConfigScreen extends Screen {
    private final Screen parent;
    private TextFieldWidget titleField;
    private TextFieldWidget contentField;
    private int scrollOffset = 0;

    public ScoreboardConfigScreen(Screen parent) {
        super(Text.literal("Scoreboard Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.clearChildren();
        
        // Title field
        this.titleField = new TextFieldWidget(this.textRenderer, 10, 30, 200, 20, Text.literal("Title"));
        this.titleField.setText(ScoreboardConfig.title);
        this.addDrawableChild(this.titleField);
        
        // Content field (multi-line)
        this.contentField = new TextFieldWidget(this.textRenderer, 10, 60, this.width - 20, 150, Text.literal("Content"));
        this.contentField.setText(String.join("\n", ScoreboardConfig.lines));
        this.contentField.setMaxLength(5000);
        this.addDrawableChild(this.contentField);

        // Save button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Save"), button -> this.save())
            .dimensions(10, this.height - 60, 100, 20)
            .build());

        // Test Fake Pay button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Test Fake Pay"), button -> this.testFakePay())
            .dimensions(120, this.height - 60, 100, 20)
            .build());

        // Done button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Done"), button -> this.close())
            .dimensions(this.width - 110, this.height - 60, 100, 20)
            .build());
    }

    private void save() {
        ScoreboardConfig.title = this.titleField.getText();
        String content = this.contentField.getText();
        ScoreboardConfig.lines.clear();
        for (String line : content.split("\n")) {
            if (!line.trim().isEmpty()) {
                ScoreboardConfig.lines.add(line);
            }
        }
        ScoreboardConfig.save();
    }

    private void testFakePay() {
        if (this.client != null && this.client.player != null) {
            this.client.player.sendMessage(
                Text.literal("§f[§6PAY§f] §eVotre joueur §6a payé §eunknown §e§o100€"),
                false
            );
        }
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 10, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, Text.literal("Title:"), 10, 18, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, Text.literal("Scoreboard Lines:"), 10, 50, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}