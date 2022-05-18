package com.github.tsuoihito.raidgame.objects;


import com.github.tsuoihito.raidgame.objects.GameResult;

import java.util.*;

public class Team {

    public final String teamName;
    public final List<String> members;
    public GameResult gameResult;

    public Team(String teamName) {
        this.teamName = teamName;
        members = new ArrayList<>();
        gameResult = new GameResult();
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getMembers() {
        return members;
    }

    public void addMember(String member) {
        this.members.add(member);
    }

    public void removeMember(String member) {
        this.members.removeIf(p -> p.equalsIgnoreCase(member));
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void clearGameResult() {
        gameResult = new GameResult();
    }

}
