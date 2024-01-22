package com.example.menucalorias;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int seleccion = item.getItemId();

        switch (seleccion) {
            case R.id.itemLista:
                Intent intent = new Intent(MainActivity.this,Ventana.class);
                startActivity(intent);
                break;
            case R.id.item_menu:
                Toast.makeText(this, "Menu calorias", Toast.LENGTH_SHORT).show();
                break;

        }

        return true;
    }

    private void onSeleccionSpinner(Spinner spinner1) {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listaIngredientes.add(ingredientesPlato(i));
                System.out.println(ingredientesPlato(i));
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
                ingredientes += "\\\"Judías verdes\\\",\\n\" +\n" +
                        "                \"    calorias: 100\\n\" +\n" +
                        "                \"    \\\"Judías verdes\\\"\\n\" +\n" +
                        "                \"    \\\"Puerro\\\"\\n";
                break;
            case 2:
                ingredientes += "\\\"Lentejas con verdura\\\"\\n\" +\n" +
                        "                \"    calorias: 200;\\n\" +\n" +
                        "                \"    \\\"Lentejas\\\"\\n\" +\n" +
                        "                \"    \\\"Calabacín\\\"\\n\" +\n" +
                        "                \"    \\\"Zanahoria\\\"\\n\" +\n" +
                        "                \"    \\\"Calabaza\\\"\\n";
                break;
            case 3:
                ingredientes += "\\\"Pollo al ajillo\\\"\\n\" +\n" +
                        "                \"    calorias: 300\\n\" +\n" +
                        "                \"    \\\"Pollo\\\"\\n\" +\n" +
                        "                \"    \\\"Ajos\\\"\\n";
                break;
            case 4:
                ingredientes += "\\\"Salmón plancha\\\"\\n\" +\n" +
                        "                \"    calorias: 150\\n\" +\n" +
                        "                \"    \\\"Salmón\\\"\\n";
                break;
            case 5:
                ingredientes += "\\\"Crema de calabaza\\\"\\n\" +\n" +
                        "                \"    calorias: 50\\n\" +\n" +
                        "                \"    \\\"Calabaza\\\"\\n\" +\n" +
                        "                \"    \\\"Tomate\\\"\\n";
                break;
            case 6:
                ingredientes += "\\\"Crema de calabacín\\\"\\n\" +\n" +
                        "                \"    calorias: 50\\n\" +\n" +
                        "                \"    \\\"Calabacín\\\"\\n";
                break;
            case 7:
                ingredientes += "\\\"Trucha plancha\\\"\\n\" +\n" +
                        "                \"    calorias: 100\\n\" +\n" +
                        "                \"    \\\"Trucha\\\"\\n";
                break;
        }

        return ingredientes;
    }
}