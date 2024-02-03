package com.example.maestromultiplicadorv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.maestromultiplicadorv2.databinding.ActivityVentanaSelectorAvatarBinding;

import java.util.ArrayList;

public class VentanaSelectorAvatar extends AppCompatActivity {
    //3
    ArrayList<Avatar> lista;
    RecyclerView recyclerView;
    ActivityVentanaSelectorAvatarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_selector_avatar);
        binding = ActivityVentanaSelectorAvatarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        construirRecycleView();
        llenarPersonajes();

    }


    private void construirRecycleView() {
        lista = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerAvatar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Adaptador adaptador = new Adaptador(lista);

        //metodo para manejar los eventos al click en los intem del RecycleView
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencion = new Intent();
                intencion.putExtra("AVATAR",lista.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                setResult(RESULT_OK,intencion);
                finish();
//                Toast.makeText(VentanaSelectorAvatar.this, "Pulsaste : "
//                        + lista.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(adaptador);
    }

    private void llenarPersonajes() {
        lista.add(new Avatar("Hulk", "El forzudo Verde", R.drawable.hulk));
        lista.add(new Avatar("Capitan America", "Heroe Americano", R.drawable.capitan));
        lista.add(new Avatar("Iron Man", "Rico Bueno", R.drawable.iron));
    }
}