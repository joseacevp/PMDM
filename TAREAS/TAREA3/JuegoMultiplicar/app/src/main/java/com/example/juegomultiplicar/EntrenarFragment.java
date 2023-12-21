package com.example.juegomultiplicar;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntrenarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntrenarFragment extends Fragment implements View.OnClickListener {

    View view;
    EstadisticasFragment estadisticasFragment = new EstadisticasFragment();

    Bundle datosRecividos ;

    private int indiceActualImagen = 0;
    private int indiceActualBarra = 0;
    TextView respuesta, pregunta, respuestaUsuario;


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
            , R.drawable.capidos, R.drawable.irontres
            , R.drawable.ironcuatro, R.drawable.ironcinco, R.drawable.ironseis
            , R.drawable.ironsiete, R.drawable.ironocho
            , R.drawable.ironnueve, R.drawable.iron};
    private int[] imagen_capi = {R.drawable.capiuno
            , R.drawable.capidos, R.drawable.capitres
            , R.drawable.capicuatro, R.drawable.capicinco, R.drawable.capiseis
            , R.drawable.capisiete, R.drawable.capiocho
            , R.drawable.capinueve, R.drawable.capi};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EntrenarFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EntrenarFragment newInstance(String param1, String param2) {
        EntrenarFragment fragment = new EntrenarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_entrenar, container, false);

        datosRecividos = getActivity().getIntent().getExtras();
        if (datosRecividos!=null){
            int numeroTabla = datosRecividos.getInt("numero");
            Log.i("Info",Integer.toString(numeroTabla));
            String dificultad = datosRecividos.getString("dificultad");
            Log.i("Info",dificultad);
        }

        Button boton_cero = view.findViewById(R.id.boton_cero);
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

        iniciarTablaDificil();
        return view;

    }

    private void iniciarTablaDificil() {
        Random random = new Random();
        // Genera dos números aleatorios para la pregunta
        int numero1 = random.nextInt(10) + 1;
        int numero2 = random.nextInt(10) + 1;

        // Muestra la pregunta en el formato "número X número"
        pregunta.setText(numero1 + " x " + numero2);

        // Establece la respuesta esperada para la multiplicación de los dos números
        int respuestaEsperada = numero1 * numero2;

        // Guarda la respuesta esperada para usarla en el método chekearRespuesta
        respuesta.setTag(respuestaEsperada);

        // Limpia el área de respuesta del usuario
        respuestaUsuario.setText("");

    }

    private void mostrarBarra() {
        ProgressBar bar = view.findViewById(R.id.progressBar);

        indiceActualBarra++;


        bar.setProgress(indiceActualBarra);
    }

    private void mostrarImagen() {
        ImageView imageView = view.findViewById(R.id.imageViewHeroe);
        if (indiceActualImagen < imagen_batman.length - 1) {
            indiceActualImagen++;

        }
//        else {
//            indiceActual = 0;
//        }
        imageView.setImageResource(imagen_batman[indiceActualImagen]);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boton_cero:
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
                    iniciarTablaDificil();

                } else {
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
                mostrarImagen();
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
}