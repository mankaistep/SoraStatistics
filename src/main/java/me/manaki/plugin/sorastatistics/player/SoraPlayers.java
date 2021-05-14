package me.manaki.plugin.sorastatistics.player;

import com.google.common.collect.Maps;
import mk.plugin.playerdata.storage.PlayerDataAPI;
import mk.plugin.santory.traveler.Travelers;
import org.bukkit.entity.Player;

import java.util.Map;

public class SoraPlayers {

    private static final Map<Player, SoraPlayer> players = Maps.newConcurrentMap();

    private static final String HOOK = "sorastatistics";

    public static void clearAll() {
        players.clear();
    }

    public static SoraPlayer get(Player player) {
        if (!players.containsKey(player)) players.put(player, load(player.getName()));
        return players.get(player);
    }

    public static SoraPlayer load(String name) {
        var data = PlayerDataAPI.get(name, HOOK);
        Map<String, Long> statistics = Maps.newHashMap();
        for (Map.Entry<String, String> e : data.getDataMap().entrySet()) {
            statistics.put(e.getKey(), Long.parseLong(e.getValue()));
        }
        return new SoraPlayer(name, statistics);
    }

    public static void save(SoraPlayer sp) {
        String name = sp.getName();
        var data = PlayerDataAPI.get(name, HOOK);
        for (Map.Entry<String, Long> e : sp.getAll().entrySet()) {
            data.set(e.getKey(), e.getValue() + "");
        }

        // Static placholder
        for (Map.Entry<String, Long> e : getStaticPlaceholders(sp.getPlayer()).entrySet()) {
            data.set(e.getKey(), e.getValue() + "");
        }
        data.save();
    }

    public static void saveAndClearCache(Player player) {
        get(player).save();
        players.remove(player);
    }

    public static void saveAll() {
        for (Player p : players.keySet()) {
            get(p).save();
        }
    }

    public static Map<String, Long> getStaticPlaceholders(Player player) {
        Map<String, Long> m = Maps.newHashMap();

        // Santory
        var t = Travelers.get(player);
        m.put("santory_player_exp", t.getData().getExp());

        return m;
    }

}
