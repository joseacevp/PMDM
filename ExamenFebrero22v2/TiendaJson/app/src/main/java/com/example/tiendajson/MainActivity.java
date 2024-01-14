package com.example.tiendajson;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiendajson.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static final int SELECCION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.botonPrinSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VentanaSelector.class);
                startActivityForResult(intent, SELECCION);
            }
        });


    }

    //recive el resultado de la consulta del intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //codigo para gestionar el resultado de la consulta devuelto por la actividad llamada.

        if (requestCode == SELECCION) {//si la etiqueta del dato recibido es
            if (resultCode == RESULT_OK){
                //si recibe datos
                binding.textPrincipProducSelec.setText("Producto seleccionado :"+ data.getStringExtra("datosLista"));
            }
        }

    }

}