package com.example.glowhub.ui.rewards;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RewardModel.class}, version = 1, exportSchema = false)
public abstract class RewardDatabase extends RoomDatabase {

    public abstract RewardDao rewardDao();

    private static volatile RewardDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RewardDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RewardDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RewardDatabase.class, "reward_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
