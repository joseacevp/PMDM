package com.example.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button botonRegistrar, botonConsultar, botonListaSpinner, botonListaListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonRegistrar = findViewById(R.id.botonRegistrar);
        botonRegistrar.setOnClickListener(this);

        botonConsultar = findViewById(R.id.botonConsultar);
        botonConsultar.setOnClickListener(this);

        botonListaListView = findViewById(R.id.botonListaLisstView);
        botonListaListView.setOnClickListener(this);

        botonListaSpinner = findViewById(R.id.botonListaSpinner);
        botonListaSpinner.setOnClickListener(this);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,
                "db_usuarios", null, 1);

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.botonRegistrar:
                    Intent evento = new Intent(getApplicationContext(),FormularioRegistro.class);
                    startActivity(evento);
                    break;
                case R.id.botonConsultar:
                    Intent eventoBuscar = new Intent(getApplicationContext(),FormularioConsulta.class);
                    startActivity(eventoBuscar);
                    break;
                case R.id.botonListaSpinner:
                    Intent eventoListSp = new Intent(getApplicationContext(),ConsultaSpinner.class);
                    startActivity(eventoListSp);

                    break;
                case R.id.botonListaLisstView:
                    Intent eventoListaView = new Intent(getApplicationContext(),ConsultaListView.class);
                    startActivity(eventoListaView);
                    break;

            }
    }
}