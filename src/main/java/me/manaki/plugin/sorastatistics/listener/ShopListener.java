package me.manaki.plugin.sorastatistics.listener;

import me.manaki.plugin.shops.event.PlayerShopItemBuyEvent;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShopListener implements Listener {

    @EventHandler
    public void onBuy(PlayerShopItemBuyEvent e) {
        var player = e.getPlayer();
        var id = e.getItemID();
        var sid = e.getShopID();
        var sp = SoraPlayers.get(player);
        sp.add("shops_bought_times", 1);
        sp.add("shops_bought_times_" + id.toLowerCase(), 1);
        sp.add("shops_bought_times_" + id.toLowerCase() + "_" + sid.toLowerCase(), 1);
    }

}
