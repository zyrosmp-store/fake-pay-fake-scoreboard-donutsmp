package com.donutsmp.client.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.donutsmp.client.config.ScoreboardConfig;

public class FakePayCommand {

    public static void register() {
        ClientCommandManager.getActiveDispatcher().register(
            ClientCommandManager.literal("pay")
                .then(ClientCommandManager.argument("player", StringArgumentType.word())
                    .then(ClientCommandManager.argument("amount", IntegerArgumentType.integer(1, 1000000))
                        .executes(context -> fakepay(
                            context.getSource(),
                            StringArgumentType.getString(context, "player"),
                            IntegerArgumentType.getInteger(context, "amount")
                        ))
                    )
                )
        );
    }

    private static int fakepay(FabricClientCommandSource source, String player, int amount) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (client.player != null) {
            // Send chat message like DonutSMP style
            String message = String.format("§f[§6PAY§f] §eVotre joueur §6a payé §e%s §e§o%d€", 
                player, amount);
            
            client.player.sendMessage(Text.literal(message), false);

            // Update scoreboard
            boolean playerFound = false;
            for (int i = 0; i < ScoreboardConfig.lines.size(); i++) {
                String line = ScoreboardConfig.lines.get(i);
                if (line.contains(player)) {
                    // Extract current amount and add to it
                    int currentAmount = extractAmount(line);
                    int newAmount = currentAmount + amount;
                    String updatedLine = String.format("§eJoueur: %s §6%d€", player, newAmount);
                    ScoreboardConfig.lines.set(i, updatedLine);
                    playerFound = true;
                    break;
                }
            }

            // If player not found, add new line
            if (!playerFound) {
                ScoreboardConfig.lines.add(String.format("§e%s: §6%d€", player, amount));
            }

            ScoreboardConfig.save();
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int extractAmount(String line) {
        try {
            // Extract number from line (assumes format like "Player: 100€")
            String[] parts = line.split("§6");
            if (parts.length > 1) {
                String numberStr = parts[parts.length - 1].replaceAll("[^0-9]", "");
                return Integer.parseInt(numberStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}