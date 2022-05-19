package com.github.tsuoihito.raidgame.utils;

import com.github.tsuoihito.raidgame.RaidGame;
import com.github.tsuoihito.raidgame.objects.GameResult;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public boolean isNameInGame(String name) {
        return
                plugin.isInGame() &&
                plugin.getTeamManager().getTeamOfMember(name).isPresent() &&
                plugin.getTeamManager().getTeamOfMember(name).get().getTeamName().equalsIgnoreCase(plugin.getNowGameResult().getTeamName());
    }

    public List<GameResult> getSortedGameResults() {
        return plugin.gameResults.stream().filter(GameResult::isWin).sorted(Comparator.comparing(GameResult::getDeathCount).thenComparing(GameResult::getElapsedTime)).collect(Collectors.toList());
    }

}
