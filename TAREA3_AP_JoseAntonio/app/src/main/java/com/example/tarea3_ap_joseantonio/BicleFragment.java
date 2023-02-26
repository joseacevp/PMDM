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
public class BicleFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    String fecha;
//    //referencia a //
//
    RecyclerView recycleBicicletas;
//
//    CalenFragment calenFragment = new CalenFragment();

   RecyclerView.LayoutManager layoutManager;
   RecyclerView.Adapter myAdapter;


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

        BikesContent.clearBikes();
        BikesContent.loadBikesFromJSON(getContext());



        this.recycleBicicletas.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());

        recycleBicicletas.setLayoutManager(layoutManager);

        myAdapter = new Adaptador(BikesContent.ITEMS,this);
        this.recycleBicicletas.setAdapter(myAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View vista = inflater.inflate(R.layout.fragment_bicle, container, false);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View bicicletaFragment, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(bicicletaFragment, savedInstanceState);

    }

    // PASO 2
    // Este método captura la selección del usuario
//    public void terminar(BikesContent.Bike product){
//        //System.out.println("TERMINAR: " + product.toString());
//        Intent i = new Intent();
//        i.putExtra("PRODUCTO", product.getDescription());
//
//        // Los resultados se devuelven a través de un Intent invocando al método setResult()
//        setResult(RESULT_OK,i);
//
//        // Se finaliza la actividad invocando al método finish()
//        finish();
//    }
}