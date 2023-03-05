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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BicleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BicleFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Object RESULT_OK = 1;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;

    String fecha;
    String email;
    Bundle bundle;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //referencia a //

    RecyclerView recycleBicicletas;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View vista = inflater.inflate(R.layout.fragment_bicle, container, false);

        //p2
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



        return vista;
    }

    // PASO 2
    // Este método captura la selección del usuario
    public void terminar (BikesContent.Bike bike){
        System.out.println("TERMINAR: " + bike.toString());
        Intent i = new Intent();
        i.putExtra("PRODUCTO", bike.getEmail());

        // Los resultados se devuelven a través de un Intent invocando al método setResult()
        //p3
        setResult(RESULT_OK,i);

        // Se finaliza la actividad invocando al método finish()
        //finish();
    }

    private void setResult(Object resultOk, Intent i) {
    }

    @Override
    public void onViewCreated(@NonNull View bicicletaFragment, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(bicicletaFragment, savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                fecha = result.getString("fechaKey");
                Toast.makeText(getContext(),fecha, Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        //Recupera los datos de la fecha seleccionada
        try {
            //Metodo para enviar Email
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Alquiler de Bicicleta");
            intent.putExtra(Intent.EXTRA_TEXT, "Hola me encantaria alquilar " + "tu maravillosa bicicleta el día " +fecha+  "\n Un saludo");
            startActivity(intent);
        }catch (Throwable e){
            Toast.makeText(getContext(),"fecha no seleccionada", Toast.LENGTH_LONG).show();
        }

    }
}