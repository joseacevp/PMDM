package com.example.maestromultiplicador.ui.estadisticas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.example.maestromultiplicador.databinding.FragmentEstadisticasBinding;

public class EstadisticasFragment extends Fragment {

    private FragmentEstadisticasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EstadisticasViewModel notificationsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(EstadisticasViewModel.class);

        binding = FragmentEstadisticasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEstadisticas;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}