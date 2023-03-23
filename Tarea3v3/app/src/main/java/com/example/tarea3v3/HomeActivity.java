package com.example.tarea3v3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.tarea3v3.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity implements CalendarioActivity.OnFechaSeleccionada {

    private ActivityMainBinding binding;
    public static final int SELECCIONA_PRODUCTO = 1;

    String fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(onItemSelectedListener);

    }

    private final NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homeFragment:
                    Intent intent = new Intent(getApplication(), HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.biciFragment:
                    intent = new Intent(getApplication(), BiciActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.calendarioFragment:
                    CalendarioActivity d = new CalendarioActivity();
                    d.show(getSupportFragmentManager(), "Mi diálogo Fecha");
                    return true;
            }

            return false;

        }
    };

    @Override
    public void onResultadoFecha(String fecha) {
        TextView textView = findViewById(R.id.textoSelecciona);
        this.fecha=fecha;
        textView.setText(fecha);
    }
}