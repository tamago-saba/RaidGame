package com.github.tsuoihito.raidgame;

import com.github.tsuoihito.raidgame.listeners.PlayerDeathListener;
import com.github.tsuoihito.raidgame.listeners.PlayerJoinListener;
import com.github.tsuoihito.raidgame.listeners.RaidFinishListener;
import com.github.tsuoihito.raidgame.listeners.RaiderDamageListener;
import com.github.tsuoihito.raidgame.objects.GameResult;
import com.github.tsuoihito.raidgame.objects.Team;
import com.github.tsuoihito.raidgame.utils.GameResultManager;
import com.github.tsuoihito.raidgame.utils.MessageData;
import com.github.tsuoihito.raidgame.utils.RGScoreBoard;
import com.github.tsuoihito.raidgame.utils.TeamManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class RaidGame extends JavaPlugin {

    public Location rgBase;

    public GameResult nowGameResult = null;
    public final List<Team> teams = new ArrayList<>();
    public final List<GameResult> gameResults = new ArrayList<>();

    public final TeamManager teamManager = new TeamManager(this);
    public final GameResultManager gameResultManager = new GameResultManager(this);
    public final RGScoreBoard rgScoreBoard = new RGScoreBoard(this);
    public final MessageData messageData = new MessageData();

    @Override
    public void onEnable() {

        register();
        runScheduler();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void register() {

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new RaidFinishListener(this), this);
        getServer().getPluginManager().registerEvents(new RaiderDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        getCommand("raidgame").setExecutor(new RGCommandExecutor(this));

    }

    private void runScheduler() {
        new SBScheduler(this).runTaskTimer(this, 0, 20);
    }

    public void saveGameResult() {
        if (nowGameResult != null) {
            gameResults.add(nowGameResult);
        }
    }

    public Optional<Location> getRgBase() {
        return Optional.ofNullable(rgBase);
    }

    public void setRgBase(Location rgBase) {
        this.rgBase = rgBase;
    }

    public GameResult getNowGameResult() {
        return nowGameResult;
    }

    public boolean isInGame() {
        return nowGameResult != null;
    }

    public void startGame(String teamName) {
        nowGameResult = new GameResult(teamName);
    }

    public void stopGame() {
        nowGameResult = null;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public GameResultManager getGameResultManager() {
        return gameResultManager;
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }

    public RGScoreBoard getRgScoreBoard() {
        return rgScoreBoard;
    }

    public MessageData getMessageData() {
        return messageData;
    }
}
