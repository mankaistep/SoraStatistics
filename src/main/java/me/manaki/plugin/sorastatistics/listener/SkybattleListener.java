package me.manaki.plugin.sorastatistics.listener;

import manaki.plugin.skybattleclient.event.PlayerEndGameEvent;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkybattleListener implements Listener {

    @EventHandler
    public void onGameEnd(PlayerEndGameEvent e) {
        var p = e.getPlayer();
        var pr = e.getResult();
        var sp = SoraPlayers.get(p);

        sp.add("skybattle_kills", pr.getStatistic().getKill());
        sp.add("skybattle_assists", pr.getStatistic().getAssist());
    }

}
