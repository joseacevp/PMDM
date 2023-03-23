package com.example.tarea3v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.tarea3v3.databinding.ActivityCalendarioBinding;

public class BiciActivity extends AppCompatActivity {
    private ActivityCalendarioBinding binding;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarioBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());

        BikesContent.clearBikes();
        BikesContent.loadBikesFromJSON(this);
        System.out.println(BikesContent.ITEMS.size());

        /*
         * INICIO CREACIÓN DEL RecyclerView
         */

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        myAdapter = new Adaptador(BikesContent.ITEMS, this);//mlistener);
        binding.recyclerView.setAdapter(myAdapter);
        /*
         * FIN CREACIÓN DEL RecyclerView
         */

    }




    // PASO 2
    // Este método captura la selección del usuario
    public void terminar(BikesContent.Bike product){
        //System.out.println("TERMINAR: " + product.toString());
        Intent i = new Intent();
        i.putExtra("PRODUCTO", product.getEmail());

        // Los resultados se devuelven a través de un Intent invocando al método setResult()
        setResult(RESULT_OK,i);

        // Se finaliza la actividad invocando al método finish()
        finish();
    }
}