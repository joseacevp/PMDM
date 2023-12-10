package com.example.maestromultiplicador.ui.entrenar;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class EntrenarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EntrenarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el Fragment Entrenar");
    }

    public LiveData<String> getText() {
        return mText;
    }
}