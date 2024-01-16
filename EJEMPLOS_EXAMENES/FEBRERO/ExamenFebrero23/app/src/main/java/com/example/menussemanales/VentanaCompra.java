package com.example.menussemanales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.menussemanales.databinding.ActivityVentanaCompraBinding;

import java.util.ArrayList;

public class VentanaCompra extends AppCompatActivity {

    ActivityVentanaCompraBinding binding;

    //9. construcción de la lista que mandaremos como parametro
    // 13. instancia de la lista de datos a presentar en el recycleView
    private ArrayList<String> listaDatos = new ArrayList<String>();


    //10. referencia al id del recycleView del layout main
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_compra);
        binding = ActivityVentanaCompraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.botonVentVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(VentanaCompra.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        //11. instancia a la referencias
        recyclerView = findViewById(R.id.recyclerView);
        //12. indicamos que el recycleView sea una lista horizontal o vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));

        //reccion de datos
        Bundle datoRecivido = this.getIntent().getExtras();
        if (datoRecivido != null) {

            //14. llenamos la lista de datos
            listaDatos = datoRecivido.getStringArrayList("dato");
            System.out.println("tamaño de la lista : " + datoRecivido.size());

        }

        // para que se muestren los datos en foma de celdas
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//2 columnas

//        listaDatos.add("datos de prueba");

        //15. enviamos la lista con los datos al adaptador
        Adaptador adapter = new Adaptador(listaDatos);
        //16. indicamos al recycleView que adaptador usar
        recyclerView.setAdapter(adapter);


    }
}