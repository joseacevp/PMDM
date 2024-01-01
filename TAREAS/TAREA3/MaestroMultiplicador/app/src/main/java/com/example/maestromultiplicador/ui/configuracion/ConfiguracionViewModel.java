package com.example.maestromultiplicador.ui.configuracion;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ConfiguracionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConfiguracionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el Fragment Configuración");
    }

    public LiveData<String> getText() {
        return mText;
    }
}