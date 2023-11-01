package com.example.tarea2sodoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton1 = findViewById(R.id.button1);
        boton1.setOnClickListener(this);
        boton2 = findViewById(R.id.button2);
        boton2.setOnClickListener(this);
        boton3 = findViewById(R.id.button3);
        boton3.setOnClickListener(this);
        boton4 = findViewById(R.id.button4);
        boton4.setOnClickListener(this);
        boton5 = findViewById(R.id.button5);
        boton5.setOnClickListener(this);
        boton6 = findViewById(R.id.button6);
        boton6.setOnClickListener(this);
        boton7 = findViewById(R.id.button7);
        boton7.setOnClickListener(this);
        boton8 = findViewById(R.id.button8);
        boton8.setOnClickListener(this);
        boton9 = findViewById(R.id.button9);
        boton9.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                break;
        }

    }
}