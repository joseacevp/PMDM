package com.example.basedatosoperariomaterial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button botonRegistroOperario,botonActualizarOperario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referencias
        botonRegistroOperario = findViewById(R.id.buttonIrActivityRegistroOperario);
        botonRegistroOperario.setOnClickListener(this);
        botonActualizarOperario = findViewById(R.id.buttonIrActivityActualizarOperario);
        botonActualizarOperario.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonIrActivityRegistroOperario:
                Intent eventoIrRegistroOpe = new Intent(getApplicationContext(), RegistroOperariosAct.class);
                startActivity(eventoIrRegistroOpe);
                break;
            case R.id.buttonIrActivityActualizarOperario:
                Intent eventoIrActualizarOpe = new Intent(getApplicationContext(), ActualizarOperario.class);
                startActivity(eventoIrActualizarOpe);
                break;
        }

    }
}