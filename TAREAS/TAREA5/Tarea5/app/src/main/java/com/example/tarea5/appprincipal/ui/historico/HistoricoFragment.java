package com.example.tarea5.appprincipal.ui.historico;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarea5.R;
import com.example.tarea5.databinding.FragmentConvocadosBinding;

public class HistoricoFragment extends Fragment {

    private HistoricoViewModel mViewModel;
    private FragmentConvocadosBinding binding;

    public static HistoricoFragment newInstance() {
        return new HistoricoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentConvocadosBinding.inflate(inflater, container,false);
        View view = binding.getRoot();


        
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoricoViewModel.class);
        // TODO: Use the ViewModel
    }

}