package com.example.tarea3_ap_joseantonio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private List<BikesContent.Bike> localDataSet;
    private MainActivity baInstance;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public BikesContent.Bike mItem;
        private final ImageView imageViewProduct;
        private final TextView textViewOwner;
        private final TextView textViewDescripcion;
        private final TextView textViewCity;
        private final TextView textViewLocation;
        private final TextView textViewEmail;

  public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            mView = view;
            imageViewProduct = view.findViewById(R.id.imagen_bici);
            textViewOwner = view.findViewById(R.id.owner);
            textViewDescripcion = view.findViewById(R.id.descripcion);
            textViewCity = view.findViewById(R.id.city);
            textViewLocation = view.findViewById(R.id.location);
            textViewEmail = view.findViewById(R.id.email);
        }
    }

    public Adaptador(List<BikesContent.Bike> dataSet, MainActivity biciActivity){
        localDataSet = dataSet;
        baInstance = biciActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.mItem = localDataSet.get(position);
        viewHolder.imageViewProduct.setImageBitmap(localDataSet.get(position).getPhoto());
        viewHolder.textViewOwner.setText(localDataSet.get(position).getOwner());
        viewHolder.textViewDescripcion.setText(localDataSet.get(position).getDescription());
        viewHolder.textViewCity.setText(localDataSet.get(position).getCity());
        viewHolder.textViewLocation.setText(localDataSet.get(position).getLocation());
        viewHolder.textViewEmail.setText(localDataSet.get(position).getEmail());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Dentro de onBindViewHolder() --> setOnClickListener");
              baInstance.terminar(viewHolder.mItem);
            }
        });
        //Log.i("onBindViewHolder()", viewHolder.textView + " : " + position);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
