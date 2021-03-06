package com.github.tsuoihito.raidgame.utils;


import com.github.tsuoihito.raidgame.RaidGame;
import com.github.tsuoihito.raidgame.objects.Team;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class RGScoreBoard {

    private final ScoreboardManager scoreboardManager;

    public RGScoreBoard(RaidGame plugin) {
        scoreboardManager = plugin.getServer().getScoreboardManager();
    }

    public Scoreboard getScoreBoard(Team team) {

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("RG", "dummy", "RG");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§a%teamName%".replace("%teamName%", team.getTeamName()));

        team.getMembers().forEach(m -> objective.getScore(m).setScore(0));

        return scoreboard;

    }

    public Scoreboard getEmptyScoreboard() {
        return scoreboardManager.getNewScoreboard();
    }

}
