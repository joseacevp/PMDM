package com.example.tarea3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private static final Object RESULT_OK = 1;
    String email;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;
    RecyclerView recycleBicicletas;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

        View vista = inflater.inflate(R.layout.fragment_bicle,container,false);

        //metodo para cargar los datos del archivo .json en le recycleView
        recycleBicicletas = vista.findViewById(R.id.idRecicle);
        recycleBicicletas.setLayoutManager(new LinearLayoutManager(getContext()));
        BikesContent.clearBikes();
        BikesContent.loadBikesFromJSON(getActivity().getApplicationContext());
        System.out.println(BikesContent.ITEMS.size());
        recycleBicicletas.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(getActivity().getApplicationContext());
        recycleBicicletas.setLayoutManager(layoutManager);
        myAdapter = new Adaptador(BikesContent.ITEMS,this);
        recycleBicicletas.setAdapter(myAdapter);
        //


        return vista;
    }
    // PASO 2
    // Este método captura la selección del usuario
    public void terminar (BikesContent.Bike bike){
        System.out.println("TERMINAR: " + bike.toString());
        Intent i = new Intent();
        i.putExtra("PRODUCTO", bike.getEmail());
        email=bike.getEmail();
        System.out.println(email);

        try {
            //Metodo para enviar Email
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Alquiler de Bicicleta");
            intent.putExtra(Intent.EXTRA_TEXT, "Hola me encantaria alquilar " + "tu maravillosa bicicleta el día " + "\n Un saludo");
            startActivity(intent);
        }catch (Throwable e){
            Toast.makeText(getContext(),"fecha no seleccionada", Toast.LENGTH_LONG).show();
        }

        // Los resultados se devuelven a través de un Intent invocando al método setResult()
        //p3
        //setResult(RESULT_OK,i);

        // Se finaliza la actividad invocando al método finish()
        //finish();
    }
//    private void setResult(Object resultOk, Intent i) {
//    }


}