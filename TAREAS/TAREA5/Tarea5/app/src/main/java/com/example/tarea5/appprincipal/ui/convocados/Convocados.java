package com.example.tarea5.appprincipal.ui.convocados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea5.appprincipal.ui.equipo.AdaptadorRecyclerMultipl;
import com.example.tarea5.appprincipal.ui.equipo.EquipoFragment;
import com.example.tarea5.appprincipal.ui.equipo.Jugador;
import com.example.tarea5.databinding.FragmentConvocadosBinding;

import java.util.ArrayList;

public class Convocados extends Fragment {

    private FragmentConvocadosBinding binding;
    private ArrayList<Jugador> jugadores;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentConvocadosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        jugadores = (ArrayList<Jugador>) EquipoFragment.judadoresSeleccionados;
        
        recyclerView = binding.reciclerConvocados;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdaptadorConvocados adaptadorConvocados = new AdaptadorConvocados((ArrayList<Jugador>) EquipoFragment.judadoresSeleccionados);
        recyclerView.setAdapter(adaptadorConvocados);
//        for (int i = 0; i< jugadores.size(); i++){
//            String jugador = jugadores.get(i).getNombre();
//            System.out.println(jugador);
//        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}