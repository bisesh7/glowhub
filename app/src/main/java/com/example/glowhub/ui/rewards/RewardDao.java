package com.example.glowhub.ui.rewards;

import androidx.lifecycle.LiveData;
        import androidx.room.Dao;
        import androidx.room.Delete;
        import androidx.room.Insert;
        import androidx.room.Query;
        import androidx.room.Update;

        import java.util.List;

@Dao
public interface RewardDao {
    @Query("SELECT * FROM rewards")
    LiveData<List<RewardModel>> getAllRewards();

    @Insert
    void insertReward(RewardModel reward);

    @Update
    void updateReward(RewardModel reward);

    @Delete
    void deleteReward(RewardModel reward);
}

