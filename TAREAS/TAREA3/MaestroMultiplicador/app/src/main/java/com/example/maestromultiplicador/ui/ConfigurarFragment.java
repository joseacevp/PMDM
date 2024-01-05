package com.example.maestromultiplicador.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.maestromultiplicador.R;
import com.example.maestromultiplicador.databinding.FragmentConfigurarBinding;

public class ConfigurarFragment extends Fragment {

    private FragmentConfigurarBinding binding;
    //variables
    private View view;
    private String heroe, dificultad, fechaSeleccionada, tabla, numero_tabla_selec;

    private String[] heroes = {"Batman",
            "Hulk",
            "Iron Man",
            "Capitan America"};

//    private String[] descripcion = {"Matches Malone\u200B El caballero de la noche El caballero oscuro Zurdo Knox\u200B El señor de la noche\u200B",
//            "Hulk, El Increíble Hulk,\u200B El Justiciero, El Hombre Increíble",
//            "Tony Stark, Iron Man (El Hombre de Hierro)",
//            "Nómada, El Capitán, Cap, Steve Rogers, Steve"
//    };
//
//    private int imagenes[] = {R.drawable.batman,
//            R.drawable.huld,
//            R.drawable.iron,
//            R.drawable.capi
//    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentConfigurarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //spinner selector de abatar
        Spinner selectorAbatar = root.findViewById(R.id.spinner_avatar);
        AdaptadorPersonalizado a = new AdaptadorPersonalizado(getContext(),
                R.layout.linea_personajes, heroes);
        selectorAbatar.setAdapter(a);

        selectorAbatar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //codigo para gestionar lo que ocurre cuando seleccionas una opcion del Spinner
                heroe = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getContext(), heroe, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //spinner selector dificulatad

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}