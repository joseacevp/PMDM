package com.example.tempoentrenador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean iniciado = false;
    private int tiempoPre;
    private int tiempoTrab;
    private int tiempoDesc;
    private int numCicl;
    private boolean ciclo;
    private TextView tvTotal;
    private TextView tvTiempoPreparacion;
    private TextView tvTiempoTrabajo;
    private TextView tvTiempoDescanso;
    private TextView tvTiempoCiclos;
    private TextView tvTiempoTotal;
    private Button INICIO;
    private TextView twEstado;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        int theme = sharedPreferences.getInt("THEME", 1);
        switch (theme) {
            case 1:
                setTheme(R.style.Theme_TempoEntrenador);
                break;
            case 2:
                setTheme(R.style.Theme_TempoEntrenador2);
                break;
            case 3:
                setTheme(R.style.Theme_TempoEntrenador3);
                break;

        }


        setContentView(R.layout.activity_main);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvTiempoPreparacion = (TextView) findViewById(R.id.tvTiempoPrepara);
        tvTiempoTrabajo = (TextView) findViewById(R.id.tvTiempoTrabajo);
        tvTiempoDescanso = (TextView) findViewById(R.id.tvTiempoDescaso);
        tvTiempoCiclos = (TextView) findViewById(R.id.tvTiempoCiclos);
        tvTiempoTotal = (TextView) findViewById(R.id.tvTiempoTotal);
        INICIO = (Button) findViewById(R.id.INICIO);
        twEstado = (TextView) findViewById(R.id.twEstado);

    }

    //metodo para hacer aparecer el menu de colores tresPuntos
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menutrepuntos, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();

        switch (item.getItemId()) {
            case R.id.ItemTema1:
                System.out.println("Item1");
                sharedPreferences.edit().putInt("THEME", 1).apply();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.ItemTema2:
                System.out.println("Item2");
                sharedPreferences.edit().putInt("THEME", 2).apply();
                Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.ItemTema3:
                System.out.println("Item3");
                sharedPreferences.edit().putInt("THEME", 3).apply();
                Intent intent3 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent3);
                break;
            case R.id.ItemInformacion:

                break;
            case R.id.ItemReset:

                break;
        }


        return super.onOptionsItemSelected(item);
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

    public void start(View view) {
        /*metodo para iniciar el cuenteo
         * se inicia en caso que no se haya iniciado antes*/
        if (!iniciado) {
            iniciado = true;//indica a la variable que a sido iniciado el proceso
            INICIO.setText("PARAR");//cambia el estado del boton de INICIO a PARAR
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
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    twEstado.setText("PREPARACIÓN");//cambia el titulo
                                    /*indica el valor del tiempo de Preparacion en el cuenteo
                                     * en el hilo principal*/
                                    tvTiempoTotal.setText(String.valueOf(tiempoPre));
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
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    twEstado.setText("TRABAJANDO");
                                    tvTiempoTotal.setText(String.valueOf(tiempoTrab));
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
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    twEstado.setText("DESCANSO");
                                    tvTiempoTotal.setText(String.valueOf(tiempoDesc));
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
                    /*hilo secundario que cambia el valor del boton INICIO al terminar los
                     * ciclos indicados y el valor inicio para que se pueda reiniciar el proceso*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iniciado = false;
                            INICIO.setText("INICIAR");//cambia el texto del boton
                        }
                    });
                }
            }).start();/*fin del hilo secundario*/

        } else {
            iniciado = false;
            INICIO.setText("INICIAR");//cambia el texto del boton
        }
    }
}