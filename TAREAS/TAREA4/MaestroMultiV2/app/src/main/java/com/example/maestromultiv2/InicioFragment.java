package com.example.maestromultiv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maestromultiv2.basedatos.ConexionSqlLite;
import com.example.maestromultiv2.basedatos.Utilidades;

import java.util.ArrayList;
import java.util.Random;


public class InicioFragment extends Fragment {

    View view;
    Spinner usuarios;
    Button botonInicio, botonRegistro;
    private ArrayList<String> lista_usuarios;
    static String usuario;
    private ConexionSqlLite con;

    public InicioFragment() {
        // Required empty public constructor
    }


    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inicio, container, false);

        usuarios = view.findViewById(R.id.spinner_usuarios_inicio);
        botonInicio = view.findViewById(R.id.botonSeleccionInicio);
        botonRegistro = view.findViewById(R.id.botonRegistroInico);

        //instancia de la base de datos para consultar leer
        con = new ConexionSqlLite(getActivity(), "base_datos_tarea4", null, 1);
        if (!comprobarUsuarioAdministrador()) {
            guardarUsuarioAdministrador();
        }
//        forma de crear un adaptador para usar un ArrayList en vez de una lista de Item desde Archivo en Values
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, cargarDatosUsuariosSQL());
        usuarios.setAdapter(adapter);
        usuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //accion para llamar an fragmento contenedor del recyclerView avatar
                usuario = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPreferencias();
                Navigation.findNavController(view).navigate(R.id.action_inicioFragment_to_loginFragment);
            }
        });
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_inicioFragment_to_nav_formulario_usuario);
            }
        });


        return view;
    }

    private ArrayList<String> cargarDatosUsuariosSQL() {
        lista_usuarios = new ArrayList<>();
        String nombre = "";
        SQLiteDatabase db = con.getReadableDatabase();
        //parametros de la consulta
        String[] parametros = {" usuario "};
        try {
            Cursor cursor = db.rawQuery("SELECT  DISTINCT " + Utilidades.NOMBRE + " FROM " + Utilidades.TABLA_USUARIOS, null);

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
        return lista_usuarios;
    }

    //metodo que almacena los datos de los campos de texto en un archivo .XML para compartirlos
    //con otra actividad de la aplicación.
    private void guardarPreferencias() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(10) + 1;

        SharedPreferences preferencias = getContext().getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("usuario", usuario);

        editor.commit();
    }

    private void guardarUsuarioAdministrador() {


        //crea la base de datos
        ConexionSqlLite conn = new ConexionSqlLite(getActivity(),
                "base_datos_tarea4", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            String insert = "INSERT INTO " + Utilidades.TABLA_USUARIOS
                    + " ( "
                    + Utilidades.NOMBRE + " , "
                    + Utilidades.PASSWORD
                    + " )  "
                    + "VALUES ( ' " + "" +
                    "Administrador" + "' , ' "
                    + "1234" + " ' )";

            db.execSQL(insert);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Fallo al registrar Usuario." + e.toString(), Toast.LENGTH_SHORT).show();

        }
    }

    private boolean comprobarUsuarioAdministrador() {
        boolean resultado = false;

        for (int i = 0; i < cargarDatosUsuariosSQL().size(); i++) {
            String usuarioAdministrador = cargarDatosUsuariosSQL().get(i);
            if (usuarioAdministrador == "Administrador") {
                resultado = true;
            }
        }
        return resultado;
    }
}