package com.example.tempoentrenador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean iniciado = false;
    private int contador = 5;
    private int tiempoPre;
    private int tiempoTrab;
    private int tiempoDesc;
    private int numCicl;
    private int total;
    private TextView tvTotal;
    private TextView tvTiempoPreparacion;
    private TextView tvTiempoTrabajo;
    private TextView tvTiempoDescanso;
    private TextView tvTiempoCiclos;
    private TextView tvTiempoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvTiempoPreparacion = (TextView) findViewById(R.id.tvTiempoPrepara);
        tvTiempoTrabajo = (TextView) findViewById(R.id.tvTiempoTrabajo);
        tvTiempoDescanso = (TextView) findViewById(R.id.tvTiempoDescaso);
        tvTiempoCiclos = (TextView) findViewById(R.id.tvTiempoCiclos);
        tvTiempoTotal = (TextView) findViewById(R.id.tvTiempoTotal);

    }

    //metodo para reducir el tiempo de preparacion
    public void preparacionMenos(View view) {
        if (tiempoPre >= 1) {
            tvTiempoPreparacion.setText(String.valueOf(tiempoPre = tiempoPre - 1));//reduce el tiempo
            tvTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));//calcula el total del tiempo a emplear
        }
    }

    //metodo para aumentar el tiempo de preparacion
    public void preparacionMas(View view) {
        tvTiempoPreparacion.setText(String.valueOf(tiempoPre = tiempoPre + 1));//aumenta el tiempo
        tvTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
    }

    //metodo para reducir el tiempo de trabajo
    public void trabajoMenos(View view) {
        if (tiempoTrab >= 1) {
            tvTiempoTrabajo.setText(String.valueOf(tiempoTrab = tiempoTrab - 1));
            tvTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
        }
    }

    //metodo para aumentar el tiempo de trabajo
    public void trabajoMas(View view) {
        tvTiempoTrabajo.setText(String.valueOf(tiempoTrab = tiempoTrab + 1));
        tvTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
    }

    //metodo para reducir el tiempo de descanso
    public void decansoMenos(View view) {
        if (tiempoDesc >= 1) {
            tvTiempoDescanso.setText(String.valueOf(tiempoDesc = tiempoDesc - 1));
            tvTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
        }
    }

    //metodo para aumentar el tiempo de descanso
    public void descansoMas(View view) {
        tvTiempoDescanso.setText(String.valueOf(tiempoDesc = tiempoDesc + 1));
        tvTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
    }

    //metodo para reducir el numero de ciclos
    public void cicloMenos(View view) {
        if (numCicl >= 1) {
            tvTiempoCiclos.setText(String.valueOf(numCicl = numCicl - 1));
        }
    }

    //metodo para aumentar el numero de ciclos
    public void cicloMas(View view) {
        tvTiempoCiclos.setText(String.valueOf(numCicl = numCicl + 1));
    }



    //metodo para iniciar el contador
    public void inicio(View view) {
        //tvTiempoTotal.setText(00);
        if (!iniciado) {//solo arrancar el hilo secundario si  no se ha iniciado ya
            iniciado = true;
            new Thread(new Runnable() {//creamos un hilo secundario para evitar el bloqueo de la
                // aplicación por detener el hilo principal
                @Override
                public void run() {
                    while (iniciado) {//si apretamos el boton cuando esta en proceso
                        if (contador >= 1) {
                            contador--;
                            runOnUiThread(new Runnable() {//ejecutamos los comandos en el hilo
                                // principal y que no podemos modificar los
                                // elementos de pantalla desde el hilo secundario
                                @Override
                                public void run() {
                                    tvTiempoTotal.setText(String.valueOf(contador));//muestra el contador
                                    // por pantalla
                                }
                            });
                        }
                        try {
                            Thread.sleep(1000);//para controlar la velocidad del contador
                        } catch (InterruptedException e) {//control de excepciones
                            e.printStackTrace();
                        }
                    }
                }
            }).start();//iniciamos el hilo secundario
        }
    }


}