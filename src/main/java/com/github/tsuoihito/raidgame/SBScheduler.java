package com.github.tsuoihito.raidgame;

import com.github.tsuoihito.raidgame.objects.Team;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SBScheduler extends BukkitRunnable {

    private final RaidGame plugin;

    public SBScheduler(RaidGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        if (!plugin.isInGame()) {

            for (Player player : plugin.getServer().getOnlinePlayers()) {
                for (Team team : plugin.getTeams()) {
                    if (team.getMembers().stream().anyMatch(m -> m.equalsIgnoreCase(player.getName()))) {
                        player.setScoreboard(plugin.getRgScoreBoard().getScoreBoard(team));
                        return;
                    }
                }
                player.setScoreboard(plugin.getRgScoreBoard().getEmptyScoreboard());
            }

            return;
        }

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (plugin.getGameState().getTeam().getMembers().stream().anyMatch(m -> m.equalsIgnoreCase(player.getName()))) {
                player.setScoreboard(plugin.getRgScoreBoard().getScoreBoard(plugin.getGameState().getTeam()));
            } else {
                player.setScoreboard(plugin.getRgScoreBoard().getEmptyScoreboard());
            }
        }

        plugin.getGameState().getTeam().getGameResult().increaseElapsedTime();

    }
}
