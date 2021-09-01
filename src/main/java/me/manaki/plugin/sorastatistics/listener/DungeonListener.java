package me.manaki.plugin.sorastatistics.listener;

import me.manaki.plugin.dungeons.dungeon.event.DungeonFinishEvent;
import me.manaki.plugin.dungeons.dungeon.event.DungeonMobKilledEvent;
import me.manaki.plugin.dungeons.dungeon.status.DStatus;
import me.manaki.plugin.dungeons.dungeon.status.DungeonResult;
import me.manaki.plugin.dungeons.dungeon.util.DPlayerUtils;
import me.manaki.plugin.dungeons.rank.Rank;
import me.manaki.plugin.dungeons.rank.RankUtils;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DungeonListener implements Listener {

    @EventHandler
    public void onDungeonFinish(DungeonFinishEvent e) {
        String id = e.getID();
        DStatus status = e.getStatus();
        boolean win = e.getResult() == DungeonResult.WIN;

        for (var uuid : status.getStarters()) {
            var player = Bukkit.getPlayer(uuid);
            if (player == null) continue;

            var sp = SoraPlayers.get(player);

            if (!win) {
                sp.add("dungeon_lose_times", 1);
            }
            else {
                Rank r = RankUtils.getRank(id, status.getStatistic(player));
                sp.add("dungeon_win_times", 1);
                sp.add("dungeon_win_times_" + id, 1);
                sp.add("dungeon_win_times_" + id + "_" + r.name().toLowerCase(), 1);
            }

            // Statistic
            var ps = status.getStatistic(player);
            sp.add("dungeon_save_times", ps.getSlaveSaved());
        }
    }

    @EventHandler
    public void onMobKilled(DungeonMobKilledEvent e) {
        String id = e.getID();
        Player killer = e.getKiller();
        var sp = SoraPlayers.get(killer);
        sp.add("dungeon_mob_killed", 1);
        sp.add("dungeon_mob_killed_" + e.getMobID().toLowerCase(), 1);
        sp.add("dungeon_mob_killed_" + e.getMobID().toLowerCase() + "_" + id, 1);
    }

    @EventHandler
    public void onPlayerDeathInDungeon(PlayerDeathEvent e) {
        var p = e.getEntity();
        if (DPlayerUtils.isInDungeon(p)) {
            var sp = SoraPlayers.get(p);
            sp.add("dungeon_death_times", 1);
        }
    }

}
