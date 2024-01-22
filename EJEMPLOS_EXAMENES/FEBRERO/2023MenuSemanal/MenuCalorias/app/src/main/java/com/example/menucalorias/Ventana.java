package com.example.menucalorias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.menucalorias.databinding.ActivityVentanaBinding;

public class Ventana extends AppCompatActivity {
    ActivityVentanaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana);
        binding = ActivityVentanaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}