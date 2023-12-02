package com.example.glowhub.ui.appointment;

public class Appointment {
    private String title;
    private String dateTime;
    // Add more fields as needed
    public Appointment(){};
    public Appointment(String title, String dateTime /* add more fields */) {
        this.title = title;
        this.dateTime = dateTime;
        // Initialize other fields
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    // Add getters and setters for additional fields if added
}
