package com.example.glowhub.ui.services;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ServiceModel.class}, version = 1, exportSchema = false)
public abstract class ServiceDatabase extends RoomDatabase {

    public abstract ServiceDao serviceDao();

    private static volatile ServiceDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ServiceDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ServiceDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ServiceDatabase.class, "service_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
