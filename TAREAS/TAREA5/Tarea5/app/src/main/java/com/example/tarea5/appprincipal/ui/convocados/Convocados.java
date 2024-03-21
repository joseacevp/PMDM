package com.example.tarea5.appprincipal.ui.convocados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea5.R;
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
        if (jugadores != null) {
            recyclerView = binding.reciclerConvocados;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            AdaptadorConvocados adaptadorConvocados = new AdaptadorConvocados(jugadores);
            recyclerView.setAdapter(adaptadorConvocados);
        } else {
            Toast.makeText(root.getContext(), "¡No Hay Convocados Aun!", Toast.LENGTH_SHORT).show();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}