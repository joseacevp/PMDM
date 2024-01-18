package com.example.menussemanales;

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

import com.example.menussemanales.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ArrayList<String> listaDatos = new ArrayList<>();
    private String caloriasString = "";
    private int calorias = 0;

    DialogoCalorias dialogoCalorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_platos,
                android.R.layout.simple_spinner_item);
        binding.spinner1.setAdapter(adapter);
        binding.spinner2.setAdapter(adapter);
        binding.spinner3.setAdapter(adapter);
        binding.spinner4.setAdapter(adapter);
        binding.spinner5.setAdapter(adapter);
        binding.spinner6.setAdapter(adapter);
        binding.spinner7.setAdapter(adapter);

        //evento del spinner captura la seleccion entre la lista de opciones del Spinner
        seleccionMenu(binding.spinner1);
        seleccionMenu(binding.spinner2);
        seleccionMenu(binding.spinner3);
        seleccionMenu(binding.spinner4);
        seleccionMenu(binding.spinner5);
        seleccionMenu(binding.spinner6);
        seleccionMenu(binding.spinner7);
     
    }

    private void seleccionMenu(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                ingredientesPlato(posicion);
                calcularCalorias(posicion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//                estado.setText("");
            }
        });
    }

    private String ingredientesPlato(int posicion) {
        String ingredientes = "";
        switch (posicion) {

            case 0:
//                listaDatos.add(ingredientes);
                break;
            case 1:
                ingredientes += "Judías verdes: " +
                        "Judías verdes ," +
                        "Puerro";
                listaDatos.add(ingredientes);
                break;
            case 2:
                ingredientes += "Lentejas con verdura: " +
                        "Lentejas " +
                        "Calabacín " +
                        "Zanahoria " +
                        "Calabaza ";
                listaDatos.add(ingredientes);
                break;
            case 3:
                ingredientes +=
                        "Pollo al ajillo: " +
                                "Pollo " +
                                "Ajos ";
                listaDatos.add(ingredientes);
                break;
            case 4:
                ingredientes += "Salmón plancha: " + "Salmón"
                ;
                listaDatos.add(ingredientes);
                break;
            case 5:
                ingredientes += "Crema de calabaza: " +
                        "Calabaza " +
                        "Tomate"
                ;
                listaDatos.add(ingredientes);
                break;
            case 6:
                ingredientes += "Crema de calabacín: " +
                        "Calabacín"
                ;
                listaDatos.add(ingredientes);
                break;
            case 7:
                ingredientes += "Trucha plancha: " +
                        "Trucha"
                ;
                listaDatos.add(ingredientes);
                break;

        }
        return ingredientes;
    }

    private String calcularCalorias(int posicion) {

        switch (posicion) {

            case 0:
//                listaDatos.add(ingredientes);
                break;
            case 1:
                calorias = calorias + 100;
                break;
            case 2:
                calorias = calorias + 200;
                break;
            case 3:
                calorias = calorias + 300;
                break;
            case 4:
                calorias = calorias + 150;
                break;
            case 5:
                calorias = calorias + 50;
                break;
            case 6:
                calorias = calorias + 50;
                break;
            case 7:
                calorias = calorias + 100;
                break;

        }
        caloriasString = String.valueOf(calorias);
        System.out.println("Total Calorias: " + caloriasString);
        return caloriasString;


    }
    // Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int seleccion = item.getItemId();

        switch (seleccion) {
            case R.id.item_ver:
                Intent intent = new Intent(MainActivity.this, VentanaCompra.class);
                Bundle miDato = new Bundle();
//                listaDatos.add("prueba");
                miDato.putStringArrayList("dato", listaDatos);
                intent.putExtras(miDato);
                startActivity(intent);
                break;
            case R.id.item_calorias:

                Bundle datoCalorias = new Bundle();
                datoCalorias.putString("dialogo_calo", caloriasString);
                dialogoCalorias = new DialogoCalorias();
                dialogoCalorias.setCalorias(caloriasString);
                dialogoCalorias.show(getSupportFragmentManager(), "dialogo_calo");
                break;
        }

        return true;
    }
    //fin Menu
}