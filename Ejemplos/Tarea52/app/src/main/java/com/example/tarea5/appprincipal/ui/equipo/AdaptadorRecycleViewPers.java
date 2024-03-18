package com.example.tarea5.appprincipal.ui.equipo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea5.R;

import java.util.ArrayList;

//implementacion del evento de pulsar o click en los elementos del RecycleView
//para esto implementamos OnClickListener
public class AdaptadorRecycleViewPers extends RecyclerView.Adapter<AdaptadorRecycleViewPers.ViewHolderPersonajes>
        implements View.OnClickListener {

    ArrayList<Jugador> listaPersonajes;

    //propiedad escuchador para implementar onClick
    private View.OnClickListener listener;

    public AdaptadorRecycleViewPers(ArrayList<Jugador> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    @NonNull
    @Override
    public AdaptadorRecycleViewPers.ViewHolderPersonajes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()
        ).inflate(R.layout.intem_list_jugadores, null, false);

        //escuchador de onClick a la escucha
        view.setOnClickListener(this);

        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycleViewPers.ViewHolderPersonajes viewHolderPersonajes, int i) {
        //2.
        viewHolderPersonajes.etiNombre.setText(listaPersonajes.get(i).getNombre());
        viewHolderPersonajes.etiPosicion.setText(listaPersonajes.get(i).getPosicion());
        viewHolderPersonajes.foto.setImageResource(listaPersonajes.get(i).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }


    //metodo para implementar onClick
    public  void setOnClickListener(View.OnClickListener listener){
            this.listener = listener;
    }
    //implementacion de onClick
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {
        TextView etiNombre, etiPosicion;
        ImageView foto;

        public ViewHolderPersonajes(@NonNull View itemView) {
            super(itemView);

            etiNombre = itemView.findViewById(R.id.textNombre);
            etiPosicion = itemView.findViewById(R.id.textDescripcion);
            foto = itemView.findViewById(R.id.imageView);
            //1. cambia apartir de aqui
        }
    }
}
