package com.example.maestromultiv2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maestromultiv2.basedatos.ConexionSqlLite;
import com.example.maestromultiv2.basedatos.Utilidades;

public class LoginFragment extends Fragment {

    View view;
    EditText passwordLog;
    Button botonRegistroLog;
    ConexionSqlLite con;
    SQLiteDatabase db;
    private String nombreUsuario = "1";

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);


        //instancia de la base de datos para consultar leer
        con = new ConexionSqlLite(getActivity(), "base_datos_tarea4", null, 1);
        db = con.getReadableDatabase();
        passwordLog = view.findViewById(R.id.area_Password_login);
        botonRegistroLog = view.findViewById(R.id.boton_login);

        botonRegistroLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conprobarCredenciales(passwordLog.getText().toString(), nombreUsuario)) {

                    Toast.makeText(getActivity(), "DATOS CORRECTOS", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Los datos no son correctos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    private boolean conprobarCredenciales(String password,
                                          String nombre) {
        String passRecuperado;

        //parametros de la consulta
        String[] parametros = {nombre};

        Cursor cursor = db.rawQuery("SELECT " + Utilidades.PASSWORD
                + " FROM " + Utilidades.TABLA_USUARIOS
                + " WHERE " + Utilidades.NOMBRE + "=?", parametros);
        // Verificar si el cursor contiene algún resultado
        if (cursor != null && cursor.moveToFirst()) {
            passRecuperado = cursor.getString(0);
            System.out.println(passRecuperado);
            // Comprobar si la contraseña recuperada coincide con la contraseña ingresada
            if (passRecuperado.equals(password)) {
                System.out.println("VALIDO");
                return true;
            }
        }
        cursor.close();
        return false;
    }

}