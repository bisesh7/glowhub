package com.example.glowhub.ui.services;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "services")
public class ServiceModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String serviceName;
    private double servicePrice;

    public ServiceModel(String serviceName, double servicePrice) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
}
