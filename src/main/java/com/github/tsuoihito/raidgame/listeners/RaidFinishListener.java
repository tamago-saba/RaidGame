package com.github.tsuoihito.raidgame.listeners;

import com.github.tsuoihito.raidgame.RaidGame;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidFinishEvent;

public class RaidFinishListener implements Listener {

    public final RaidGame plugin;

    public RaidFinishListener(RaidGame plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onRaidFinish(RaidFinishEvent e) {

        if (!plugin.isInGame()) {
            return;
        }

        plugin.getGameState().getTeam().getGameResult().setWin(true);

        for (Player player : plugin.getServer().getOnlinePlayers()) {

            player.sendTitle("You win!", "", 20, 60, 20);
            player.sendMessage(plugin.getMessageData().getTeamResult(plugin.gameState.getTeam()));

            plugin.getRgBase().ifPresent(player::teleport);
            player.setGameMode(GameMode.SURVIVAL);

        }

        plugin.setInGame(false);
        plugin.saveGameResult();

    }
}
