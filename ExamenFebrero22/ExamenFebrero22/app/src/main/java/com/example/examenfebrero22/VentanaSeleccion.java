package com.example.examenfebrero22;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.examenfebrero22.databinding.ActivityVentanaSeleccionBinding;

import java.util.ArrayList;

public class VentanaSeleccion extends AppCompatActivity {

    ActivityVentanaSeleccionBinding bindingV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingV = ActivityVentanaSeleccionBinding.inflate(getLayoutInflater());
        setContentView(bindingV.getRoot());


        //9. construcción de la lista que mandaremos como parametro
        ProductContent.clearProducts();
        ProductContent.loadProductsFromJSON(this);
        //System.out.println(ProductContent.ITEMS.size());

        /*
         * INICIO CREACIÓN DEL RecyclerView
         */
        //10. referencia al id del recycleView del layout main
        //RecyclerView recyclerView;
        //11. instancia a la referencias
//        recyclerView = findViewById(R.id.recycleId);
        //12. indicamos que el recycleView sea una lista horizontal o vertical
        bindingV.recyclerView.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));
        // para que se muestren los datos en foma de celdas
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//2 columnas
        //13. instancia de la lista de datos a presentar en el recycleView
//        listaDatos = new ArrayList<String>();
        //14. llenamos la lista de datos
//        listaDatos.add("primer dato");

        //15. enviamos la lista con los datos al adaptador
        Adaptador adapter = new Adaptador(ProductContent.ITEMS, this);//referencia a onclick
        //16. indicamos al recycleView que adaptador usar
        bindingV.recyclerView.setAdapter(adapter);

    }


    //metodo para capturar la seleccion del objeto en el recycler
    public void terminar(ProductContent.Product objeto) {
        Intent i = new Intent();
        i.putExtra("dato", objeto.getName());
        setResult(RESULT_OK, i);
        finish();
    }
}