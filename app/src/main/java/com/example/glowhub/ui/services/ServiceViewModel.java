package com.example.glowhub.ui.services;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ServiceViewModel extends AndroidViewModel {

    private ServiceRepository serviceRepository;
    private LiveData<List<ServiceModel>> allServices;

    public ServiceViewModel(Application application) {
        super(application);
        serviceRepository = new ServiceRepository(application);
        allServices = serviceRepository.getAllServices();
    }

    public LiveData<List<ServiceModel>> getAllServices() {
        return allServices;
    }

    public void insert(ServiceModel service) {
        serviceRepository.insertService(service);
    }

    public void update(ServiceModel service) {
        Log.d("UpdateService", "Updating service: " + service.toString());
        serviceRepository.updateService(service);
        Log.d("UpdateService", "Service updated successfully");
    }

    public void delete(ServiceModel service) {
        serviceRepository.deleteService(service);
    }
}
