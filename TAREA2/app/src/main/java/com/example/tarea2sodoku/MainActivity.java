package com.example.tarea2sodoku;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9;
    GameBoard board;
    int dificultad;

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

        board.resetBoard(cargarDificultad());
    }

    //metodo que indica la acción a seguir segun el numero pulsado.
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                board.setInputNumber(1);
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
                mensageEmerg();
                break;
            case R.id.menu_dificultad:
                dificultadActivity();
                break;
            case R.id.menu_nueva_partida:
                nuevaPartida(cargarDificultad());
                break;
        }
        return true;
    }

    //crea el nivel de dificultad recuperado del archivo SharedPreferences
    private int cargarDificultad() {
        SharedPreferences preferencias = getSharedPreferences
                ("nivelDificultad", Context.MODE_PRIVATE);
        int numeroCasillasVacias = preferencias.getInt("dificultad", 20);//en caso de no optener
        // dato por defecto 20 dificultad normal

        Log.i("info", "dificultad recuperada: " + dificultad);
        return numeroCasillasVacias;
    }

    //lanza al actividad para seleccionar el nivel de dificultad
    private void dificultadActivity() {
        Intent intencionDificultad = new Intent(this, MenuDificulad.class);
        startActivity(intencionDificultad);
    }

    //metodo para iniciar una nueva partida usara por defecto la ultima dificultad indicada.
    private void nuevaPartida(int dificultad) {
        board.resetBoard(dificultad);//numero de casillas vacia determina la dificultad
    }

    //cierra la aplicación si damos a la tecla atras.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return super.onKeyDown(keyCode, event);
    }

    public void mensageEmerg() {

//      mensage emergente
        AlertDialog.Builder constructor = new AlertDialog.Builder(MainActivity.this);
        constructor.setMessage(R.string.info).setTitle(R.string.titulo_como);
        AlertDialog dialogo = constructor.create();
        dialogo.show();
    }


}