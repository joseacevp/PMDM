package com.example.tarea5.appprincipal.ui.fechas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tarea5.R;
import com.example.tarea5.databinding.FragmentEquipoBinding;
import com.example.tarea5.databinding.FragmentFechaBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FechaFragment extends Fragment implements DialogoFecha.OnFechaSeleccionada{

    private FechaViewModel mViewModel;
    private FragmentFechaBinding binding;
    TextView textoFecha;
    public static FechaFragment newInstance() {
        return new FechaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFechaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        DialogoFecha fecha = new DialogoFecha();
        // Establece ConfiguracionFragment como el oyente para los eventos de fecha
        fecha.setFechaSeleccionadaListener(FechaFragment.this);
        fecha.show(getFragmentManager(), "fecha");
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FechaViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        //resultado de la fecha seleccionada
        textoFecha = binding.textoFechaSeleccionada;
        textoFecha.setText(fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR));
    }
}