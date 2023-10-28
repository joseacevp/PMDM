package com.example.basedatosoperariomaterial;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.basedatosoperariomaterial.Utilidades.ConexioSQLiteHelper;
import com.example.basedatosoperariomaterial.Utilidades.Utilidades;
import com.example.basedatosoperariomaterial.entidades.Operario;

import java.util.ArrayList;
import java.util.Locale;

public class ActualizarOperario extends AppCompatActivity {

    String departamentoSeleccionado = "";
    Button botonBuscar;
    Spinner spinnerDepartamentos;
    ConexioSQLiteHelper conexion;
    SQLiteDatabase baseDatos;
    Operario operario;
    ArrayList<Operario> listaOperarios = new ArrayList<Operario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_operario);

        //asignaciones
        spinnerDepartamentos = findViewById(R.id.spinnerDepartamento);
        botonBuscar = findViewById(R.id.buttonBucarOperario);

        ArrayAdapter<CharSequence> adaptadorDepartamentos = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinner_departamentos, android.R.layout.simple_spinner_item);
        spinnerDepartamentos.setAdapter(adaptadorDepartamentos);

        //metodo para recuperar el dato de la seleccion del Spinner departamentos
        spinnerDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                if (posicion!=0){
                    departamentoSeleccionado = spinnerDepartamentos.getSelectedItem().toString();
                    Toast.makeText(getApplicationContext(), departamentoSeleccionado, Toast.LENGTH_SHORT).show();
                    //consultar a la base de datos operarios segun departamentos
                    consultarOperarios();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //consultar a la base de datos operarios segun departamentos
        consultarOperarios();
    }

    private void consultarOperarios() {
        conexion = new ConexioSQLiteHelper(getApplicationContext(), "base_dato", null, 1);
        baseDatos = conexion.getReadableDatabase();
        //campo para comparar y seleccionar el resultado
        String[] parametrosConsulta = {
               departamentoSeleccionado
        };
        //datos a optener desde la base de datos através de la consulta
        String[] resultadoConsulta = {
                Utilidades.CAMPO_NOMBREOPERARIO,
                Utilidades.CAMPO_ID_OPERARIO
        };
        //consulta mediante cursor
        Cursor cursor = baseDatos.query(Utilidades.TABLA_OPERARIOS,
                resultadoConsulta,
                Utilidades.CAMPO_DEPARTAMENTO + " = ? ",
                parametrosConsulta,
                null,
                null,
                Utilidades.CAMPO_ID_OPERARIO);
        //bucle par optener todos los resultados de la consulta
        while (cursor.moveToNext()) {
            operario = new Operario();
            operario.setNombre(cursor.getString(0));
            operario.setIdOperario(cursor.getInt(1));
            operario.setDepartamento(departamentoSeleccionado);
            //añade los operarios a la lista de operarios
            listaOperarios.add(operario);
            Log.i("info", operario.getNombre().toString());
            Log.i("info", "Operario guardado");

        }
        baseDatos.close();
        trasladarLista();

    }

    private void trasladarLista() {
        
    }


    public void onClick(View view) {
        consultarOperarios();
    }
}