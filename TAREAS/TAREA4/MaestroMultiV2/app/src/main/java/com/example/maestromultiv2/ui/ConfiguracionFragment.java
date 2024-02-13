package com.example.maestromultiv2.ui;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maestromultiv2.AdaptadorRecycleViewPers;
import com.example.maestromultiv2.Avatar;
import com.example.maestromultiv2.Partida;
import com.example.maestromultiv2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ConfiguracionFragment extends Fragment {

    //variables
    View view;
    String heroe, dificultad, fechaSeleccionada, numero_tabla_selec;
    TextView fecha;
    EditText numeroTabla;
    Partida partida;
    Button botonAvatar;
    RecyclerView recyclerView;

    //3
    ArrayList<Avatar> lista;

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


    public ConfiguracionFragment() {
        // Required empty public constructor
    }


    public static ConfiguracionFragment newInstance(String param1, String param2) {
        ConfiguracionFragment fragment = new ConfiguracionFragment();

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
        recyclerView = view.findViewById(R.id.recyclerView);
        botonAvatar = view.findViewById(R.id.boton_avatar);
        botonAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarRecyclerAvatar();
            }
        });
        //fecha
        fecha = view.findViewById(R.id.texto_fecha);
        fecha.setText(recuperarFechaActual());
        fechaSeleccionada = fecha.getText().toString();

        //numero de tabla
        numeroTabla = view.findViewById(R.id.edit_numero_tabla);
        // Crear un filtro para limitar la longitud del texto a 1 carácter
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(1);
        // Establecer el filtro en el EditText
        numeroTabla.setFilters(filters);
        numero_tabla_selec = numeroTabla.getText().toString();


        //spinner selector de dificultad
        Spinner selectorDificultad = view.findViewById(R.id.spinner_dificultad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.array_dificultad,
                android.R.layout.simple_spinner_item);
        selectorDificultad.setAdapter(adapter);
        selectorDificultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dificultad = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getContext(), dificultad, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }

    private void llamarRecyclerAvatar() {
        lista = new ArrayList<>();
        lista.add(new Avatar("Hulk", "El forzudo Verde", R.drawable.huld));
        lista.add(new Avatar("Capitan America", "Heroe Americano", R.drawable.capi));
        lista.add(new Avatar("Iron Man", "Rico Bueno", R.drawable.iron));
        lista.add(new Avatar("Batman", "El caballero Oscuro", R.drawable.batman));


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        AdaptadorRecycleViewPers adaptador = new AdaptadorRecycleViewPers(lista);

        //metodo para manejar los eventos al click en los intem del RecycleView
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Pulsaste : "
                        + lista.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(adaptador);

    }




    private String recuperarFechaActual() {
        // Obtener la instancia de Calendar y establecer la fecha actual
        Calendar calendar = Calendar.getInstance();

        // Crear un formato de fecha deseado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Formatear la fecha actual
        String fechaActual = dateFormat.format(calendar.getTime());

        return fechaActual;
    }


    //adaptador personalizado para el spinner de los heroes
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
            View miFila = inflater.inflate(R.layout.linea_personaje,
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