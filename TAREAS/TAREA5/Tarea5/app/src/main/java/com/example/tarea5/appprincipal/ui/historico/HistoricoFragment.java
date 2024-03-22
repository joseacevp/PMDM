package com.example.tarea5.appprincipal.ui.historico;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarea5.R;
import com.example.tarea5.appprincipal.ui.convocados.AdaptadorConvocados;
import com.example.tarea5.appprincipal.ui.equipo.Jugador;
import com.example.tarea5.databinding.FragmentConvocadosBinding;
import com.example.tarea5.databinding.FragmentHistoricoBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoricoFragment extends Fragment {

    private HistoricoViewModel mViewModel;
    private FragmentHistoricoBinding binding;
    private ArrayList<Jugador> jugadores;
    private Jugador jugador;
    private String fecha;
    RecyclerView recyclerView;
    public static HistoricoFragment newInstance() {
        return new HistoricoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentHistoricoBinding.inflate(inflater, container,false);
        View view = binding.getRoot();

        //consulta la lista de jugadores segun la fecha seleccionada
        consultarBaseDatos(fecha);

        recyclerView = binding.reciclerHistorico;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AdaptadorConvocados adaptadorConvocados = new AdaptadorConvocados(jugadores);
        recyclerView.setAdapter(adaptadorConvocados);

        return view;
    }

    //consulta la lista de jugadores segun la fecha seleccionada
    private ArrayList<Jugador>  consultarBaseDatos(String fecha) {
        ArrayList<Jugador> listaJugadoresConsula = null;
        DatabaseReference partidosRef = FirebaseDatabase.getInstance().getReference().child("Partidos").child(fecha);

        partidosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Obtener los datos de cada jugador y agregarlos a la lista
                    String nombre = snapshot.child("nombre").getValue(String.class);
                    String posicion = snapshot.child("posicion").getValue(String.class);
                    int foto = snapshot.child("foto").getValue(Integer.class);
                    int favorito = snapshot.child("favorito").getValue(Integer.class);

                    // Añadir jugador a la lista
                    listaJugadoresConsula.add(new Jugador(nombre, posicion,foto,favorito));
                }

                // Hacer algo con la lista de jugadores obtenida
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejo de errores
            }
        });
        return listaJugadoresConsula;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoricoViewModel.class);
        // TODO: Use the ViewModel
    }

}