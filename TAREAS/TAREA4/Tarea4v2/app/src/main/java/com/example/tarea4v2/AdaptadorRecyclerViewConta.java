package com.example.tarea4v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRecyclerViewConta  extends RecyclerView.Adapter<AdaptadorRecyclerViewConta.ViewHolderContactos>
        implements View.OnClickListener {
    ArrayList<String> listaContactos;

    //propiedad escuchador para implementar onClick
    private View.OnClickListener listener;

    public AdaptadorRecyclerViewConta(ArrayList<String> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public AdaptadorRecyclerViewConta.ViewHolderContactos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()
        ).inflate(R.layout.intem_list_contactos, null, false);

        //escuchador de onClick a la escucha
        view.setOnClickListener(this);

        return new AdaptadorRecyclerViewConta.ViewHolderContactos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecyclerViewConta.ViewHolderContactos viewHolderPersonajes, int i) {
        //2.
        viewHolderPersonajes.etiNombre.setText(listaContactos.get(i));

    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
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

    public class ViewHolderContactos extends RecyclerView.ViewHolder {
        TextView etiNombre;

        public ViewHolderContactos(@NonNull View itemView) {
            super(itemView);

            etiNombre = itemView.findViewById(R.id.textoNombreContacto);

            //1. cambia apartir de aqui
        }
    }

}
