package com.example.glowhub.ui.appointments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppointmentsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AppointmentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is appointment fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}