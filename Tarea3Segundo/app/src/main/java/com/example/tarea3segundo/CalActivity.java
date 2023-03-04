package com.example.tarea3segundo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class CalActivity extends AppCompatActivity implements View.OnClickListener{
    Bundle bundle = new Bundle();
    String fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);


        Button boton = this.findViewById(R.id.botonFecha);
        if (fecha == null) {
            boton.setVisibility(View.INVISIBLE);
            elegirFecha();
        } else {
            boton.setVisibility(View.VISIBLE);

        }

    }
    public void elegirFecha() {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                //System.out.println(fecha);
                bundle.putString("fechaKey", fecha);
               // getApplicationContext().("key", bundle);

                //Toast.makeText(getContext(),fecha, Toast.LENGTH_LONG).show();
                //enviar dato a mailFragment

            }
        }, anio, mes, dia);
        dpd.show();
    }

    @Override
    public void onClick(View v) {

    }
}