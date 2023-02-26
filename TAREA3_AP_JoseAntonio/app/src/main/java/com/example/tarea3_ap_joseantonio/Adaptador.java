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

{
//
//    ArrayList<Bicicleta> listDatos;
//    private View.OnClickListener listener;

    private List<BikesContent.Bike> localDataSet;
    private BicleFragment saInstance;


//
//    public void setOnClickListener(View.OnClickListener listener){
//        this.listener=listener;
//    }



    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        public final View mView;
        public BikesContent.Bike mItem;

        public final TextView owner;
        public final TextView email;
        public final  TextView city;
        public final TextView description;
        public final  TextView country;
        public final  TextView location;
        public final ImageView image;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            owner=itemView.findViewById(R.id.owner);
            email=itemView.findViewById(R.id.email);
            city=itemView.findViewById(R.id.city);
            description=itemView.findViewById(R.id.descripcion);
            country=itemView.findViewById(R.id.country);
            location=itemView.findViewById(R.id.location);
            image=itemView.findViewById(R.id.image);


        }
    }

    public Adaptador(List<BikesContent.Bike> dataSet, BicleFragment bicleFragment) {
       localDataSet = dataSet;
       saInstance = bicleFragment;
  }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list,viewGroup,false);
    return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, final int position) {

       holder.owner.setText(localDataSet.get(position).getOwner());
       holder.email.setText(localDataSet.get(position).getEmail());
       holder.city.setText(localDataSet.get(position).getCity());
       holder.description.setText(localDataSet.get(position).getDescription());
       holder.country.setText(localDataSet.get(position).getLocation());
       holder.location.setText(localDataSet.get(position).getLocation());
       holder.image.setImageBitmap(localDataSet.get(position).getPhoto());

//       holder.mView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               saInstance.terminar(holder.mItem);
//           }
//       });

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


}
