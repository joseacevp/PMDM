package com.example.juegomultiplicar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfiguracionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfiguracionFragment extends Fragment implements DialogoFecha.OnFechaSeleccionada {
    View view;
    DialogoFecha fecha = new DialogoFecha();
   private String[] heroes = {"Batman",
            "Hulk",
            "Airon Man",
            "Capitan America"};

    private String[] descripcion = {"Matches Malone\u200B El caballero de la noche El caballero oscuro Zurdo Knox\u200B El señor de la noche\u200B",
            "Hulk, El Increíble Hulk,\u200B El Justiciero, El Hombre Increíble",
            "Tony Stark, Iron Man (El Hombre de Hierro)",
            "Nómada, El Capitán, Cap, Steve Rogers, Steve"
    };

    private int imagenes[] = {R.drawable.batman,
            R.drawable.huld_cuatro,
            R.drawable.iron_cuatro,
            R.drawable.capi_cuatro
    };
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConfiguracionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfiguracionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfiguracionFragment newInstance(String param1, String param2) {
        ConfiguracionFragment fragment = new ConfiguracionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_configuracion, container, false);

        Button boton = view.findViewById(R.id.boton_fecha_config);
        boton.setOnClickListener(this::seleccion_fecha);
        Spinner selectorAbatar = view.findViewById(R.id.spinner_avatar);
        AdaptadorPersonalizado a = new AdaptadorPersonalizado(getContext(),
                R.layout.linea_personajes, heroes);
        selectorAbatar.setAdapter(a);

        selectorAbatar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //codigo para gestionar lo que ocurre cuando seleccionas una opcion del Spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner selectorDificultad = view.findViewById(R.id.spinner_dificultad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.array_dificultad,
                android.R.layout.simple_spinner_item);
        selectorDificultad.setAdapter(adapter);


        return view;

    }

    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {


    }

    public void seleccion_fecha(View view) {

        fecha.show(getFragmentManager(), "fecha");

    }


    public class AdaptadorPersonalizado extends ArrayAdapter {


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
            LayoutInflater inflater = getLayoutInflater();
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

}