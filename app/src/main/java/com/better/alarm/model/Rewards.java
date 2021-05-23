package com.better.alarm.model;

public class Rewards {
    private Integer rewardsPoints = 1000;
    private Integer streak = 0;
    private boolean[] purchases = new boolean[4];

    public void incrementStreak(int points) {
        streak++;
        rewardsPoints += points * streak;
    }

    public void endStreak() {
        streak = 0;
    }

    public void increaseRewardPoints(Integer increase) {
        rewardsPoints += increase;
    }

    public void decreaseRewardPoints(Integer decrease) {
        rewardsPoints -= decrease;
    }

    public Integer getRewardPoints() {
        return rewardsPoints;
    }

    public Boolean getPurchase(int index) {
        return purchases[index];
    }

    public void purchase(int index) {
        purchases[index] = true;
    }
}
