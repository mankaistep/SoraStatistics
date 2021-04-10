package me.manaki.plugin.sorastatistics.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.entity.Player;

public class StatisticPlaceholder extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "sora";
    }

    @Override
    public String getAuthor() {
        return "MankaiStep";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String s) {
        var sp = SoraPlayers.get(p);
        return sp.get(s) + "";
    }


}
