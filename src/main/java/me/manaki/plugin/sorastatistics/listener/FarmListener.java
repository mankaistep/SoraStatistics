package me.manaki.plugin.sorastatistics.listener;

import me.manaki.plugin.farms.event.PlayerFarmHarvestEvent;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FarmListener implements Listener {

    @EventHandler
    public void onHarvest(PlayerFarmHarvestEvent e) {
        var player = e.getPlayer();
        var id = e.getID();
        var sp = SoraPlayers.get(player);
        sp.add("farms_harvest_times", 1);
        sp.add("farms_harvest_times_" + id.toLowerCase(), 1);
    }

}
