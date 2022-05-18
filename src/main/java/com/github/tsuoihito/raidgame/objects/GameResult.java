package com.github.tsuoihito.raidgame.objects;

import java.util.HashMap;
import java.util.Map;

public class GameResult {

    public Map<String, Integer> damageScore;
    public Integer deathCount;
    public Integer elapsedTime;
    public boolean win;

    public GameResult() {
        damageScore = new HashMap<>();
        deathCount = 0;
        elapsedTime = 0;
        win = false;
    }

    public void addDamageScore(String member, Integer additionalDamageScore) {
        if (damageScore.get(member) == null) {
            damageScore.put(member, additionalDamageScore);
            return;
        }
        damageScore.put(member, damageScore.get(member) + additionalDamageScore);
    }

    public Map<String, Integer> getDamageScore() {
        return damageScore;
    }

    public void increaseDeathCount() {
        deathCount ++;
    }

    public Integer getDeathCount() {
        return deathCount;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void increaseElapsedTime() {
        elapsedTime ++;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

}
