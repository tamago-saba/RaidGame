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

        plugin.getNowGameResult().setWin(true);

        for (Player player : plugin.getServer().getOnlinePlayers()) {

            player.sendMessage(plugin.getMessageData().getTeamResultMessage(plugin.getNowGameResult()));

            plugin.getRgBase().ifPresent(player::teleport);
            player.setGameMode(GameMode.SURVIVAL);

        }

        plugin.saveGameResult();
        plugin.stopGame();

    }
}
