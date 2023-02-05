package com.example.tarea3_ap_joseantonio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adaptador  extends RecyclerView.Adapter<Adaptador.ViewHolderDatos>
implements View.OnClickListener
{

   private List<BikesContent.Bike> listDatos;
    private View.OnClickListener listener;

    public Adaptador(List<BikesContent.Bike> listDatos) {
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

        public BikesContent.Bike mItem;
        public final View mView;
        public TextView owner;
        public TextView email;
        public  TextView city;
        public TextView description;
        public TextView country;
        public TextView location;
        public ImageView image;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            owner=itemView.findViewById(R.id.owner);
            email=itemView.findViewById(R.id.email);
            city=itemView.findViewById(R.id.city);
            description=itemView.findViewById(R.id.descripcion);
            country=itemView.findViewById(R.id.country);
            location=itemView.findViewById(R.id.location);
            image=itemView.findViewById(R.id.image);


            mView = itemView;
        }
    }


    @Override
    public ViewHolderDatos onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,parent,false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.mItem = listDatos.get(position);
       holder.owner.setText(listDatos.get(position).getOwner());
       holder.email.setText(listDatos.get(position).getEmail());
       holder.city.setText(listDatos.get(position).getCity());
       holder.description.setText(listDatos.get(position).getDescription());
       //holder.country.setText(listDatos.get(position).get);
       holder.location.setText(listDatos.get(position).getLocation());
       holder.image.setImageBitmap(listDatos.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }


}
