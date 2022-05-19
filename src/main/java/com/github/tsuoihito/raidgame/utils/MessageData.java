package com.github.tsuoihito.raidgame.utils;

import com.github.tsuoihito.raidgame.objects.GameResult;

import java.util.List;
import java.util.stream.Collectors;

public class MessageData {

    public String getTotalResultMessage(List<GameResult> gameResults) {
        int i = 0;
        StringBuilder totalTeamResultMessage = new StringBuilder();
        for (GameResult gameResult : gameResults) {
            i ++;
            totalTeamResultMessage.append(getTotalTeamResultTemplate().replace("%rank%", Integer.toString(i)).replace("%teamName%", gameResult.getTeamName())).append("\n");
        }
        return getTotalResultTemplate().replace("%teamResult%", totalTeamResultMessage.toString().trim());
    }

    private String getTotalTeamResultTemplate() {
        return "§e%rank%位: §a%teamName%";
    }

    private String getTotalResultTemplate() {
        return
                "§b=== TOTAL RESULT ===\n" +
                "%teamResult%"
                ;
    }

    public String getTeamResultMessage(GameResult gameResult) {
        return getTeamResultTemplate()
                .replace("%teamName%", gameResult.getTeamName())
                .replace("%deathCount%", gameResult.getDeathCount().toString())
                .replace("%elapsedTime%", getHumanTime(gameResult.getElapsedTime()))
                .replace("%memberResult%", getMemberResultMessage(gameResult));
    }

    public String getMemberResultMessage(GameResult gameResult) {
        return gameResult.getDamageScore().entrySet().stream().map(e -> getMemberResultTemplate().replace("%member%", e.getKey()).replace("%damageScore%", e.getValue().toString())).collect(Collectors.joining("\n"));
    }

    private String getTeamResultTemplate() {
        return
                "§b=== §a%teamName% §bRESULT ===\n" +
                "§e合計死亡回数: §c%deathCount%回\n" +
                "§e経過時間: §c%elapsedTime%\n" +
                "%memberResult%"
                ;
    }

    private String getMemberResultTemplate() {
        return "§a%member% §c%damageScore%ダメージ";
    }

    private String getHumanTime(Integer second) {
        return "%m%分%s%秒"
                .replace("%m%", Integer.toString(second / 60))
                .replace("%s%", Integer.toString(second % 60));
    }

}
