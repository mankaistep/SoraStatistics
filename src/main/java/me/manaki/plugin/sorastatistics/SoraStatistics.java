package me.manaki.plugin.sorastatistics;

import me.manaki.plugin.sorastatistics.command.StatisticCommand;
import me.manaki.plugin.sorastatistics.listener.*;
import me.manaki.plugin.sorastatistics.placeholder.StatisticPlaceholder;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import mk.plugin.santory.stat.Stat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SoraStatistics extends JavaPlugin {

    public boolean hasCrafts;
    public boolean hasDungeons;
    public boolean hasSantoryCore;
    public boolean hasShops;
    public boolean hasFarms;

    private BukkitRunnable task;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        // Placeholder
        new StatisticPlaceholder().register();

        // Commands
        this.getCommand("sorastatistics").setExecutor(new StatisticCommand());

        // Hook
        var pm = Bukkit.getPluginManager();
        hasCrafts = pm.isPluginEnabled("Crafts");
        hasDungeons = pm.isPluginEnabled("Dungeons");
        hasFarms = pm.isPluginEnabled("Farms");
        hasSantoryCore = pm.isPluginEnabled("SantoryCore");
        hasShops = pm.isPluginEnabled("Shops");
        hasFarms = pm.isPluginEnabled("Farms");

        // Event
        pm.registerEvents(new StatisticListener(), this);
        if (hasCrafts) pm.registerEvents(new CraftListener(), this);
        if (hasDungeons) pm.registerEvents(new DungeonListener(), this);
        if (hasSantoryCore) pm.registerEvents(new SantoryListener(), this);
        if (hasShops) pm.registerEvents(new ShopListener(), this);
        if (hasFarms) pm.registerEvents(new FarmListener(), this);

        // Saving task
        task = new BukkitRunnable() {
            @Override
            public void run() {
                SoraPlayers.saveAll();
            }
        };
        task.runTaskTimerAsynchronously(this, 0, 30 * 20);
    }

    @Override
    public void onDisable() {
        task.cancel();
        SoraPlayers.saveAll();
        SoraPlayers.clearAll();
    }

    public static SoraStatistics get() {
        return JavaPlugin.getPlugin(SoraStatistics.class);
    }

}
