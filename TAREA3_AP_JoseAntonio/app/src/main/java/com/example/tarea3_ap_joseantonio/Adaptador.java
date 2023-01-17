package com.example.tarea3_ap_joseantonio;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador  extends RecyclerView.Adapter<Adaptador.ViewHolderDatos>{

    ArrayList<Bikes> listDatos;

    public Adaptador(ArrayList<Bikes> listDatos) {
        this.listDatos = listDatos;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView imagenBici;
        TextView localidad;
        TextView descripcion;
        ImageView icono;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            imagenBici=itemView.findViewById(R.id.imagenId);
            localidad=itemView.findViewById(R.id.localidad);
            descripcion=itemView.findViewById(R.id.descripcion);
            icono=itemView.findViewById(R.id.icono_carta);

        }
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
       holder.imagenBici.setImageResource(listDatos.get(position).getImagen());
       holder.localidad.setText(listDatos.get(position).getLocalidad());
       holder.descripcion.setText(listDatos.get(position).getDescripcion());
       holder.icono.setImageResource(listDatos.get(position).getIcono());
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }


}
