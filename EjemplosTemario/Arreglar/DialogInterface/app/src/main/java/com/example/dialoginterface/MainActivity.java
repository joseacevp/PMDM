package com.example.dialoginterface;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;
    Button confirmar;
    CheckBox facil,norma,dificil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView = findViewById(R.id.textView2);
        // Inflar el diseño personalizado


        facil= findViewById(R.id.radioButton);
        norma = findViewById(R.id.radioButton2);
        dificil = findViewById(R.id.radioButton3);

    }

    public void mensageEmerg(View view) {

//      mensage emergente
        AlertDialog.Builder constructor = new AlertDialog.Builder(MainActivity.this);
        constructor.setMessage(R.string.mensage).setTitle(R.string.titulo_mensaje);
        AlertDialog dialogo = constructor.create();
        dialogo.show();
    }

    public void mensageSelec(View view) {
        AlertDialog.Builder constructor = new AlertDialog.Builder(MainActivity.this);
        constructor.setMessage(R.string.mensage_2).setTitle(R.string.titulo_mensaje_2)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Pulso Aceptar", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Pulso Cancelar", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialogo = constructor.create();
        dialogo.show();
    }

    public void mensageLista(View view) {
        confirmar = findViewById(R.id.buttonConf);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.opciones_difi, null);
        AlertDialog.Builder construptor = new AlertDialog.Builder(this);
        construptor.setView(dialogView).setTitle(R.string.titulo_mensaje_3);
        AlertDialog dialog = construptor.create();
        dialog.show();

    }


}