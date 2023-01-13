package com.example.entrenadortiempo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TiemposFragment extends Fragment {
    int tiempoPre;
    int tiempoTrab;
    int tiempoDesc;
    int numCicl;
    boolean iniciado;
    boolean ciclo;

    public TiemposFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View componenteFragmentTiempo = inflater.inflate(R.layout.fragment_tiempos, container, false);

        TextView textoEstado = (TextView) componenteFragmentTiempo.findViewById(R.id.textoEstado);
        TextView tiempoSeleccionado = (TextView) componenteFragmentTiempo.findViewById(R.id.textoTiempoSeleccionado);
        TextView tvTiempoPreparacion = (TextView) componenteFragmentTiempo.findViewById(R.id.textPreparacionTiempo);
        TextView tvTiempoTrabajo = (TextView) componenteFragmentTiempo.findViewById(R.id.textoTrabajoTiempo);
        TextView tvTiempoDescanso = (TextView) componenteFragmentTiempo.findViewById(R.id.textDescansoTiempo);
        TextView tvTiempoCiclos = (TextView) componenteFragmentTiempo.findViewById(R.id.textNumeroCiclos);
        TextView tvTiempoTotal = (TextView) componenteFragmentTiempo.findViewById(R.id.textTiempoTotal);
        Button INICIO = (Button) componenteFragmentTiempo.findViewById(R.id.buttonIniciarParar);
        //TextView  twEstado = (TextView) componenteFragmentTiempo.findViewById(R.id.textoEstado);


        SharedPreferences sharedPreferences;


        /**
         * Metodo para cambiar los tiempos mediante botones de más o menos
         * @param view cambia el numero en el texto de seleccion de tiempo
         */
        //metodo para reducir el tiempo de preparacion
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonPreparacionMenos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiempoPre >= 1) {
                    tvTiempoPreparacion.setText(String.valueOf(tiempoPre = tiempoPre - 1));//reduce el tiempo
                    tvTiempoTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));//calcula el total del tiempo a emplear
                }
            }
        });
        //metodo para aumentar el tiempo de preparacion
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonPreparacionMas)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTiempoPreparacion.setText(String.valueOf(tiempoPre = tiempoPre + 1));//aumenta el tiempo
                tvTiempoTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
            }
        });
        //metodo para reducir el tiempo de trabajo
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonTrabajoMenos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiempoTrab >= 1) {
                    tvTiempoTrabajo.setText(String.valueOf(tiempoTrab = tiempoTrab - 1));
                    tvTiempoTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
                }
            }
        });
        //metodo para aumentar el tiempo de trabajo
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonTrabajoMas)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTiempoTrabajo.setText(String.valueOf(tiempoTrab = tiempoTrab + 1));
                tvTiempoTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
            }
        });
        //metodo para reducir el tiempo de descanso
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonDescansoMenos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiempoDesc >= 1) {
                    tvTiempoDescanso.setText(String.valueOf(tiempoDesc = tiempoDesc - 1));
                    tvTiempoTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
                }
            }
        });
        //metodo para aumentar el tiempo de descanso
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonDescansoMas)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTiempoDescanso.setText(String.valueOf(tiempoDesc = tiempoDesc + 1));
                tvTiempoTotal.setText(String.valueOf(tiempoPre + tiempoDesc + tiempoTrab));
            }
        });
        //metodo para reducir el numero de ciclos
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonCiclosMenos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numCicl >= 1) {
                    tvTiempoCiclos.setText(String.valueOf(numCicl = numCicl - 1));
                }
            }
        });
        //metodo para aumentar el numero de ciclos
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonCinclosMas)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTiempoCiclos.setText(String.valueOf(numCicl = numCicl + 1));
            }
        });
        /**
         * metodo para iniciar la cuenta atras de la aplicación. Rediciendo el tiempo de cada apartado.
         * @param view
         */
        ((Button) componenteFragmentTiempo.findViewById(R.id.buttonIniciarParar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*metodo para iniciar el cuenteo
                 * se inicia en caso que no se haya iniciado antes*/
                Activity estaAdtividad = getActivity();//recupera la actividad actual
                //usa la interface comunica menu para pasar los datos del fragment al Main
                /*metodo para iniciar el cuenteo
                 * se inicia en caso que no se haya iniciado antes*/
                if (iniciado == false) {
                    iniciado = true;//indica a la variable que a sido iniciado el proceso
                    //INICIO.setText("PARAR");//cambia el estado del boton de INICIO a PARAR
                    /*variables que reciven los valores originales de los tiempos para
                     * indicarselos despues de la cuenta a tras y recuperar los datos para
                     * el siguiente ciclo*/
                    int cuentaPrepa = tiempoPre;
                    int cuentaTrabajo = tiempoTrab;
                    int cuentaDesc = tiempoDesc;
                    int cuentaCiclos = numCicl;
                    /*apertura de un nuevo hilo para evitar que la aplicación se cierre al ocupar mas
                     * tiempo de proceso que lo permitido por el hilo principal*/
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (numCicl != 0) {//repite el ciclo tantas veces como se indican en el valor
                                /*bucle para la cuenta a tras del tiempo Preparación inicia un hilo para
                                 * cambiar el valor del texto de cuenta atras en el hilo principal*/
                                while (tiempoPre >= 0) {
                                    estaAdtividad.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            textoEstado.setText("PREPARACIÓN");//cambia el titulo
                                            /*indica el valor del tiempo de Preparacion en el cuenteo
                                             * en el hilo principal*/
                                            tiempoSeleccionado.setText(String.valueOf(tiempoPre));
                                            tiempoPre--;//resta uno al tiempo de preparacion
                                        }
                                    });
                                    /*relentiza el tiempo de ejecución para que sea un segundo cada cuenta
                                     * hay que controlar las posibles excepciones */
                                    try {
                                        Thread.sleep(1000);//para controlar la velocidad del contador
                                    } catch (InterruptedException e) {//control de excepciones
                                        e.printStackTrace();
                                    }
                                }
                                /*recuperamos el valor originalmente indicado para el siguiente ciclo*/
                                tiempoPre = cuentaPrepa;//recupera el valor original
                                /*hilo secundario para el cuenteo del tiempo Trabajo
                                 * igual que el anterior hilo para el tiempo Preparacion*/
                                while (tiempoTrab >= 0) {
                                    estaAdtividad.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            textoEstado.setText("TRABAJANDO");
                                            tiempoSeleccionado.setText(String.valueOf(tiempoTrab));
                                            tiempoTrab--;
                                        }
                                    });
                                    try {
                                        Thread.sleep(1000);//para controlar la velocidad del contador
                                    } catch (InterruptedException e) {//control de excepciones
                                        e.printStackTrace();
                                    }
                                }
                                tiempoTrab = cuentaTrabajo;//recupera el valor original
                                /*hilo secundario para el cuenteo del tiempo Trabajo
                                 * igual que el anterior hilo para el tiempo Descanso*/
                                while (tiempoDesc >= 0) {
                                    estaAdtividad.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            textoEstado.setText("DESCANSO");
                                            tiempoSeleccionado.setText(String.valueOf(tiempoDesc));
                                            tiempoDesc--;
                                        }
                                    });
                                    try {
                                        Thread.sleep(1000);//para controlar la velocidad del contador
                                    } catch (InterruptedException e) {//control de excepciones
                                        e.printStackTrace();
                                    }
                                }
                                tiempoDesc = cuentaDesc;//recupera el valor original
                                numCicl--;//un ciclo finalizado
                            }
                            numCicl = cuentaCiclos;////recupera el valor original
                        }
                    }).start();/*fin del hilo secundario*/
                } else {
                    iniciado = false;
                    INICIO.setText("INICIAR");//cambia el texto del boton
                }
            }
            /**
             *
             */

        });


        return componenteFragmentTiempo;

    }

}