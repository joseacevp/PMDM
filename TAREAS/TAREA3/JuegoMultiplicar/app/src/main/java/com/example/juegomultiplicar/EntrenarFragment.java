package com.example.juegomultiplicar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EntrenarFragment extends Fragment implements View.OnClickListener {

    View view;
    Bundle datos;
    EstadisticasFragment estadisticasFragment = new EstadisticasFragment();
    private int i = 1;
    private int e = 10;
    Random random = new Random();
    private int primer, respuestaEsperada;
    private String tabla, dificultad, heroe, fecha, aleatorio;

    private int indiceActualImagen = 0;
    private int indiceActualBarra = 1;
    TextView respuesta, respuestaIncorecta, pregunta, respuestaUsuario;

    int[] imagen;
    private int[] imagen_batman = {R.drawable.batmanuno
            , R.drawable.batmandos, R.drawable.batmantres
            , R.drawable.batmancuatro, R.drawable.batmancinco
            , R.drawable.batmanseis, R.drawable.batmansiete
            , R.drawable.batmanocho, R.drawable.batmannueve
            , R.drawable.batman};
    private int[] imagen_hulk = {R.drawable.hulduno
            , R.drawable.hulddos, R.drawable.huldtres
            , R.drawable.huldcuatro, R.drawable.huldcinco, R.drawable.huldseis
            , R.drawable.huldsiete, R.drawable.huldocho
            , R.drawable.huldnueve, R.drawable.huld};
    private int[] imagen_iron = {R.drawable.ironuno
            , R.drawable.irondos, R.drawable.irontres
            , R.drawable.ironcuatro, R.drawable.ironcinco, R.drawable.ironseis
            , R.drawable.ironsiete, R.drawable.ironocho
            , R.drawable.ironnueve, R.drawable.iron};
    private int[] imagen_capi = {R.drawable.capiuno
            , R.drawable.capidos, R.drawable.capitres
            , R.drawable.capicuatro, R.drawable.capicinco, R.drawable.capiseis
            , R.drawable.capisiete, R.drawable.capiocho
            , R.drawable.capinueve, R.drawable.capi};

    public EntrenarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //resetea la pantalla pare iniciar desde cero
    @Override
    public void onPause() {
        super.onPause();
        indiceActualBarra = 1;
        indiceActualImagen = 0;
        i = 1;
        e = 10;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_entrenar, container, false);
        datos = new Bundle();

        //carga los datos de la configuración almacenada y los muestra en logcat
        cargarPreferencias();
        Log.i("Tabla", tabla);
        Log.i("Dificulatad", dificultad);
        Log.i("Heroe", heroe);
        Log.i("Fecha", fecha);
        Log.i("aleatoria", aleatorio);


        //botones de la botonera para indicar los datos
        Button boton_cero = view.findViewById(R.id.boton_diez);
        Button boton_uno = view.findViewById(R.id.boton_uno);
        Button boton_dos = view.findViewById(R.id.boton_dos);
        Button boton_tres = view.findViewById(R.id.boton_tres);
        Button boton_cuatro = view.findViewById(R.id.boton_cuatro);
        Button boton_cinco = view.findViewById(R.id.boton_cinco);
        Button boton_seis = view.findViewById(R.id.boton_seis);
        Button boton_siete = view.findViewById(R.id.boton_siete);
        Button boton_ocho = view.findViewById(R.id.boton_ocho);
        Button boton_nueve = view.findViewById(R.id.boton_nueve);
        Button boton_ok = view.findViewById(R.id.boton_ok);
        Button boton_borrar = view.findViewById(R.id.boton_borrar);
        boton_cero.setOnClickListener(this);
        boton_uno.setOnClickListener(this);
        boton_dos.setOnClickListener(this);
        boton_tres.setOnClickListener(this);
        boton_cuatro.setOnClickListener(this);
        boton_cinco.setOnClickListener(this);
        boton_seis.setOnClickListener(this);
        boton_siete.setOnClickListener(this);
        boton_ocho.setOnClickListener(this);
        boton_nueve.setOnClickListener(this);
        boton_ok.setOnClickListener(this);
        boton_borrar.setOnClickListener(this);


        respuesta = view.findViewById(R.id.area_respuesta_correcta);
        respuestaIncorecta = view.findViewById(R.id.area_respuesta_correcta2);
        respuestaUsuario = view.findViewById(R.id.area_respuesta_usuario);
        pregunta = view.findViewById(R.id.area_pregunta);

        iniciarTablaDificil(dificultad, tabla);


        return view;

    }


    private void iniciarTablaDificil(String dificultad, String tabla) {
        if (tabla.equals("aleatorio")) {
            primer = Integer.parseInt(aleatorio);
        } else {
            try {
                primer = Integer.parseInt(tabla);
            } catch (Exception e) {
                Toast.makeText(getContext(), "No selecciono numero de tabla por defecto tabla del 1", Toast.LENGTH_SHORT).show();
                primer = 1;
            }
        }
        switch (dificultad) {
            case "Facil"://tabla de multiplicar en orden ascendente
//                primer = numeroTablaAleter(tabla);
                // Muestra la pregunta en el formato "número X número"
                pregunta.setText(primer + " x " + i + " = ");
                // Establece la respuesta esperada para la multiplicación de los dos números
                respuestaEsperada = primer * i;
                // Guarda la respuesta esperada para usarla en el método chekearRespuesta
                respuesta.setTag(respuestaEsperada);
                // Limpia el área de respuesta del usuario
                respuestaUsuario.setText("");
                i++;

                break;
            case "Normal"://tabla de multiplicar en orden descendente
//                primer = Integer.parseInt(tabla);
                // Muestra la pregunta en el formato "número X número"
                pregunta.setText(primer + " x " + e + " = ");
                // Establece la respuesta esperada para la multiplicación de los dos números
                respuestaEsperada = primer * e;
                // Guarda la respuesta esperada para usarla en el método chekearRespuesta
                respuesta.setTag(respuestaEsperada);
                // Limpia el área de respuesta del usuario
                respuestaUsuario.setText("");
                e--;
                break;
            case "Dificil"://tabla de multiplicar en orden aleatorio
                // Genera dos números aleatorios para la pregunta
                int numero1 = random.nextInt(10) + 1;
//                primer = Integer.parseInt(tabla);
                // Muestra la pregunta en el formato "número X número"
                pregunta.setText(primer + " x " + numero1 + " = ");
                // Establece la respuesta esperada para la multiplicación de los dos números
                respuestaEsperada = primer * numero1;
                // Guarda la respuesta esperada para usarla en el método chekearRespuesta
                respuesta.setTag(respuestaEsperada);
                // Limpia el área de respuesta del usuario
                respuestaUsuario.setText("");
                break;
        }
    }


    private void mostrarBarra() {
        ProgressBar bar = view.findViewById(R.id.progressBar);
        indiceActualBarra++;
        bar.setProgress(indiceActualBarra);
    }

    private void mostrarImagen(String heroe) {
        switch (heroe) {
            case "Batman":
                imagen = imagen_batman;
                break;
            case "Hulk":
                imagen = imagen_hulk;
                break;
            case "Iron Man":
                imagen = imagen_iron;
                break;
            case "Capitan America":
                imagen = imagen_capi;
                break;
        }
        ImageView imageView = view.findViewById(R.id.imageViewHeroe);
        if (indiceActualImagen < imagen.length - 1) {
            indiceActualImagen++;
        }
        imageView.setImageResource(imagen[indiceActualImagen]);
    }

    //comportamiento de los botones de la botonera
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.boton_diez:
            case R.id.boton_uno:
            case R.id.boton_dos:
            case R.id.boton_tres:
            case R.id.boton_cuatro:
            case R.id.boton_cinco:
            case R.id.boton_seis:
            case R.id.boton_siete:
            case R.id.boton_ocho:
            case R.id.boton_nueve:
                addNumeroRespuesta(view);
                break;
            case R.id.boton_ok:
                // Aquí puedes procesar la respuesta actual (respuestaUsuario.getText().toString())
                // y verificar si es correcta.

                if (!respuestaUsuario.getText().toString().equals("")) {//si la respuesta del usuario no esta vacia
                    if (indiceActualBarra < 10) {//si no a respondido 10 veces
                        chekearRespuesta(1);
                        iniciarTablaDificil(dificultad, tabla);

                    } else {
                        //la tabla enpieza de 1 nuevamente
                        i = 1;
                        llamarFragmentoEsta();

                    }
                }

            case R.id.boton_borrar:
                // Aquí puedes implementar la lógica para borrar un dígito de la respuestaUsuario.
                borrarNumeroRespuesta();
                break;
        }
    }

    private void llamarFragmentoEsta() {
        //comunicacion entre fragmentos
        datos.putString("numeroTabla",tabla);
        datos.putString("fecha",fecha);
        datos.putString("heroe",heroe);

        estadisticasFragment.setArguments(datos);
        //llamamos al fragmento estadisticas
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //relaciona el fragment recibido "f" con el contenedor de fragment 'frame_container'
        transaction.replace(R.id.fragment_container, estadisticasFragment);
        transaction.commit();
    }

    private void chekearRespuesta(int numeroTabla) {
        // Obtén la respuesta del usuario como una cadena
        String respuestaUsuarioStr = respuestaUsuario.getText().toString();
        // Obtiene la respuesta esperada del Tag de la vista respuesta
        int respuestaEsperada = (int) respuesta.getTag();

        // Verifica si la respuesta del usuario no está vacía
        if (!respuestaUsuarioStr.isEmpty()) {
            // Convierte la respuesta del usuario a un número entero
            int respuestaUsuarioInt = Integer.parseInt(respuestaUsuarioStr);
            // Compara la respuesta del usuario con la respuesta esperada
            if (respuestaUsuarioInt == respuestaEsperada) {
                // La respuesta es correcta
                respuesta.setText("");
                respuestaIncorecta.setText("");
                mostrarImagen(heroe);

            } else {
                // La respuesta es incorrecta
                respuestaIncorecta.setTextColor(Color.RED);
                respuestaIncorecta.setText(pregunta.getText() + respuestaUsuario.getText().toString());
                respuesta.setTextColor(Color.GREEN);
                respuesta.setText(pregunta.getText().toString() + respuestaEsperada);
            }
            mostrarBarra();
        }
    }

    private void addNumeroRespuesta(View view) {
        Button button = (Button) view;
        String respuestaString = respuestaUsuario.getText().toString();
        // Limita la longitud de la respuesta a dos dígitos
        if (respuestaString.length() < 2) {
            respuestaUsuario.setText(respuestaString + button.getText().toString());
        }
    }

    private void borrarNumeroRespuesta() {
        String respuestaString = respuestaUsuario.getText().toString();
        // Verifica si hay al menos un dígito antes de intentar borrar
        if (!respuestaString.isEmpty()) {
            respuestaUsuario.setText(respuestaString.substring(0, respuestaString.length() - 1));
        }
    }

    //metodo que carga los datos previamente almacenados en un XML de preferencias
    private void cargarPreferencias() {
        SharedPreferences preferencias = getActivity().getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        tabla = preferencias.getString("tabla", "Sin información");
        dificultad = preferencias.getString("dificultad", "Sin información");
        heroe = preferencias.getString("heroe", "Sin información");
        fecha = preferencias.getString("fecha", "Sin información");
        aleatorio = preferencias.getString("aleatorio", "Sin información");

    }
}