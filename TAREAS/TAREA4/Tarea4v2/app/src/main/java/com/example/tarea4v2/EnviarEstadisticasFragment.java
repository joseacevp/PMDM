package com.example.tarea4v2;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

//
public class EnviarEstadisticasFragment extends Fragment {
    View view;

    RecyclerView recyclerView;
    private boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EnviarEstadisticasFragment() {
        // Required empty public constructor
    }


    public static EnviarEstadisticasFragment newInstance(String param1, String param2) {
        EnviarEstadisticasFragment fragment = new EnviarEstadisticasFragment();
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
        view = inflater.inflate(R.layout.fragment_enviar_estadisticas, container, false);
        recyclerView = view.findViewById(R.id.recyclerContactos);
        // Solicitud de permisos
        if (getContext().getApplicationContext().checkSelfPermission("android.permission.READ_CONTACTS") != PackageManager.PERMISSION_GRANTED
                ||
                getContext().getApplicationContext().checkSelfPermission("android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                            "android.permission.READ_CONTACTS",
                            "android.permission.SEND_SMS"},
                    PETICION_PERMISOS);
        } else {tengo_permisos = true;}

        if (tengo_permisos) {
            Toast.makeText(getActivity().getApplicationContext(), "Permisos obtenidos", Toast.LENGTH_SHORT).show();
            obtenerContactos();

        }
        construirRecycleView();

        return view;
    }

    private ArrayList<String> obtenerContactos() {
        ArrayList<String> listaContactos = new ArrayList<>();
        ContentResolver cr = getContext().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));//                String nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                listaContactos.add(nombre);
            }
            cursor.close();
        }
        return listaContactos;
    }

    private void construirRecycleView() {
        ArrayList<String> lista = obtenerContactos();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AdaptadorRecyclerViewConta adaptador = new AdaptadorRecyclerViewConta(lista);

        //metodo para manejar los eventos al click en los intem del RecycleView
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                heroe = lista.get(recyclerView.getChildAdapterPosition(view)).getNombre();
//
//                guardarPreferencias();

                //accion para llamar an fragmento contenedor del recyclerView avatar
//                Navigation.findNavController(view).navigate(R.id.action_avatarFragment_to_nav_configurar);

            }
        });
        recyclerView.setAdapter(adaptador);
    }

}