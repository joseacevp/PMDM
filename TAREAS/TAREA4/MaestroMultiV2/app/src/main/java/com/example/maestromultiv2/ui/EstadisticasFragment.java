package com.example.maestromultiv2.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maestromultiv2.Avatar;
import com.example.maestromultiv2.R;
import com.example.maestromultiv2.basedatos.ConexionSqlLite;
import com.example.maestromultiv2.basedatos.Utilidades;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstadisticasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstadisticasFragment extends Fragment {
    View view;
    Spinner usuarios, fechas, numero_tabla;
    TextView campo_fallos;
    ConexionSqlLite con;
    private String tabla, dificultad, heroe, fecha, aleatorio;

    public EstadisticasFragment() {
        // Required empty public constructor
    }


    public static EstadisticasFragment newInstance(String param1, String param2) {
        EstadisticasFragment fragment = new EstadisticasFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        //instancia de la base de datos para consultar leer
        con = new ConexionSqlLite(getActivity(), "base_datos_tarea4", null, 1);


        usuarios = view.findViewById(R.id.spinner_estadis_usuarios);
        fechas = view.findViewById(R.id.spinner_estadis_fechas);
        numero_tabla = view.findViewById(R.id.spinner_estadis_tablas);
        campo_fallos = view.findViewById(R.id.area_fallos_estadis);
        cargarSpinnerUsuarios();
        return view;
    }

    private void cargarSpinnerUsuarios() {
        ArrayList<String> lista_usuarios = new ArrayList<>();
        String nombre = "";
        SQLiteDatabase db = con.getReadableDatabase();
        //parametros de la consulta
        String[] parametros = {" usuario "};
        try {
            Cursor cursor = db.rawQuery("SELECT  " + Utilidades.USUARIO + " FROM " + Utilidades.TABLA_PARTIDAS, null);


            while (cursor.moveToNext()) {
                String usuario = cursor.getString(0);
                lista_usuarios.add(usuario);
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Sin usuarios registrados", Toast.LENGTH_SHORT).show();

        }
        //COMPROBACION DE DATOS RECUPERADOS
        for (int i = 0; i < lista_usuarios.size(); i++) {
            nombre += lista_usuarios.get(i).toString();
        }
        System.out.println(nombre);
       //
    }


}