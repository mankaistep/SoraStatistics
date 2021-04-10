package me.manaki.plugin.sorastatistics.command;

import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class StatisticCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {

        try {
            if (args[0].equalsIgnoreCase("show")) {
                String name = args[1];
                var sp = SoraPlayers.load(name);
                for (Map.Entry<String, Long> e : sp.getAll().entrySet()) {
                    sender.sendMessage("§a" + e.getKey() + ": §7" + e.getValue());
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            sendHelp(sender);
        }

        return false;
    }

    public void sendHelp(CommandSender sender) {
        sender.sendMessage("§a/ss show <player>");
    }

}
