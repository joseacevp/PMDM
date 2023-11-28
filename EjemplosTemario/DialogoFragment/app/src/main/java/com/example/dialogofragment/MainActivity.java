package com.example.dialogofragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogoSexo.RespuestaDialogoSexo{

    //Salida por pantalla del mensaje con la respuesta recibida del DialogFragment
    @Override
    public void onRespuesta(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    //metodo onClick lanza el dialog y recibe la respuesta de este asignando un tag "Mi diálogo"
    public void onClick(View view) {
        DialogoSexo ds = new DialogoSexo();
        ds.show(getFragmentManager(),"Mi diálogo");
    }


//punto 2.5 de la teoria del tema 2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}