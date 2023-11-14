package com.example.tarea2sodoku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MenuDificulad extends AppCompatActivity implements View.OnClickListener {
    RadioButton botonFacil, botonNormal, botonDificil;
    TextView ingresarDifi;
    int dificultad = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dificulad);

        botonFacil = findViewById(R.id.radioButtonFacil);
        botonFacil.setOnClickListener(this);
        botonNormal = findViewById(R.id.radioButtonNormal);
        botonNormal.setOnClickListener(this);
        botonDificil = findViewById(R.id.radioButtonDificil);
        botonDificil.setOnClickListener(this);
        ingresarDifi = findViewById(R.id.textViewVolver);
    }

    private void enviarDificultad(int dificultad) {
        SharedPreferences preferencias = getSharedPreferences
                ("nivelDificultad", Context.MODE_PRIVATE);
        //alamacenamos los datos de los campos
        //introduciomos los en el archivo XML con un editor.
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt("dificultad", dificultad);
        Log.i("info", "dificultad almacenada: " + dificultad);
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioButtonFacil:
                dificultad = 10;
                break;
            case R.id.radioButtonNormal:
                dificultad = 20;
                break;
            case R.id.radioButtonDificil:
                dificultad = 30;
                break;
            case R.id.textViewVolver:
                enviarDificultad(dificultad);
                Log.i("info", "dificultad " + dificultad);
                Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intencion);
                break;
        }
//
    }
}