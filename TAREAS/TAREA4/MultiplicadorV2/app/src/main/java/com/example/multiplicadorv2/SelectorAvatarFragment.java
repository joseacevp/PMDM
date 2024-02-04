package com.example.multiplicadorv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class SelectorAvatarFragment extends Fragment {
    Bundle datos;
    String heroe;
    View view;
    //3
    ArrayList<Personaje> lista;
    RecyclerView recyclerView;



    public SelectorAvatarFragment() {
        // Required empty public constructor
    }


    public static SelectorAvatarFragment newInstance(String param1, String param2) {
        SelectorAvatarFragment fragment = new SelectorAvatarFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_selector_avatar, container, false);


        construirRecycleView();
        llenarPersonajes();


        return view;
    }


    private void construirRecycleView() {
        lista = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycleViewId);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        AdaptadorRecycleViewPers adaptador = new AdaptadorRecycleViewPers(lista);

        //metodo para manejar los eventos al click en los intem del RecycleView
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "seleccionado: "+lista.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                heroe = lista.get(recyclerView.getChildAdapterPosition(view)).getNombre();

                Bundle miDato = new Bundle();
                miDato.putString("dato",heroe);

                ConfiguracionFragment configuracionFragment = new ConfiguracionFragment();
                configuracionFragment.setArguments(miDato);
                selectorFragment(configuracionFragment);
            }
        });
        recyclerView.setAdapter(adaptador);
    }

    private void llenarPersonajes() {
        lista.add(new Personaje("Hulk", "El forzudo Verde", R.drawable.hulk));
        lista.add(new Personaje("Capitan America", "Heroe Americano", R.drawable.capitan));
        lista.add(new Personaje("Iron Man", "Rico Bueno", R.drawable.iron));
    }
    private void selectorFragment(Fragment f) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //relaciona el fragment recibido "f" con el contenedor de fragment 'frame_container'
        transaction.replace(R.id.contenedor, f);
        transaction.commit();
    }
}