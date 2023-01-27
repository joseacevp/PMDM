package com.example.tarea3_ap_joseantonio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador  extends RecyclerView.Adapter<Adaptador.ViewHolderDatos>
implements View.OnClickListener
{

    ArrayList<Bicicleta> listDatos;
    private View.OnClickListener listener;
    public Adaptador(ArrayList<Bicicleta> listDatos) {
        this.listDatos = listDatos;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {


        TextView owner;
        TextView email;
        TextView city;
        TextView description;
        TextView country;
        TextView location;
        TextView image;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            owner=itemView.findViewById(R.id.owner);
            email=itemView.findViewById(R.id.email);
            city=itemView.findViewById(R.id.city);
            description=itemView.findViewById(R.id.descripcion);
            country=itemView.findViewById(R.id.country);
            location=itemView.findViewById(R.id.location);
            image=itemView.findViewById(R.id.image);


        }
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

       holder.owner.setText(listDatos.get(position).getOwner());
       holder.email.setText(listDatos.get(position).getEmail());
       holder.city.setText(listDatos.get(position).getCity());
       holder.description.setText(listDatos.get(position).getDescription());
       holder.country.setText(listDatos.get(position).getCountry());
       holder.location.setText(listDatos.get(position).getLocation());
       holder.image.setText(listDatos.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }


}
