package com.example.practicaexamenfebrero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.practicaexamenfebrero.databinding.ActivityMainBinding;
import com.example.practicaexamenfebrero.databinding.ActivityVentanaDatosBinding;

import java.util.ArrayList;

public class VentanaDatos extends AppCompatActivity {
    public static final int DATOS_RESULTADO = 1;
    private int anos;
    //9. construcción de la lista que mandaremos como parametro
    private ArrayList<Integer> listaDatos;
    ActivityVentanaDatosBinding binding2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_datos);
        binding2 = ActivityVentanaDatosBinding.inflate(getLayoutInflater());
        View view = binding2.getRoot();
        setContentView(view);

        Bundle datoRecivido = this.getIntent().getExtras();
        if (datoRecivido != null) {
            ArrayList<Integer> datosOpt = datoRecivido.getIntegerArrayList("dato");
            anos = datosOpt.get(0);

            listaDatos = datosOpt;
        }

        binding2.recycleId.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

//        listaDatos = new ArrayList<String>();
//        //14. llenamos la lista de datos
//        listaDatos.add("primer dato");
//        listaDatos.add("segundo dato");
//        listaDatos.add("tercer dato");
//        listaDatos.add("cuarto dato");
//        listaDatos.add("quinto dato");
//        listaDatos.add("sexto dato");
//        listaDatos.add("octavo dato");
//        listaDatos.add("noveno dato");
//        listaDatos.add("decimo dato");

        //15. enviamos la lista con los datos al adaptador
        AdapterDatos adapter = new AdapterDatos(listaDatos);
        //16. indicamos al recycleView que adaptador usar
        binding2.recycleId.setAdapter(adapter);


        //boton volver
        binding2.botonVentDatosVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularEstado();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle miDato = new Bundle();
                miDato.putString("resultado", calcularEstado());
                intent.putExtras(miDato);
                startActivityForResult(intent, DATOS_RESULTADO);

            }
        });
    }

    private String calcularEstado() {
        String estado = "";
//        Edad Mensaje
//                <=12 Eres un niño
//                <= 18 Eres un adolescente
//                <= 50 Estás en la flor de la vida
//                <= 60 Cincuentón... ¡cuidadin !
//                > 60 Disfruta cada día, cada hora y cada segundo

        if (anos <= 12) {
            Toast.makeText(this, "menos de doce", Toast.LENGTH_SHORT).show();
            estado = "Eres un niño";
        } else if (anos <= 18) {
            Toast.makeText(this, "menos de dieciocho", Toast.LENGTH_SHORT).show();
            estado = "Eres un adolescente";
        } else if (anos <= 50) {
            Toast.makeText(this, "menos de cincuenta", Toast.LENGTH_SHORT).show();
            estado = "Estás en la flor de la vida";
        } else if (anos <= 60) {
            Toast.makeText(this, "menos de sesenta", Toast.LENGTH_SHORT).show();
            estado = "Cincuentón... ¡cuidadin !";
        } else if (anos > 60) {
            Toast.makeText(this, "mas de sesenta", Toast.LENGTH_SHORT).show();
            estado = "Disfruta cada día, cada hora y cada segundo";
        }
        return estado;
    }
}