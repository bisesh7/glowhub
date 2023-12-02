package com.example.glowhub.ui.rewards;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "rewards")
public class RewardModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String rewardName;
    private double rewardPoint;

    public RewardModel(String rewardName, double rewardPoint) {
        this.rewardName = rewardName;
        this.rewardPoint = rewardPoint;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public double getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(double rewardPrice) {
        this.rewardPoint = rewardPrice;
    }
}
