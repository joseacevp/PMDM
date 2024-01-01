package com.example.maestromultiplicador.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.maestromultiplicador.databinding.FragmentEstadisticasBinding;

public class EstadisticasFragment extends Fragment {

    private FragmentEstadisticasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEstadisticasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}