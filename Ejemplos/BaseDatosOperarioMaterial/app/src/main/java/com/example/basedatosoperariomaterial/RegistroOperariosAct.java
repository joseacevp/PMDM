package com.example.basedatosoperariomaterial;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basedatosoperariomaterial.Utilidades.ConexioSQLiteHelper;
import com.example.basedatosoperariomaterial.Utilidades.Utilidades;

public class RegistroOperariosAct extends AppCompatActivity implements View.OnClickListener {

    TextView nombreOperario, departamento;
    Button botonRegistro;
    ConexioSQLiteHelper conexion;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_operarios);

        //referencias
        nombreOperario = findViewById(R.id.editTextNombreOperario);
        departamento = findViewById(R.id.editTextDepartamento);

        botonRegistro = findViewById(R.id.buttonRegistrarOperario);
        botonRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRegistrarOperario:
//                Toast.makeText(getApplicationContext(), "bonton registro", Toast.LENGTH_SHORT).show();
                registrarOperario();
                break;

        }
    }

    private void registrarOperario() {
        try {
            //crear conexion con la base de datos
            conexion = new ConexioSQLiteHelper(this, "base_dato", null, 1);
            Log.i("info", "base de datos creada");

            //abrir base de datos para escritura
            baseDatos = conexion.getWritableDatabase();
            Log.i("info", "base de datos abierta para escritura");

            //insertar  Operario en la base de datos
            ContentValues valores = new ContentValues();
            valores.put(Utilidades.CAMPO_NOMBREOPERARIO, nombreOperario.getText().toString());
            valores.put(Utilidades.CAMPO_DEPARTAMENTO, departamento.getText().toString());

            baseDatos.insert(Utilidades.TABLA_OPERARIOS, Utilidades.CAMPO_ID_OPERARIO, valores);
            baseDatos.close();
            Toast.makeText(getApplicationContext(), "Registro Insertado en la Base de Datos", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Fallo al registrar en al Base de Datos", Toast.LENGTH_SHORT).show();
        }
        limpiar();
    }

    private void limpiar() {
        nombreOperario.setText("");
        departamento.setText("");
    }
}