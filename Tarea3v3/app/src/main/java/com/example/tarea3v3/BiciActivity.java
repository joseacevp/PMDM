package com.example.tarea3v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class BiciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bici);
    }
    // PASO 2
    // Este método captura la selección del usuario
    public void terminar(BikesContent.Bike product){
        //System.out.println("TERMINAR: " + product.toString());
        Intent i = new Intent();
        i.putExtra("PRODUCTO", product.getEmail());

        // Los resultados se devuelven a través de un Intent invocando al método setResult()
        setResult(RESULT_OK,i);

        // Se finaliza la actividad invocando al método finish()
        finish();
    }
}