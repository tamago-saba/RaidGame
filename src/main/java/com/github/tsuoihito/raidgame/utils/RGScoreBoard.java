package com.github.tsuoihito.raidgame.utils;


import com.github.tsuoihito.raidgame.RaidGame;
import com.github.tsuoihito.raidgame.objects.Team;
import org.bukkit.scoreboard.*;

public class RGScoreBoard {

    private final ScoreboardManager scoreboardManager;

    public RGScoreBoard(RaidGame plugin) {
        scoreboardManager = plugin.getServer().getScoreboardManager();
    }

    // Only during a game
    public Scoreboard getScoreBoard(Team team) {

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("RG", "dummy", "RG");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(team.getTeamName());

        team.getMembers().forEach(m -> objective.getScore(m).setScore(team.getGameResult().getDamageScore().getOrDefault(m, 0)));

        return scoreboard;

    }

    public Scoreboard getEmptyScoreboard() {
        return scoreboardManager.getNewScoreboard();
    }

}
