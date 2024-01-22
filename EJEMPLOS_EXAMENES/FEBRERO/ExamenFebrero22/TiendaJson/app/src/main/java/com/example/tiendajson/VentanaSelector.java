package com.example.tiendajson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tiendajson.databinding.ActivityVentanaSelectorBinding;

public class VentanaSelector extends AppCompatActivity {

    ActivityVentanaSelectorBinding bending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_selector);
        bending = ActivityVentanaSelectorBinding.inflate(getLayoutInflater());
        View view = bending.getRoot();
        setContentView(view);


        //9. construcción de la lista que mandaremos como parametro
        ProductContent.clearProducts();
        //System.out.println(ProductContent.ITEMS.size());

        /*
         * INICIO CREACIÓN DEL RecyclerView
         */
        //10. referencia al id del recycleView
        //11. instancia a la referencias
        //12. indicamos que el recycleView sea una lista horizontal o vertical
        bending.recyclerView.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));

        //13. instancia de la lista de datos a presentar en el recycleView
        //14. llenamos la lista de datos
        ProductContent.loadProductsFromJSON(this);

        //15. enviamos la lista con los datos al adaptador
        Adaptador adapter = new Adaptador(ProductContent.ITEMS, this);//referencia a onclick
        //16. indicamos al recycleView que adaptador usar
        bending.recyclerView.setAdapter(adapter);

    }

    //envia los datos del objeto seleccionado en el RecyclearView
    public void reenviarDatos(ProductContent.Product objeto) {
        Intent intent = new Intent();
        intent.putExtra("datosLista", objeto.getName());
        setResult(RESULT_OK,intent);
        finish();
    }
}