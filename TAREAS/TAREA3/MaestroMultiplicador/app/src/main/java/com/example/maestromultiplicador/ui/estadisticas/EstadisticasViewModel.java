package com.example.maestromultiplicador.ui.estadisticas;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class EstadisticasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EstadisticasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el Fragment Estadisticas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}