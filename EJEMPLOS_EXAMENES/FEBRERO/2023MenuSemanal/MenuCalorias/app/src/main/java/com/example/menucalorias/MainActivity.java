package com.example.menucalorias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.menucalorias.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ArrayList<String> listaIngredientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_platos,
                android.R.layout.simple_spinner_item);
        binding.spinner1.setAdapter(adapter);
        binding.spinner2.setAdapter(adapter);
        binding.spinner3.setAdapter(adapter);
        binding.spinner4.setAdapter(adapter);
        binding.spinner5.setAdapter(adapter);
        binding.spinner6.setAdapter(adapter);
        binding.spinner7.setAdapter(adapter);

        onSeleccionSpinner(binding.spinner1);
        onSeleccionSpinner(binding.spinner2);
        onSeleccionSpinner(binding.spinner3);
        onSeleccionSpinner(binding.spinner4);
        onSeleccionSpinner(binding.spinner5);
        onSeleccionSpinner(binding.spinner6);
        onSeleccionSpinner(binding.spinner7);
    }

    private void onSeleccionSpinner(Spinner spinner1) {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listaIngredientes.add(ingredientesPlato(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private String ingredientesPlato(int i) {
        String ingredientes = "";

        switch (i) {
            case 1:
                ingredientes += "";
                break;
            case 2:
                ingredientes += "";
                break;
            case 3:
                ingredientes += "";
                break;
            case 4:
                ingredientes += "";
                break;
            case 5:
                ingredientes += "";
                break;
        }

        return ingredientes;
    }
}