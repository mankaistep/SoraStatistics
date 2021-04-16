package me.manaki.plugin.sorastatistics.listener;

import me.manaki.plugin.dungeons.dungeon.event.DungeonFinishEvent;
import me.manaki.plugin.dungeons.dungeon.event.DungeonMobKilledEvent;
import me.manaki.plugin.dungeons.dungeon.status.DStatus;
import me.manaki.plugin.dungeons.dungeon.status.DungeonResult;
import me.manaki.plugin.dungeons.dungeon.util.DGameUtils;
import me.manaki.plugin.dungeons.rank.Rank;
import me.manaki.plugin.dungeons.rank.RankUtils;
import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class DungeonListener implements Listener {

    @EventHandler
    public void onDungeonFinish(DungeonFinishEvent e) {
        String id = e.getID();
        DStatus status = DGameUtils.getStatus(id);
        boolean win = e.getResult() == DungeonResult.WIN;

        for (UUID uuid : status.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            var sp = SoraPlayers.get(player);
            // Lose
            if (!win) {
                sp.add("dungeon_lose_times", 1);
                continue;
            }

            // Win
            Rank r = RankUtils.getRank(id, status.getStatistic(player));
            sp.add("dungeon_win_times", 1);
            sp.add("dungeon_win_times_" + id, 1);
            sp.add("dungeon_win_times_" + id + "_" + r.name().toLowerCase(), 1);
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

}
