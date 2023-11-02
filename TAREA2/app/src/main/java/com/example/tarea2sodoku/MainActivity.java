package com.example.tarea2sodoku;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9;
    GameBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = findViewById(R.id.gameBoard);

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

    //inicia el menu en la pantalla
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    //maneja las acciones que adoptemos en el menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_pregunta:
        Toast.makeText(getApplicationContext(), "¿CÓMO JUGAR?\n" +
                "Cada fila, columna y cadrado (9 espacios cada uno)" +
                "debe completarse conlos números del 1 al 9," +
                "sin repetir ningún número dentro de la fila," +
                "columna o cuadrado.", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_dificultad:

                break;

            case R.id.menu_nueva_partida:

                break;

        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                board.setInputNumber(1);
//                board.resetBoard(0);
                break;
            case R.id.button2:
                board.setInputNumber(2);
                break;
            case R.id.button3:
                board.setInputNumber(3);
                break;
            case R.id.button4:
                board.setInputNumber(4);
                break;
            case R.id.button5:
                board.setInputNumber(5);
                break;
            case R.id.button6:
                board.setInputNumber(6);
                break;
            case R.id.button7:
                board.setInputNumber(7);
                break;
            case R.id.button8:
                board.setInputNumber(8);
                break;
            case R.id.button9:
                board.setInputNumber(9);
                break;
        }


    }

}