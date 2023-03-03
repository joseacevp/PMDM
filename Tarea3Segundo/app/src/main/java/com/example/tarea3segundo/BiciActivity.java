package com.example.tarea3segundo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.tarea3segundo.databinding.ActivityBiciBinding;

public class BiciActivity extends AppCompatActivity {

    private ActivityBiciBinding binding;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBiciBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BikesContent.clearBikes();
        BikesContent.loadBikesFromJSON(this);
        System.out.println(BikesContent.ITEMS.size());

        binding.recyclerView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        myAdapter = new Adaptador(BikesContent.ITEMS,this);
        binding.recyclerView.setAdapter(myAdapter);
    }
//        // PASO 2
//        // Este método captura la selección del usuario
      public void terminar (BikesContent.Bike bike){
           //System.out.println("TERMINAR: " + product.toString());
           Intent i = new Intent();
           i.putExtra("PRODUCTO", bike.getDescription());

           // Los resultados se devuelven a través de un Intent invocando al método setResult()
           setResult(RESULT_OK,i);

           // Se finaliza la actividad invocando al método finish()
           finish();
      }


}