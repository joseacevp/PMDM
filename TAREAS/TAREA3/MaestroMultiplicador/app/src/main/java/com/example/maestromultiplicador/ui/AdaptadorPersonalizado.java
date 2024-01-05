package com.example.maestromultiplicador.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maestromultiplicador.R;

public class AdaptadorPersonalizado extends ArrayAdapter{

    private String[] heroes = {"Batman",
            "Hulk",
            "Iron Man",
            "Capitan America"};

    private String[] descripcion = {"Matches Malone\u200B El caballero de la noche El caballero oscuro Zurdo Knox\u200B El señor de la noche\u200B",
            "Hulk, El Increíble Hulk,\u200B El Justiciero, El Hombre Increíble",
            "Tony Stark, Iron Man (El Hombre de Hierro)",
            "Nómada, El Capitán, Cap, Steve Rogers, Steve"
    };

    private int imagenes[] = {R.drawable.batman,
            R.drawable.huld,
            R.drawable.iron,
            R.drawable.capi
    };

    public String[] getHeroes() {
        return heroes;
    }

    public void setHeroes(String[] heroes) {
        this.heroes = heroes;
    }

    public String[] getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String[] descripcion) {
        this.descripcion = descripcion;
    }

    public int[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(int[] imagenes) {
        this.imagenes = imagenes;
    }
    //adaptador personalizado para el spinner de los heroes


        public AdaptadorPersonalizado(@NonNull Context context,
                                      int textViewResourceId,
                                      @NonNull Object[] objects) {

            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position,
                                    @Nullable View convertView,
                                    @NonNull ViewGroup parent) {
            return crearFilaPersonalizada(position, convertView, parent);
        }

        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            return crearFilaPersonalizada(position, convertView, parent);
        }

        private View crearFilaPersonalizada(int position,
                                            View convertView,
                                            ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View miFila = inflater.inflate(R.layout.linea_personajes,
                    parent, false);

            TextView nombre = miFila.findViewById(R.id.nombre);
            nombre.setText(heroes[position]);

            TextView descipcion = miFila.findViewById(R.id.descripcion);
            descipcion.setText(descripcion[position]);

            ImageView imagen = miFila.findViewById(R.id.imagen_heroe);
            imagen.setImageResource(imagenes[position]);
            return miFila;
        }



}
