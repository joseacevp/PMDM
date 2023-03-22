package com.example.tarea3v3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import com.example.tarea3v3.databinding.ActivityCalendarioBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarioActivity  extends DialogFragment  implements DatePickerDialog.OnDateSetListener{

    private ActivityCalendarioBinding binding;

    private Bundle bundle = new Bundle();
    OnFechaSeleccionada fechaSeleccionada;

    @Override
    public void onAttach(Activity activity) {
        fechaSeleccionada = (OnFechaSeleccionada)activity;
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int año = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,año,mes,dia);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
//        GregorianCalendar g = new GregorianCalendar(year, month, dayOfMonth);
        fechaSeleccionada.onResultadoFecha(fecha);
    }


    public interface OnFechaSeleccionada{
        public void onResultadoFecha(String fecha);
    }


}