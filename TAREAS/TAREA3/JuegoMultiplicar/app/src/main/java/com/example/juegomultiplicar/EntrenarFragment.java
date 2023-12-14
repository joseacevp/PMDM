package com.example.juegomultiplicar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntrenarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntrenarFragment extends Fragment {

    View view;


    private int[] imagen = {R.drawable.batmanuno
            , R.drawable.batmandos, R.drawable.batmantres
            , R.drawable.batmancuatro, R.drawable.batmancinco, R.drawable.batmanseis
            , R.drawable.batmansiete, R.drawable.batmanocho
            , R.drawable.batmannueve, R.drawable.batman};
    private int indiceActual = 0;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EntrenarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EntrenarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntrenarFragment newInstance(String param1, String param2) {
        EntrenarFragment fragment = new EntrenarFragment();
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
        view = inflater.inflate(R.layout.fragment_entrenar, container, false);




        mostrarImagen();
        ///////   prueba imagen progresiva
        Button bonton_cero = view.findViewById(R.id.boton_tabla_cero);
        bonton_cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mostrarImagen();
                mostrarBara();
            }
        });
        ///////           fin
        return view;

    }

    private void mostrarBara() {
        ProgressBar bar = view.findViewById(R.id.progressBar);

            indiceActual++;


        bar.setProgress(indiceActual);
    }

    private void mostrarImagen() {
            ImageView imageView = view.findViewById(R.id.imageViewHeroe);
        if (indiceActual < imagen.length - 1) {
            indiceActual++;

        } else {
            indiceActual = 0;
        }
        imageView.setImageResource(imagen[indiceActual]);
    }

}