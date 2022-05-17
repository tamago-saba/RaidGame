package com.github.tsuoihito.raidgame.utils;

import com.github.tsuoihito.raidgame.RaidGame;
import com.github.tsuoihito.raidgame.objects.Team;

import java.util.Optional;

public class TeamManager {

    private final RaidGame plugin;

    public TeamManager(RaidGame plugin) {
        this.plugin = plugin;
    }

    public Optional<Team> getTeam(String teamName) {
        for (Team team : plugin.getTeams()) {
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                return Optional.of(team);
            }
        }
        return Optional.empty();
    }

    public boolean addTeam(String teamName) {
        if (plugin.getTeams().stream().anyMatch(t -> t.getTeamName().equalsIgnoreCase(teamName))) {
            return false;
        }

        Team team = new Team(teamName);
        plugin.getTeams().add(team);

        return true;
    }

    public boolean removeTeam(String teamName) {
        return plugin.getTeams().removeIf(t -> t.getTeamName().equalsIgnoreCase(teamName));
    }

    public boolean addTeamMember(String teamName, String member) {
        int i = -1;
        for (Team team : plugin.getTeams()) {
            i ++;
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                plugin.getTeams().get(i).addMember(member);
                return true;
            }
        }
        return false;
    }

    public boolean removeTeamMember(String teamName, String member) {
        int i = -1;
        for (Team team : plugin.getTeams()) {
            i ++;
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                plugin.getTeams().get(i).removeMember(member);
                return true;
            }
        }
        return false;
    }

}
