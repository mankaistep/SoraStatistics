package me.manaki.plugin.sorastatistics.listener;

import me.manaki.plugin.sorastatistics.SoraStatistics;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StatisticListener implements Listener {

    private boolean firstJoin;

    public StatisticListener(boolean firstJoin) {
        this.firstJoin = firstJoin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (firstJoin) {
            Bukkit.getScheduler().runTaskLater(SoraStatistics.get(), () -> {
                SoraStatistics.get().registerHookListeners();
            }, 10);
            firstJoin = false;
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        var player = e.getPlayer();
        SoraPlayers.saveAndClearCache(player);
    }

}
