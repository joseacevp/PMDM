package com.example.tarea4v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InicioActivity extends AppCompatActivity {
    View view;
    Spinner usuarios;
    Button botonInicio, botonRegistro;
    private ArrayList<String> lista_usuarios;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        usuarios = findViewById(R.id.spinner_usuarios_inicio);
        botonInicio = findViewById(R.id.botonSeleccionInicio);
        botonRegistro = findViewById(R.id.botonRegistroInico);

        crearBaseDatos();

//      Spinner usuarios
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item, cargarDatosUsuariosSQL());
        usuarios.setAdapter(adapter);
        usuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //accion para llamar an fragmento contenedor del recyclerView avatar
//                usuario = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void crearBaseDatos() {
        db=openOrCreateDatabase("BaseDatosTarea4", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuarios(Nombre VARCHAR ,Password VARCHAR);");
        db.execSQL("INSERT OR IGNORE INTO Usuarios (Nombre, Password)\n" +
                "SELECT 'Administrador', '1234'\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT 1 FROM Usuarios WHERE Nombre = 'Administrador'\n" +
                "); ");

    }

    private ArrayList<String> cargarDatosUsuariosSQL() {
        lista_usuarios = new ArrayList<>();
        ArrayAdapter<String> adaptador;
        Cursor c = db.rawQuery("SELECT * FROM Usuarios", null);
        if (c.getCount() == 0)
            lista_usuarios.add("No hay registros");
        else {
            while (c.moveToNext())
                lista_usuarios.add(c.getString(0));
        }

        c.close();

        return lista_usuarios;
    }
}