package com.github.tsuoihito.raidgame.listeners;

import com.github.tsuoihito.raidgame.RaidGame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Raider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class RaiderDamageListener implements Listener {

    private final RaidGame plugin;

    public RaiderDamageListener(RaidGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {

        if (!plugin.isInGame()) {
            return;
        }

        if (!(e.getEntity() instanceof Raider)) {
            return;
        }

        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        if (!plugin.getGameResultManager().isNameInGame(e.getDamager().getName())) {
            return;
        }

        plugin.getNowGameResult().addDamageScore(e.getDamager().getName(), (int) e.getDamage());

    }
}
