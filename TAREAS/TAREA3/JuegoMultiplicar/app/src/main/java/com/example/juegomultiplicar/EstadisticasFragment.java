package com.example.juegomultiplicar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EstadisticasFragment extends Fragment {

    View view;

    TextView numeroTabla, fecha, heroe, fallos;

    public EstadisticasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        Bundle datosRecividos = getArguments();
        if (datosRecividos != null) {
            numeroTabla = view.findViewById(R.id.areaTablaEsta);
            numeroTabla.setText("Tabla del: " + datosRecividos.getString("numeroTabla", "sin datos"));
            fecha = view.findViewById(R.id.areaFechaEsta);
            fecha.setText("Fecha de la Prueba: " + datosRecividos.getString("fecha", "sin datos"));
            heroe = view.findViewById(R.id.areaHeroeEst);
            heroe.setText("Heroe : " + datosRecividos.getString("heroe", "sin datos"));
            fallos = view.findViewById(R.id.areaFallosEsta);
            ArrayList<String> datosFallos = datosRecividos.getStringArrayList("fallos");
            if (datosFallos != null && !datosFallos.isEmpty()) {
                StringBuilder fallosText = new StringBuilder();
                for (String fallo : datosFallos) {
                    fallosText.append(fallo).append("\n");
                }
                fallos.setText("Fallos cometidos:\n"+fallosText.toString()+ "\n");
            } else {
                fallos.setText("No hay fallos registrados.");
            }


        }

        return view;
    }
}