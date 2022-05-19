package com.github.tsuoihito.raidgame.utils;

import com.github.tsuoihito.raidgame.RaidGame;
import com.github.tsuoihito.raidgame.objects.GameResult;

import java.util.Optional;

public class GameResultManager {

    private final RaidGame plugin;

    public GameResultManager(RaidGame plugin) {
        this.plugin = plugin;
    }

    public Optional<GameResult> getGameResult(String teamName) {
        for (GameResult gameResult : plugin.getGameResults()) {
            if (gameResult.getTeamName().equalsIgnoreCase(teamName)) {
                return Optional.of(gameResult);
            }
        }
        return Optional.empty();
    }

    public boolean isNameNowInGame(String name) {
        return
                plugin.isInGame() &&
                plugin.getTeamManager().getTeamOfMember(name).isPresent() &&
                plugin.getTeamManager().getTeamOfMember(name).get().getTeamName().equalsIgnoreCase(plugin.getNowGameResult().getTeamName());
    }
}
