package com.example.tarea3segundo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.tarea3segundo.databinding.ActivityCalBinding;

import java.util.Calendar;

public class CalActivity extends AppCompatActivity {
    Bundle bundle = new Bundle();
    String fecha;
    private ActivityCalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (fecha == null) {
            binding.botonFecha.setVisibility(View.INVISIBLE);
            elegirFecha();
        } else {
            binding.botonFecha.setVisibility(View.VISIBLE);

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
                //bundle.putString("fechaKey", fecha);
               //getApplicationContext().("key", bundle);

                Toast.makeText(getApplicationContext(),fecha, Toast.LENGTH_LONG).show();
                //enviar dato a mailFragment
                Intent intent = new Intent(getApplicationContext(),BiciActivity.class);
                intent.putExtra("fechaKey",fecha);
                // Los resultados se devuelven a través de un Intent invocando al método setResult()
                setResult(RESULT_OK,intent);

                // Se finaliza la actividad invocando al método finish()
                //finish();
            }
        }, anio, mes, dia);
        dpd.show();
    }


}