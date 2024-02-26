package com.example.tarea4v2;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import java.io.InputStream;
import java.util.ArrayList;

//
public class EnviarEstadisticasFragment extends Fragment {
    View view;

    RecyclerView recyclerView;


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


        if (InicioActivity.tengo_permisos) {
            Toast.makeText(getActivity().getApplicationContext(), "Permisos obtenidos", Toast.LENGTH_SHORT).show();
            obtenerContactos();

        }
        construirRecycleView();

        return view;
    }

    private ArrayList<Contacto> obtenerContactos() {
        ArrayList<Contacto> listaContactos = new ArrayList<>();
        ContentResolver cr = getContext().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        try {
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                    // Obtener correo electrónico
                    Cursor emailCursor = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                    String email = "";
                    if (emailCursor != null && emailCursor.moveToFirst()) {
                        email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        emailCursor.close();
                    }

                    // Obtener foto de perfil
                    Bitmap fotoPerfil = null;
                    if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID)) > 0) {
                        Uri contactUri = ContactsContract.Contacts.CONTENT_URI;
                        Uri photoUri = Uri.withAppendedPath(contactUri, id);
                        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, photoUri);
                        if (input != null) {
                            fotoPerfil = BitmapFactory.decodeStream(input);
                        }
                    }
                    listaContactos.add(new Contacto(nombre, email, fotoPerfil));
                }
                cursor.close();
            }
        }catch (Exception e){

        }

        return listaContactos;
    }

    private void construirRecycleView() {
        ArrayList<Contacto> lista = obtenerContactos();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AdaptadorRecyclerViewConta adaptador = new AdaptadorRecyclerViewConta(lista, new AdaptadorRecyclerViewConta.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Contacto contacto = lista.get(position);
                String nombreContacto = contacto.getNombre();
                Toast.makeText(getContext(), "Contacto seleccionado: " + nombreContacto, Toast.LENGTH_SHORT).show();
                // Aquí puedes realizar otras acciones, como navegar a otro fragmento
            }
        });

        recyclerView.setAdapter(adaptador);
    }

}