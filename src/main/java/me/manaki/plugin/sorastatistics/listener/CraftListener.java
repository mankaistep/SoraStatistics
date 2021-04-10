package me.manaki.plugin.sorastatistics.listener;

import me.manaki.plugin.crafts.event.ItemCraftEvent;
import me.manaki.plugin.farms.event.PlayerFarmHarvestEvent;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CraftListener implements Listener {

    @EventHandler
    public void onHarvest(ItemCraftEvent e) {
        var player = e.getPlayer();
        var id = e.getRecipeID();
        var sp = SoraPlayers.get(player);
        sp.add("craft_success_times", 1);
        sp.add("craft_success_times_" + id.toLowerCase(), 1);
    }

}