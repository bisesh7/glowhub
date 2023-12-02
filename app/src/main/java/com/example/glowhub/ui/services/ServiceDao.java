package com.example.glowhub.ui.services;

import androidx.lifecycle.LiveData;
        import androidx.room.Dao;
        import androidx.room.Delete;
        import androidx.room.Insert;
        import androidx.room.Query;
        import androidx.room.Update;

        import java.util.List;

@Dao
public interface ServiceDao {
    @Query("SELECT * FROM services")
    LiveData<List<ServiceModel>> getAllServices();

    @Insert
    void insertService(ServiceModel service);

    @Update
    void updateService(ServiceModel service);

    @Delete
    void deleteService(ServiceModel service);
}

