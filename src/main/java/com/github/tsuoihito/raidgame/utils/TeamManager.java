package com.github.tsuoihito.raidgame.utils;

import com.github.tsuoihito.raidgame.RaidGame;
import com.github.tsuoihito.raidgame.objects.Team;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    public List<String> getTeamNameList() {
        return plugin.getTeams().stream().map(Team::getTeamName).collect(Collectors.toList());
    }

    public boolean isNameInTeam(String name, String teamName) {
        return getTeam(teamName).isPresent() && getTeam(teamName).get().getMembers().stream().anyMatch(name::equalsIgnoreCase);
    }

    public Optional<Team> getTeamOfMember(String member) {
        for (Team team : plugin.getTeams()) {
            if (team.getMembers().stream().anyMatch(member::equalsIgnoreCase)) {
                return Optional.of(team);
            }
        }
        return Optional.empty();
    }

    public boolean addTeam(String teamName) {
        return plugin.getTeams().stream().noneMatch(t -> t.getTeamName().equalsIgnoreCase(teamName)) && plugin.getTeams().add(new Team(teamName));
    }

    public boolean removeTeam(String teamName) {
        return plugin.getTeams().removeIf(t -> t.getTeamName().equalsIgnoreCase(teamName));
    }

    public boolean addTeamMember(String teamName, String member) {
        return getTeam(teamName).isPresent() && getTeam(teamName).get().getMembers().add(member);
    }

    public boolean removeTeamMember(String teamName, String member) {
        return getTeam(teamName).isPresent() && getTeam(teamName).get().getMembers().removeIf(member::equalsIgnoreCase);
    }

}
