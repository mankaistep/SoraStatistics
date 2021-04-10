package me.manaki.plugin.sorastatistics.listener;

import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class StatisticListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        var player = e.getPlayer();
        SoraPlayers.saveAndClearCache(player);
    }

}
