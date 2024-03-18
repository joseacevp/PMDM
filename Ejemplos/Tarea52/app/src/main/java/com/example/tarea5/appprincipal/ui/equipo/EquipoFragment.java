package com.example.tarea5.appprincipal.ui.equipo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea5.R;
import com.example.tarea5.appprincipal.AppPrincipal;
import com.example.tarea5.databinding.FragmentEquipoBinding;

import java.util.ArrayList;

public class EquipoFragment extends Fragment {
    //3
    ArrayList<Jugador> lista;
    RecyclerView recyclerView;
    private FragmentEquipoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentEquipoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        construirRecycleView();
        llenarPersonajes();
        return view;
    }

    private void construirRecycleView() {
        lista = new ArrayList<>();
        recyclerView = binding.reciclerJugadores;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AdaptadorRecycleViewPers adaptador = new AdaptadorRecycleViewPers(lista);

        //metodo para manejar los eventos al click en los intem del RecycleView
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Pulsaste : "
                        + lista.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adaptador);
    }

    private void llenarPersonajes() {
        lista.add(new Jugador("Enrrique Similo", "Delantero Centro", R.drawable.carita01));
        lista.add(new Jugador("Juan Bueno", "Delantero Centro", R.drawable.carita02));
        lista.add(new Jugador("Lucas Disorio", "Delantero Centro", R.drawable.carita03));
        lista.add(new Jugador("Ernesto Zifuentes", "Delantero Derecho", R.drawable.carita04));
        lista.add(new Jugador("Lolo Anoeta", "Delantero Derecho", R.drawable.carita05));
        lista.add(new Jugador("Lucas Jacinto", "Delantero Derecho", R.drawable.carita06));
        lista.add(new Jugador("Luis Paulino", "Delantero Izquierdo", R.drawable.carita07));
        lista.add(new Jugador("Gregorio Antuano", "Delantero Izquierdo", R.drawable.carita08));
        lista.add(new Jugador("Daviz Salinas", "Central", R.drawable.carita09));
        lista.add(new Jugador("Eustaquio Loindiuo", "Portero", R.drawable.carita10));
        lista.add(new Jugador("Felipa Garcia", "Central Derecho", R.drawable.carita12));
        lista.add(new Jugador("Noel Sal", "Cetral Derechio", R.drawable.carita13));
        lista.add(new Jugador("Laureano Andujar", "Central Izquierdo", R.drawable.carita14));
        lista.add(new Jugador("Santiago Robledano", "Cetral Izquierdo", R.drawable.carita15));
        lista.add(new Jugador("Raul Luisiana", "Defensa Cetral", R.drawable.carita11));
        lista.add(new Jugador("Tote Litunia", "Defensa Derecha", R.drawable.carita16));
        lista.add(new Jugador("Fran Frances", "Defensa Derecha", R.drawable.carita17));
        lista.add(new Jugador("Koke Ponpidu", "Defensa Izquierda", R.drawable.carita18));
        lista.add(new Jugador("Rick Whilson", "Defensa Izquierda", R.drawable.carita19));
        lista.add(new Jugador("Chipian Lilione", "Protero", R.drawable.carita20));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}