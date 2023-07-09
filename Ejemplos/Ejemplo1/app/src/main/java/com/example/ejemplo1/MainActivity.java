package com.example.ejemplo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView etiNombre ;
    EditText areNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etiNombre = findViewById(R.id.etiNombre);
        areNombre = findViewById(R.id.areaNombre);

    }

    public void botonRegistrar(View view) {
        switch (view.getId()){
            case R.id.botonIngresa:
                String nombre = areNombre.getText().toString();
                etiNombre.setText("El nombre es: "+nombre);
                break;
        }
    }
}