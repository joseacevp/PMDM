package com.example.tarea4v2.ui.configurar;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tarea4v2.Partida;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class ConfigurarViewModel extends ViewModel {

    private String heroe, dificultad, fechaSeleccionada,numeroTabla;
    Partida partida;

    private final MutableLiveData<String> mText;

    public ConfigurarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    private String recuperarFechaActual() {
        // Obtener la instancia de Calendar y establecer la fecha actual
        Calendar calendar = Calendar.getInstance();

        // Crear un formato de fecha deseado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Formatear la fecha actual
        String fechaActual = dateFormat.format(calendar.getTime());

        return fechaActual;
    }
    private void guardarPreferencias() {


        Random random = new Random();
        int numeroAleatorio = random.nextInt(10) + 1;

        SharedPreferences preferencias = getContext().getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("heroe", heroe);
        editor.putString("dificultad", dificultad);
        editor.putString("fecha", fechaSeleccionada);
        editor.putString("tabla", numeroTabla);
        editor.putString("aleatorio", String.valueOf(numeroAleatorio));

        editor.commit();
    }
    private void cargarPreferencias() {
        SharedPreferences preferencias = getActivity().getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);
        heroe = preferencias.getString("heroe", "Capitan America");


    }

}
