package com.example.glowhub.ui.services;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ServiceRepository {
    private ServiceDao serviceDao;
    private LiveData<List<ServiceModel>> allServices;

    public ServiceRepository(Application application) {
        ServiceDatabase db = ServiceDatabase.getDatabase(application);
        serviceDao = db.serviceDao();
        allServices = serviceDao.getAllServices();
    }

    public LiveData<List<ServiceModel>> getAllServices() {
        return allServices;
    }

    public void insertService(ServiceModel service) {
        ServiceDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.insertService(service);
        });
    }

    public void updateService(ServiceModel service) {
        ServiceDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.updateService(service);
        });
    }

    public void deleteService(ServiceModel service) {
        ServiceDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.deleteService(service);
        });
    }
}

