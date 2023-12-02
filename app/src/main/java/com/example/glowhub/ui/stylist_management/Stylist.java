package com.example.glowhub.ui.stylist_management;

public class Stylist {
    private String name;
    private String specialization;

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Stylist(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }
}
