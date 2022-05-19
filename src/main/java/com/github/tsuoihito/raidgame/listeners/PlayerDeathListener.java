package com.github.tsuoihito.raidgame.listeners;

import com.github.tsuoihito.raidgame.RaidGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final RaidGame plugin;

    public PlayerDeathListener(RaidGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnPlayerDeath(PlayerDeathEvent e) {

        if (!plugin.isInGame()) {
            return;
        }

        if (!plugin.getGameResultManager().isNameInGame(e.getEntity().getName())) {
            return;
        }

        plugin.getNowGameResult().increaseDeathCount();

    }
}
