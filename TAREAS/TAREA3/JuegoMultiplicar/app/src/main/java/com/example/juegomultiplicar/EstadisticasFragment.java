package com.example.juegomultiplicar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EstadisticasFragment extends Fragment {

    View view;

    TextView numeroTabla,fecha,heroe;
    public EstadisticasFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        Bundle datosRecividos = getArguments();
        if (datosRecividos!= null) {
            numeroTabla = view.findViewById(R.id.areaTablaEsta);
            numeroTabla.setText("Tabla del: " + datosRecividos.getString("numeroTabla", "sin datos"));
            fecha = view.findViewById(R.id.areaFechaEsta);
            fecha.setText("Fecha de la Prueba: " + datosRecividos.getString("fecha", "sin datos"));
            heroe = view.findViewById(R.id.areaHeroeEst);
            heroe.setText("Heroe : " + datosRecividos.getString("heroe", "sin datos"));

        }

        return view;
    }
}