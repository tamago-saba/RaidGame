package com.github.tsuoihito.raidgame.objects;


import java.util.ArrayList;
import java.util.List;

public class Team {

    public final String teamName;
    public final List<String> members;

    public Team(String teamName) {
        this.teamName = teamName;
        members = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getMembers() {
        return members;
    }

}
