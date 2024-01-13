package com.example.examenfebrero22;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.examenfebrero22.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static final int SELECCION_PRODUCTO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.botonPrincipalSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencionActi = new Intent(getApplicationContext(), VentanaSeleccion.class);
                startActivityForResult(intencionActi,SELECCION_PRODUCTO);
            }
        });

    }  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode == SELECCION_PRODUCTO) {
            if (resultCode == RESULT_OK){

            }
        }
    }
}