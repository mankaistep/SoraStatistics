package me.manaki.plugin.sorastatistics.player;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

public class SoraPlayer {

    private final String name;
    private final Map<String, Long> statistics;

    public SoraPlayer(String name, Map<String, Long> statistics) {
        this.name = name;
        this.statistics = statistics;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Player getPlayer() {
        return Bukkit.getPlayer(name);
    }

    public Map<String, Long> getAll() {
        return Maps.newHashMap(statistics);
    }

    public Long get(String statistic) {
        return statistics.getOrDefault(statistic, 0l);
    }

    public void set(String statistic, long value) {
        statistics.put(statistic, value);
    }

    public void add(String statistic, long value) {
        set(statistic, get(statistic) + value);
    }

    public void save() {
        SoraPlayers.save(this);
    }

}
