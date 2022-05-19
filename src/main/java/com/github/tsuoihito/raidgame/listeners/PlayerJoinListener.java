package com.github.tsuoihito.raidgame.listeners;

import com.github.tsuoihito.raidgame.RaidGame;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    public final RaidGame plugin;

    public PlayerJoinListener(RaidGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent e) {

        if (!plugin.isInGame()) {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
            return;
        }

        if (plugin.getGameResultManager().isNameInGame(e.getPlayer().getName())) {
            e.getPlayer().setGameMode(GameMode.ADVENTURE);
            return;
        }

        e.getPlayer().setGameMode(GameMode.SPECTATOR);

    }
}
