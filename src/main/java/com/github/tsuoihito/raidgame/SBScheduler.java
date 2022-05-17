package com.github.tsuoihito.raidgame;

import org.bukkit.scheduler.BukkitRunnable;

public class SBScheduler extends BukkitRunnable {

    private final RaidGame plugin;

    public SBScheduler(RaidGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        if (!plugin.isInGame()) {
            plugin.getRgScoreBoard().removeScoreBoard();
            cancel();
            return;
        }

        plugin.getRgScoreBoard().loadScoreBoard();

    }
}
