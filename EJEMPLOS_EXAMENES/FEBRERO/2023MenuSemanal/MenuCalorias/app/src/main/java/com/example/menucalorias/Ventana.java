package com.example.menucalorias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.menucalorias.databinding.ActivityVentanaBinding;

import java.util.ArrayList;

public class Ventana extends AppCompatActivity {
    ActivityVentanaBinding binding;
    //9. construcción de la lista que mandaremos como parametro
    ArrayList<String> listaDatos;
    //10. referencia al id del recycleView del layout main
//    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana);
        binding = ActivityVentanaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //11. instancia a la referencias
//        binding.recyclerView = findViewById(R.id.recycler_view);
        //12. indicamos que el recycleView sea una lista horizontal o vertical

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));

        // para que se muestren los datos en foma de celdas
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//2 columnas
        //13. instancia de la lista de datos a presentar en el recycleView
        listaDatos = new ArrayList<String>();
        //14. llenamos la lista de datos
        listaDatos.add("primer dato");
        listaDatos.add("segundo dato");
        listaDatos.add("tercer dato");
        listaDatos.add("cuarto dato");
        listaDatos.add("quinto dato");
        listaDatos.add("sexto dato");
        listaDatos.add("octavo dato");
        listaDatos.add("noveno dato");
        listaDatos.add("decimo dato");

        //15. enviamos la lista con los datos al adaptador

        Adaptador adapter = new Adaptador(listaDatos);
        //16. indicamos al recycleView que adaptador usar
        binding.recyclerView.setAdapter(adapter);
    }
}