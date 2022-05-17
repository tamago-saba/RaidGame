package com.github.tsuoihito.raidgame;

import com.github.tsuoihito.raidgame.listeners.PlayerDeathListener;
import com.github.tsuoihito.raidgame.listeners.RaidFinishListener;
import com.github.tsuoihito.raidgame.listeners.RaiderDamageListener;
import com.github.tsuoihito.raidgame.objects.GameState;
import com.github.tsuoihito.raidgame.objects.Team;
import com.github.tsuoihito.raidgame.utils.RGScoreBoard;
import com.github.tsuoihito.raidgame.utils.TeamManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class RaidGame extends JavaPlugin {

    public boolean inGame;
    public Location rgBase;
    public GameState gameState;
    public final List<Team> teams = new ArrayList<>();
    public final TeamManager teamManager = new TeamManager(this);
    public final RGScoreBoard rgScoreBoard = new RGScoreBoard(this);

    @Override
    public void onEnable() {

        inGame = false;

        register();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void register() {

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new RaidFinishListener(this), this);
        getServer().getPluginManager().registerEvents(new RaiderDamageListener(this), this);

        getCommand("raidgame").setExecutor(new RGCommandExecutor(this));

    }

    public void saveGameResult() {
        if (teams.removeIf(t -> t.getTeamName().equalsIgnoreCase(gameState.getTeam().getTeamName()))) {
            teams.add(gameState.getTeam());
        }
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Optional<Location> getRgBase() {
        return Optional.ofNullable(rgBase);
    }

    public void setRgBase(Location rgBase) {
        this.rgBase = rgBase;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public RGScoreBoard getRgScoreBoard() {
        return rgScoreBoard;
    }
}
