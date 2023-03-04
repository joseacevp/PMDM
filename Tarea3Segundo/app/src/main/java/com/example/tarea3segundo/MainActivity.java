package com.example.tarea3segundo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea3segundo.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    public static final int SELECCIONA_BICI = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigation.setOnClickListener(this);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch(item.getItemId()) {
                        case R.id.homeFragment:

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivityForResult(intent,SELECCIONA_BICI);
                            return true;
                        case R.id.biciFragment:

                            intent = new Intent(getApplicationContext(), BiciActivity.class);
                            startActivityForResult(intent,SELECCIONA_BICI);
                            return true;
                        case R.id.calendarioFragment:
                            intent = new Intent(getApplicationContext(),CalActivity.class);
                            startActivity(intent);

                            return true;
                    }

                    return false;

                }
            };
    // PASO 1
    @Override
    public void onClick(View view) {
        // Objeto Intent: intención de arrancar una actividad del tipo SegundaActividad
        Intent intent = new Intent(this, BiciActivity.class);

        // Llamada a la actividad SegundaActivity para que se cargue en pantalla
        startActivityForResult(intent,SELECCIONA_BICI);
    }

    /*
     * PASO 3
     * La actividad principal obtiene los resultados a través de este método,
     * que es la función callback
     */
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Si se seleccionó correctamente el producto ...
        if (requestCode == SELECCIONA_BICI) {
            if (resultCode == RESULT_OK) {
                // El resultado se obtiene a través del objeto Intent
                binding.textView.setText("Se ha seleccionado:\n"
                        + data.getStringExtra("PRODUCTO"));
            }
        }
    }
}