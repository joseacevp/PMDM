package com.example.tarea3_ap_joseantonio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdaptador extends RecyclerView.Adapter<ListAdaptador.ViewHolder> {

    private List<BikesContent.Bike> mData;
    private LayoutInflater mImflater;
    private Context context;

    public ListAdaptador(List<BikesContent.Bike> itemList, Context context) {
        this.mImflater= LayoutInflater.from(context);
        this.context= context;
        this.mData=itemList;
    }
    @Override
    public int getItemCount(){
        return mData.size();
    }


    @Override
    public ListAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mImflater.inflate(R.layout.list_bicis,null);
        return new ListAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdaptador.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<BikesContent.Bike> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView foto;
        ImageView carta;
        TextView ciudad,descripcion;

        ViewHolder(View inteView){
            super(inteView);
            foto = inteView.findViewById(R.id.fotoBiciImagenView);
            ciudad = inteView.findViewById(R.id.ciudadTextView);
            descripcion = inteView.findViewById(R.id.descripcionTextView);
            carta = inteView.findViewById(R.id.cartaImagenView);


        }

        void bindData(final BikesContent item){
            //define los cambios que tendran los elementos de la lista.
            //https://www.youtube.com/watch?v=HrZgfoBeams&t=575s
            //24:35min
        }
    }
}
