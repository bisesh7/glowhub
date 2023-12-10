package com.example.glowhub.ui.rewards;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class RewardRepository {
    private RewardDao rewardDao;
    private LiveData<List<RewardModel>> allRewards;

    public RewardRepository(Application application) {
        RewardDatabase db = RewardDatabase.getDatabase(application);
        rewardDao = db.rewardDao();
        allRewards = rewardDao.getAllRewards();
    }

    public LiveData<List<RewardModel>> getAllRewards() {
        return allRewards;
    }

    public void insertReward(RewardModel reward) {
        RewardDatabase.databaseWriteExecutor.execute(() -> {
            rewardDao.insertReward(reward);
        });
    }

    public void updateReward(RewardModel reward) {
        RewardDatabase.databaseWriteExecutor.execute(() -> {
            rewardDao.updateReward(reward);
        });
    }

    public void deleteReward(RewardModel reward) {
        RewardDatabase.databaseWriteExecutor.execute(() -> {
            rewardDao.deleteReward(reward);
        });
    }
}

