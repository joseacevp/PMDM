package com.example.menussemanales;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class DialogoCalorias extends DialogFragment {
    private String calorias = "";

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



// Usamos la clase Builder para construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Escribimos el título
        builder.setTitle("Calorias del Menu Semanal:");
        //Escribimos la pregunta
        builder.setMessage("Recuerda que el ejercicio regular ayuda a \n" +
                "tratar la depresión y la ansiedad; reduce el nivel " +
                "de estrés y ayuda a dormir mejor.\n" +
                "Es probable que te sientas más feliz, más satisfecho con la " +
                "vida y que mejore tu sensación de bienestar sieres físicamente " +
                "activo.\n" +
                "Las calorías totales de tu menú son..."+ calorias);
        // Crear el AlertDialog y devolverlo
        return builder.create();
    }

    // Método para mostrar el diálogo
    private void show(FragmentManager fragmentManager, String mi_dialogo) {

        show(fragmentManager, mi_dialogo);
    }
}