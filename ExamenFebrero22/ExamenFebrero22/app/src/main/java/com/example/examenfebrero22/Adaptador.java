package com.example.examenfebrero22;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderDatos> {

    //1. lista de datos con los datos de los intem
    private List<ProductContent.Product> datoRecuperadoJson;
    //instancia de la actividad contenedora
    private VentanaSeleccion saInstance;

    //constructor
    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        //referencua a la actividad receptora del recycler
        private ImageView imagen;
        //3. referencia de los componentes de la lista de datos
        private TextView nombre, descripcion, valor;
        public final View view;
        //instancia del objeto recuperado del archivo json
        public ProductContent.Product objeto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            //4. instancia a la referencia de los datos de la lista de datos
            view = itemView;
            imagen = itemView.findViewById(R.id.imagenProducto);
            nombre = itemView.findViewById(R.id.textNombreProducto);
            descripcion = itemView.findViewById(R.id.textDescripcion);
            valor = itemView.findViewById(R.id.textPrecio);
        }
    }

    //2. construptor de la lista de datos llega lista de datos y se
    //asignan al elemento listDatos
    public Adaptador(List<ProductContent.Product> datoLocalProducto, VentanaSeleccion segundaActivity) {
        datoRecuperadoJson = datoLocalProducto;
        saInstance = segundaActivity;
    }

    @NonNull
    @Override
    public Adaptador.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //5. infla la vista con la referencia del xml lista intem que contien la plantilla
        //de los datos que reflejaran los componentes del recycleView
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_productos, null, false);
        return new ViewHolderDatos(view);
    }

    //7. establece la comunicación entre el adaptador y la clase viewHolderDatos
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {
        //llamamos al metodo asignardatos pasando el dato del intem que
        //toca de la lista
        //8. asigna los datos del intem que toca de la lista y lo asigna
        //al componente del xml listaItem
        viewHolderDatos.objeto = datoRecuperadoJson.get(i);
        viewHolderDatos.imagen.setImageBitmap(datoRecuperadoJson.get(i).getPhoto());
        viewHolderDatos.nombre.setText(datoRecuperadoJson.get(i).getName());
        viewHolderDatos.descripcion.setText(datoRecuperadoJson.get(i).getCategory());
        viewHolderDatos.valor.setText(datoRecuperadoJson.get(i).getPrice());

        //metodo pare gestionar el item seleccionado
        viewHolderDatos.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saInstance.terminar(viewHolderDatos.objeto);
            }
        });

    }

    //6.retorna el tamaño de la lista de datos recibida en el construptor
    @Override
    public int getItemCount() {
        return datoRecuperadoJson.size();
    }


}
