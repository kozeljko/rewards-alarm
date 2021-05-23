package com.better.alarm.model;

public class Rewards {
    private Integer rewardsPoints = 1000;

    public void increaseRewardPoints(Integer increase) {
        rewardsPoints += increase;
    }

    public void decreaseRewardPoints(Integer decrease) {
        rewardsPoints -= decrease;
    }

    public Integer getRewardPoints() {
        return rewardsPoints;
    }
}
