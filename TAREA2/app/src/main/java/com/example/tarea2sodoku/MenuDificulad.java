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
        botonNormal = findViewById(R.id.radioButtonNormal);
        botonDificil = findViewById(R.id.radioButtonDificil);
        ingresarDifi = findViewById(R.id.textViewVolver);
        seleccion();
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

//    private void cargarDificultad() {
//        SharedPreferences preferencias = getSharedPreferences
//                ("nivelDificultad", Context.MODE_PRIVATE);
//        dificultad = preferencias.getInt("dificultad",20);
//        Log.i("info","dificultad recuperada: "+dificultad);
//    }

    private void seleccion() {
        if (botonFacil.isChecked()) {
            dificultad = 10;//diez casillas vacias nivel Facil
        }
        if (botonNormal.isChecked()) {
            dificultad = 20;//veinte casillas vacias nivel Normal
        }
        if (botonDificil.isChecked()) {//treintra casillas vacias nivel Dificil
            dificultad = 30;
        }
    }

    @Override
    public void onClick(View view) {
        seleccion();
        enviarDificultad(dificultad);
        Log.i("info", "dificultad " + dificultad);
        Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intencion);
        finish();
    }
}