package me.manaki.plugin.sorastatistics.listener;

import me.manaki.plugin.sorastatistics.player.SoraPlayers;
import mk.plugin.santory.event.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SantoryListener implements Listener {

    @EventHandler
    public void onDamage(PlayerDamagedEntityEvent e) {
        var player = e.getPlayer();
        double damage = e.getDamage();
        var type = e.getDamageType();

        var sp = SoraPlayers.get(player);
        sp.add("santory_player_damage_" + type.name().toLowerCase() + "_dealt", Double.valueOf(damage).longValue());
    }

    @EventHandler
    public void onSkill(PlayerSkillExecuteEvent e) {
        var player = e.getPlayer();
        var sp = SoraPlayers.get(player);
        sp.add("santory_player_skill_execute_times", 1);
    }

    @EventHandler
    public void onWish(PlayerWishRollEvent e) {
        var player = e.getPlayer();
        var sp = SoraPlayers.get(player);
        sp.add("santory_player_wish_roll_times", 1);
    }

    @EventHandler
    public void onItemEnhance(PlayerItemEnhanceEvent e) {
        if (!e.isSuccess()) return;
        var player = e.getPlayer();
        var sp = SoraPlayers.get(player);
        sp.add("santory_player_item_enhance_success_times", 1);
    }

    @EventHandler
    public void onItemUpgrade(PlayerItemUpgradeEvent e) {
        if (!e.isSuccess()) return;
        var player = e.getPlayer();
        var sp = SoraPlayers.get(player);
        sp.add("santory_player_item_upgrade_success_times", 1);
    }

    @EventHandler
    public void onItemAcent(PlayerItemAscentEvent e) {
        if (!e.isSuccess()) return;
        var player = e.getPlayer();
        var sp = SoraPlayers.get(player);
        sp.add("santory_player_item_ascent_success_times", 1);
    }

}
