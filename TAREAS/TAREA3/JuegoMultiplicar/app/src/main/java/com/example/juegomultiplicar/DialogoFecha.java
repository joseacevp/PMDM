package com.example.juegomultiplicar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DialogoFecha extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private OnFechaSeleccionada fechaSeleccionadaListener;

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        GregorianCalendar fecha = new GregorianCalendar(i, i1, i2);
        fechaSeleccionadaListener.onResultadoFecha(fecha);
    }
    public interface OnFechaSeleccionada {
        public void onResultadoFecha(GregorianCalendar fecha);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,ano,mes,dia);
    }
    public void setFechaSeleccionadaListener(OnFechaSeleccionada listener) {
        fechaSeleccionadaListener = listener;
    }
}
