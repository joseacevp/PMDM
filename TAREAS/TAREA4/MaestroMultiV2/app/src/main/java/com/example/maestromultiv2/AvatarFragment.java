package com.example.maestromultiv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AvatarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvatarFragment extends Fragment {
    View view;
    RecyclerView recyclerView;

    //3
    ArrayList<Avatar> lista;

    private String[] heroes = {"Batman",
            "Hulk",
            "Iron Man",
            "Capitan America"};

    private String[] descripcion = {"Matches Malone\u200B El caballero de la noche El caballero oscuro Zurdo Knox\u200B El señor de la noche\u200B",
            "Hulk, El Increíble Hulk,\u200B El Justiciero, El Hombre Increíble",
            "Tony Stark, Iron Man (El Hombre de Hierro)",
            "Nómada, El Capitán, Cap, Steve Rogers, Steve"
    };

    private int imagenes[] = {R.drawable.batman,
            R.drawable.huld,
            R.drawable.iron,
            R.drawable.capi
    };

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AvatarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AvatarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvatarFragment newInstance(String param1, String param2) {
        AvatarFragment fragment = new AvatarFragment();
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
       view =  inflater.inflate(R.layout.fragment_avatar, container, false);



        construirRecycleView();
        llenarPersonajes();


        return view;
    }

    private void construirRecycleView() {
        lista = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AdaptadorRecycleViewPers adaptador = new AdaptadorRecycleViewPers(lista);

        //metodo para manejar los eventos al click en los intem del RecycleView
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Pulsaste : "
                        + lista.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                NavController navController =
                        Navigation.findNavController(getActivity(),
                                R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_avatarFragment_to_nav_configurar);
            }
        });
        recyclerView.setAdapter(adaptador);
    }

    private void llenarPersonajes() {
        lista.add(new Avatar("Hulk", "El forzudo Verde", R.drawable.huld));
        lista.add(new Avatar("Capitan America", "Heroe Americano", R.drawable.capi));
        lista.add(new Avatar("Iron Man", "Rico Bueno", R.drawable.iron));
    }
}