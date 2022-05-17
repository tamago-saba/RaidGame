package com.github.tsuoihito.raidgame.utils;


import com.github.tsuoihito.raidgame.RaidGame;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class RGScoreBoard {

    private final RaidGame plugin;
    private final ScoreboardManager scoreboardManager;

    public RGScoreBoard(RaidGame plugin) {
        this.plugin = plugin;
        scoreboardManager = plugin.getServer().getScoreboardManager();
    }

    // Only during a game
    public void loadScoreBoard() {

        if (scoreboardManager == null) {
            return;
        }

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("RG", "dummy", "RG");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(plugin.getGameState().getTeam().getTeamName());

        plugin.getGameState().getTeam().getMembers().forEach(m -> objective.getScore(m).setScore(plugin.getGameState().getTeam().getGameResult().getDamageScore().getOrDefault(m, 0)));

        plugin.getServer().getOnlinePlayers().forEach(p -> p.setScoreboard(scoreboard));

    }

    public void removeScoreBoard() {

        plugin.getServer().getOnlinePlayers().forEach(p -> p.setScoreboard(scoreboardManager.getNewScoreboard()));

    }
}
