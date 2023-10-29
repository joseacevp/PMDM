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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.basedatosoperariomaterial.Utilidades.ConexioSQLiteHelper;
import com.example.basedatosoperariomaterial.Utilidades.Utilidades;
import com.example.basedatosoperariomaterial.entidades.Operario;

import java.util.ArrayList;

public class ActualizarOperario extends AppCompatActivity {

    Integer idOperario;
    String departamentoSeleccionado = "";
    Button botonBuscar;
    Spinner spinnerDepartamentos, spinnerOperarios;
    ConexioSQLiteHelper conexion;
    SQLiteDatabase baseDatos;
    Operario operario;
    ArrayList<Operario> listaOperarios = new ArrayList<Operario>();
    ArrayList<String> listaStringOperarios = new ArrayList<String>();
    EditText nombre;
    EditText departamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_operario);

        //asignaciones
        spinnerDepartamentos = findViewById(R.id.spinnerDepartamento);
        spinnerOperarios = findViewById(R.id.spinnerOperarios);
        botonBuscar = findViewById(R.id.buttonActualizarOper);
        nombre = findViewById(R.id.editTextNombre);
        departamento = findViewById(R.id.editTextDepartamentoOperario);

        crearSpinner();

        //adaptadores Spinner
        ArrayAdapter<CharSequence> adaptadorDepartamentos = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.spinner_departamentos, android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartamentos.setAdapter(adaptadorDepartamentos);


    }

    private void crearSpinner() {

        //metodo para recuperar el dato de la seleccion del Spinner departamentos
        spinnerDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                if (posicion != 0) {
                    departamentoSeleccionado = spinnerDepartamentos.getSelectedItem().toString();
                    Toast.makeText(getApplicationContext(), departamentoSeleccionado, Toast.LENGTH_SHORT).show();
                    //consultar a la base de datos operarios segun departamentos
                    consultarOperarios(departamentoSeleccionado);
                    //carga la lista de operarios en el spinner
                    ArrayAdapter<CharSequence> adaptardorOperarios = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_spinner_item, listaStringOperarios);
                    spinnerOperarios.setAdapter(adaptardorOperarios);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //metodo para escribir los datos del operario seleccionado en los campos editables
        spinnerOperarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                if (posicion != 0) {
                    nombre.setText(listaOperarios.get(posicion -1 ).getNombre());
                    departamento.setText(listaOperarios.get(posicion -1 ).getDepartamento());
                    idOperario = listaOperarios.get(posicion -1 ).getIdOperario();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultarOperarios(String departamento) {
        conexion = new ConexioSQLiteHelper(getApplicationContext(), "base_dato", null, 1);
        baseDatos = conexion.getReadableDatabase();
        //campo para comparar y seleccionar el resultado
        String[] parametrosConsulta = {
                departamento
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
        listaStringOperarios.add("Operarios\b");
        for (int i = 0; i < listaOperarios.size(); i++) {
            listaStringOperarios.add("Id: " + listaOperarios.get(i).getIdOperario() + " Nombre: " + listaOperarios.get(i).getNombre());
            Log.i("info", listaOperarios.get(i).getNombre());
        }
    }

    public void onClickActualizar(View view) {
    actualizar();
    finish();
    }

    private void actualizar() {
        conexion = new ConexioSQLiteHelper(getApplicationContext(), "base_dato", null, 1);
        baseDatos = conexion.getReadableDatabase();
        String[] consultaParametros = {
                idOperario.toString()
                };//parametros de la consulta, pueden ser varios
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBREOPERARIO,nombre.getText().toString());
        values.put(Utilidades.CAMPO_DEPARTAMENTO,departamento.getText().toString());
        //sustituye a la consulta SQL select  nombre,telefono from usuario
        baseDatos.update(Utilidades.TABLA_OPERARIOS,values,Utilidades.CAMPO_ID_OPERARIO +" = ?",consultaParametros);
        Toast.makeText(getApplicationContext(),"Datos Actualizados",Toast.LENGTH_SHORT).show();
        baseDatos.close();

    }
}