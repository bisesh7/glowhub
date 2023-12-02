package com.example.glowhub.ui.rewards;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class RewardViewModel extends AndroidViewModel {

    private RewardRepository rewardRepository;
    private LiveData<List<RewardModel>> allRewards;

    public RewardViewModel(Application application) {
        super(application);
        rewardRepository = new RewardRepository(application);
        allRewards = rewardRepository.getAllRewards();
    }

    public LiveData<List<RewardModel>> getAllRewards() {
        return allRewards;
    }

    public void insert(RewardModel reward) {
        rewardRepository.insertReward(reward);
    }

    public void update(RewardModel reward) {
        rewardRepository.updateReward(reward);
    }

    public void delete(RewardModel reward) {
        rewardRepository.deleteReward(reward);
    }
}
