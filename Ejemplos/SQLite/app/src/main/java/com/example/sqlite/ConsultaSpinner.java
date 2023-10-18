package com.example.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ConsultaSpinner extends AppCompatActivity {

    Spinner comboPersonas;
    EditText id,nombre,telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);
        comboPersonas = findViewById(R.id.idSpinner);
        id = findViewById(R.id.editTextid);
        nombre = findViewById(R.id.editTextNombre);
        telefono = findViewById(R.id.editTextTelefono);

        ArrayAdapter<CharSequence>adapter =ArrayAdapter.createFromResource(this,R.array.comboSpinner,
                android.R.layout.simple_spinner_item);
        comboPersonas.setAdapter(adapter);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Seleccionado: "+ adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}