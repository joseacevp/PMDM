package com.example.juegomultiplicar;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.Random;

public class EntrenarFragment extends Fragment implements View.OnClickListener {

    View view;
    EstadisticasFragment estadisticasFragment = new EstadisticasFragment();
    int i = 1;
    private String tabla, dificultad, heroe, fecha;

    private int indiceActualImagen = 0;
    private int indiceActualBarra = 1;
    TextView respuesta, pregunta, respuestaUsuario;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_entrenar, container, false);

        //carga los datos de la configuración almacenada y los muestra en logcat
        cargarPreferencias();
        Log.i("Tabla", tabla);
        Log.i("Dificulatad", dificultad);
        Log.i("Heroe", heroe);
        Log.i("Fecha", fecha);

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
        respuestaUsuario = view.findViewById(R.id.area_respuesta_usuario);
        pregunta = view.findViewById(R.id.area_pregunta);

        iniciarTablaDificil(dificultad, tabla);
        return view;

    }

    private void iniciarTablaDificil(String dificultad, String tabla) {

        switch (dificultad) {
            case "Facil":
                int primer = Integer.parseInt(tabla);
                    // Muestra la pregunta en el formato "número X número"
                    pregunta.setText(primer + " x " + i);
                    // Establece la respuesta esperada para la multiplicación de los dos números
                    int respuestaEsperada = primer * i;
                    // Guarda la respuesta esperada para usarla en el método chekearRespuesta
                    respuesta.setTag(respuestaEsperada);
                    // Limpia el área de respuesta del usuario
                    respuestaUsuario.setText("");
             i++;
                break;
            case "Normal":
                break;
            case "Dificil":
                break;
        }

        Random random = new Random();
        // Genera dos números aleatorios para la pregunta
        int numero1 = random.nextInt(10) + 1;
        int numero2 = random.nextInt(10) + 1;

        // Muestra la pregunta en el formato "número X número"
//        pregunta.setText(numero1 + " x " + numero2);

        // Establece la respuesta esperada para la multiplicación de los dos números
//        int respuestaEsperada = numero1 * numero2;
//
//        // Guarda la respuesta esperada para usarla en el método chekearRespuesta
//        respuesta.setTag(respuestaEsperada);
//
//        // Limpia el área de respuesta del usuario
//        respuestaUsuario.setText("");

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

                if (indiceActualBarra < 10) {
                    chekearRespuesta(1);
                    iniciarTablaDificil(dificultad, tabla);

                } else {
                    //la tabla enpieza de 1 nuevamente
                    i=1;
                    //llamamos al fragmento estadisticas
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    //relaciona el fragment recibido "f" con el contenedor de fragment 'frame_container'
                    transaction.replace(R.id.fragment_container, estadisticasFragment);
                    transaction.commit();
                }
            case R.id.boton_borrar:
                // Aquí puedes implementar la lógica para borrar un dígito de la respuestaUsuario.
                borrarNumeroRespuesta();
                break;
        }
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
//                respuesta.setText("¡Respuesta Correcta!");
                mostrarImagen(heroe);
            } else {
                // La respuesta es incorrecta

//                respuesta.setText("Respuesta Incorrecta. La respuesta correcta es " + respuestaEsperada);
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

    }
}