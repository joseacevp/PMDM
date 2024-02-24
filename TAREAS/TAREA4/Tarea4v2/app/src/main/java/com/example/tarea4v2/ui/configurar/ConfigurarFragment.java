package com.example.tarea4v2.ui.configurar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tarea4v2.Partida;
import com.example.tarea4v2.databinding.FragmentComfigurarBinding;

public class ConfigurarFragment extends Fragment {

    private FragmentComfigurarBinding binding;
    TextView fecha;
    EditText numeroTabla;
    Button botonAvatar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConfigurarViewModel configurarViewModel =
                new ViewModelProvider(this).get(ConfigurarViewModel.class);

        binding = FragmentComfigurarBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}