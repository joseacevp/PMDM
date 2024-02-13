package com.example.maestromultiv2.ui;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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

        botonAvatar = view.findViewById(R.id.boton_avatar);
        botonAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //accion para llamar an fragmento contenedor del recyclerView avatar
                NavController navController =
                        Navigation.findNavController(getActivity(),
                                R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_nav_configurar_to_avatarFragment);
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






    private String recuperarFechaActual() {
        // Obtener la instancia de Calendar y establecer la fecha actual
        Calendar calendar = Calendar.getInstance();

        // Crear un formato de fecha deseado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Formatear la fecha actual
        String fechaActual = dateFormat.format(calendar.getTime());

        return fechaActual;
    }


}