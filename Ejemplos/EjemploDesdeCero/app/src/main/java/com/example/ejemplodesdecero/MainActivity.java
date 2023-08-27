package com.example.ejemplodesdecero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText campoEditable ;
    TextView campoEditado ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campoEditable = findViewById(R.id.campoEditable);
        campoEditado = findViewById(R.id.campoEditado);

    }

    public void onClickGrabar(View view) {

        campoEditado.setText("Bienvenido don: "+campoEditable.getText().toString());
        Toast.makeText(this,"Realizado con exito",Toast.LENGTH_SHORT).show();
    }
}