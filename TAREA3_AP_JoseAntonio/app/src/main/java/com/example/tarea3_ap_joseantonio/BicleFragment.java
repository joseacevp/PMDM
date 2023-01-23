package com.example.tarea3_ap_joseantonio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BicleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BicleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String fecha;
    //referencia a //

    RecyclerView recycleBicicletas;
    ArrayList<Bikes> listaBicicletas;

    CalenFragment calenFragment = new CalenFragment();
    public BicleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BicleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BicleFragment newInstance(String param1, String param2) {
        BicleFragment fragment = new BicleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        String fechaElegida;
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
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                fecha = result.getString("fechaKey");

            }
        });
        View vista =inflater.inflate(R.layout.fragment_bicle, container, false);
        listaBicicletas=new ArrayList<>();

        recycleBicicletas= vista.findViewById(R.id.idRecicle);

        recycleBicicletas.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        Adaptador adaptador= new Adaptador(listaBicicletas);
        recycleBicicletas.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"email@email.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Alquiler de Bicicleta");
                intent.putExtra(Intent.EXTRA_TEXT,"Hola me encantaria alquilar " +
                        "tu maravillosa bicicleta el día "+ fecha + "\n Un saludo");
                startActivity(intent);

            }

        });


        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View bicicletaFragment, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(bicicletaFragment, savedInstanceState);




    }

    private void llenarLista() {

        listaBicicletas.add(new Bikes(R.drawable.bike01,"Localizacion","Descripcion de la bicicleta",R.drawable.mail));
        listaBicicletas.add(new Bikes(R.drawable.bike02,"Localizacion","Descripcion de la bicicleta",R.drawable.mail));
        listaBicicletas.add(new Bikes(R.drawable.bike03,"Localizacion","Descripcion de la bicicleta",R.drawable.mail));
        listaBicicletas.add(new Bikes(R.drawable.bike04,"Localizacion","Descripcion de la bicicleta",R.drawable.mail));
        listaBicicletas.add(new Bikes(R.drawable.bike05,"Localizacion","Descripcion de la bicicleta",R.drawable.mail));
    }
}