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

import com.example.menucalorias.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding binding;
    private ArrayList<String> listaIngredientes = new ArrayList<>();
    Dialogo dialogo;
    private String caloriasString = "";
    private int calorias = 0;;
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
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this, Ventana.class);
                bundle.putStringArrayList("listaDatos", listaIngredientes);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.item_menu:
//                Bundle datoCalorias = new Bundle();
//                datoCalorias.putString("dialogo_calo", caloriasString);
                dialogo = new Dialogo();
                caloriasString = String.valueOf(calorias);
                dialogo.setCalorias(caloriasString);
                dialogo.show(getSupportFragmentManager(), "dialogo_calo");
                break;
        }

        return true;
    }

    private void onSeleccionSpinner(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ingredientesPlato(i);
//                System.out.println(ingredientesPlato(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private ArrayList<String> ingredientesPlato(int i) {
        String ingredientes = "";

        switch (i) {
            case 0:

                break;
            case 1:
                calorias = calorias + 200;
                ingredientes += "Lentejas con verdura: \n " +
                        "Lentejas \n " +
                        "Calabacínm\n  " +
                        "Zanahoria \n " +
                        "Calabaza \n ";
                listaIngredientes.add(ingredientes);
                break;
            case 2:
                calorias = calorias + 100;
                ingredientes += "Judías verdes: \n" +
                        " Judías verdes " +
                        "  Puerro ";
                listaIngredientes.add(ingredientes);
                break;
            case 3:
                calorias = calorias + 100;
                ingredientes += "Trucha plancha:\n  " +
                        "Trucha\n ";
                listaIngredientes.add(ingredientes);
                break;
            case 4:
                calorias = calorias + 100;
                ingredientes += " Salmón plancha:\n  " +
                        " Salmón ";
                listaIngredientes.add(ingredientes);
                break;
            case 5:
                calorias = calorias + 50;
                ingredientes += " Crema de calabaza:\n  " +
                        " Calabaza\n  " +
                        " Tomate\n  ";
                listaIngredientes.add(ingredientes);
                break;
            case 6:
                calorias = calorias + 50;
                ingredientes += "Crema de calabacín:\n " +
                        "Calabacín \n ";
                listaIngredientes.add(ingredientes);
                break;
            case 7:
                calorias = calorias + 300;
                ingredientes += "Pollo al ajillo:\n  " +
                        " Pollo \n " +
                        " Ajos \n ";
                listaIngredientes.add(ingredientes);
                break;
        }

        return listaIngredientes;
    }


}