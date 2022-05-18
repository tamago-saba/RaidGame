package com.github.tsuoihito.raidgame.objects;

public class GameState {

    public final Team team;

    public GameState(Team team) {
        this.team = team;
        this.team.clearGameResult();
    }

    public Team getTeam() {
        return team;
    }

}
