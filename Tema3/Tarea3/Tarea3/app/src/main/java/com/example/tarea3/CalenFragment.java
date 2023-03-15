package com.example.tarea3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalenFragment extends Fragment {
    Button boton ;
    Bundle bundle = new Bundle();
    String fecha;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalenFragment newInstance(String param1, String param2) {
        CalenFragment fragment = new CalenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_calen, container, false);
         return vista;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boton= view.findViewById(R.id.botonFecha);

        if (fecha == null) {
            boton.setVisibility(View.INVISIBLE);
            elegirFecha(view);
        } else {
            boton.setVisibility(View.VISIBLE);
        }
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elegirFecha( view);
            }
        });

    }

    public void elegirFecha(View view) {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this.getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                System.out.println(fecha);
                bundle.putString("fecha", fecha);
                getParentFragmentManager().setFragmentResult("fechaKey", bundle);

            }
        }, anio, mes, dia);

        dpd.show();

    }


}