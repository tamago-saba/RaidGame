package com.github.tsuoihito.raidgame.utils;

import com.github.tsuoihito.raidgame.objects.Team;

import java.util.stream.Collectors;

public class MessageData {

    public String getTeamResult(Team team) {
        return getTeamResultTemplate()
                .replace("%teamName%", team.getTeamName())
                .replace("%deathCount%", team.getGameResult().getDeathCount().toString())
                .replace("%elapsedTime%", team.getGameResult().getElapsedTime().toString())
                .replace("%memberResult%", getMemberResult(team));
    }

    public String getMemberResult(Team team) {
        return team.getMembers().stream().map(m -> getMemberResultTemplate().replace("%member%", m).replace("%damageScore%", team.getGameResult().getDamageScore().getOrDefault(m, 0).toString())).collect(Collectors.joining("\n"));
    }

    public String getTeamResultTemplate() {
        return
                "=== %teamName% RESULT ===\n" +
                "合計死亡回数: %deathCount%\n" +
                "経過時間: %elapsedTime%\n" +
                "%memberResult%"
                ;
    }

    public String getMemberResultTemplate() {
        return "%member% %damageScore%";
    }
}
