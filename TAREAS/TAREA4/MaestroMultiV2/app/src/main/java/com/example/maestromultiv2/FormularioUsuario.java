package com.example.maestromultiv2;

import android.content.ContentValues;
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

public class FormularioUsuario extends Fragment {
    View view;
    EditText nombre_formulario_usuario, pass_formulario_usuario;
    Button boton_registrar_usuario_formulario;

    public FormularioUsuario() {
        // Required empty public constructor
    }


    public static FormularioUsuario newInstance(String param1, String param2) {
        FormularioUsuario fragment = new FormularioUsuario();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_formulario_usuario, container, false);

        //capturamos los datos del formulario
        nombre_formulario_usuario = view.findViewById(R.id.area_nombre_formu_usua);
        pass_formulario_usuario = view.findViewById(R.id.area_password_formu_usua);

        boton_registrar_usuario_formulario = view.findViewById(R.id.boton_registrar_usuario_formulario);

        //evento click boton registrar usuario
        boton_registrar_usuario_formulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuarios();
                limpiarFormulario();
            }
        });

        return view;
    }

    private void limpiarFormulario() {
        nombre_formulario_usuario.setText("");
        pass_formulario_usuario.setText("");
    }

    private void registrarUsuarios() {
        //crea la base de datos
        ConexionSqlLite conn = new ConexionSqlLite(getActivity(),
                "base_datos_tarea4", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            String insert = "INSERT INTO " + Utilidades.TABLA_USUARIOS
                    + " ( "
                    + Utilidades.NOMBRE
                    + " , "
                    + Utilidades.PASSWORD
                    + " )  "
                    + "VALUES ( ' " + nombre_formulario_usuario.getText().toString()
                    + "' , ' "
                    + pass_formulario_usuario.getText().toString()
                    + " ' )";

            db.execSQL(insert);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Fallo al registrar Usuario.", Toast.LENGTH_SHORT).show();

        }
        //añadir datos a la base de datos con values
//        ContentValues values = new ContentValues();
//        values.put(Utilidades.NOMBRE,nombre_formulario_usuario.getText().toString());
//        values.put(Utilidades.PASSWORD,pass_formulario_usuario.getText().toString());
//
//        Long idResultante = db.insert(Utilidades.TABLA_USUARIOS,Utilidades.NOMBRE,values);
//        Toast.makeText(getActivity(), "Registrado el usuario: "+ idResultante, Toast.LENGTH_SHORT).show();

    }
}