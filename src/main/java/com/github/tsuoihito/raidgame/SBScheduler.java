package com.github.tsuoihito.raidgame;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SBScheduler extends BukkitRunnable {

    private final RaidGame plugin;

    public SBScheduler(RaidGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            plugin.getTeamManager().getTeamOfMember(player.getName()).ifPresentOrElse(t -> player.setScoreboard(plugin.getRgScoreBoard().getScoreBoard(t)), () -> player.setScoreboard(plugin.getRgScoreBoard().getEmptyScoreboard()));
        }

        if (plugin.isInGame()) {
            plugin.getNowGameResult().increaseElapsedTime();
        }

    }
}
