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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
    private String fechaSeleccionada;
    private DatabaseReference mDatabase;
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

        binding = FragmentHistoricoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        consutarFechas();



        return view;
    }

    private void consutarFechas() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Partidos");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> fechas = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String fecha = snapshot.getKey(); // Obtener la fecha del partido
                    fechas.add(fecha);
                }
                mostrarFechasEnSpinner(fechas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de base de datos, si es necesario
            }
        });
    }

    private void mostrarFechasEnSpinner(ArrayList<String> fechas) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, fechas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerHistorico.setAdapter(adapter);

        // Configurar el listener para el Spinner
        binding.spinnerHistorico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fechaSeleccionada = parent.getItemAtPosition(position).toString();
//                Toast.makeText(requireContext(), "Fecha seleccionada: " + fechaSeleccionada, Toast.LENGTH_SHORT).show();
                consultarJugadoresPorFecha(fechaSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada cuando no se selecciona nada
            }
        });
    }
    private void consultarJugadoresPorFecha(String fechaSeleccionada) {
        DatabaseReference fechaRef = FirebaseDatabase.getInstance().getReference().child("Partidos").child(fechaSeleccionada);
        fechaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Jugador> jugadores = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Jugador jugador = snapshot.getValue(Jugador.class);
                    jugadores.add(jugador);
                }
                mostrarJugadoresEnRecyclerView(jugadores);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de base de datos, si es necesario
            }
        });
    }
    private void mostrarJugadoresEnRecyclerView(ArrayList<Jugador> jugadores) {
        RecyclerView recyclerView = binding.reciclerHistorico;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        AdaptadorConvocados adaptadorConvocados = new AdaptadorConvocados(jugadores);
        recyclerView.setAdapter(adaptadorConvocados);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoricoViewModel.class);
        // TODO: Use the ViewModel
    }

}