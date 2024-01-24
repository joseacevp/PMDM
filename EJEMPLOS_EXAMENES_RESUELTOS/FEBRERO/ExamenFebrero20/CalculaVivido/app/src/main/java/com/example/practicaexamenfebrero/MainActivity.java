package com.example.practicaexamenfebrero;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.danlew.android.joda.JodaTimeAndroid;

import com.example.practicaexamenfebrero.databinding.ActivityMainBinding;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DialogoFecha.OnFechaSeleccionada {
    String fechaNacimiento;
    ActivityMainBinding binding;
    ArrayList<String> listaDatos = new ArrayList<>();
    DialogoFecha fecha = new DialogoFecha();
    public static final int DATOS_EDAD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        JodaTimeAndroid.init(this);
        binding.imageButtonCalend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecha.show(getFragmentManager(), "fecha");
            }
        });

        binding.botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VentanaDatos.class);

                Bundle miDato = new Bundle();
                miDato.putIntegerArrayList("dato", calcularEdad());
                intent.putExtras(miDato);
                startActivityForResult(intent, DATOS_EDAD);
            }
        });
        Bundle datoRecivido = this.getIntent().getExtras();
        if (datoRecivido != null) {
            String datosOpt = datoRecivido.getString("resultado");
            String anos = datosOpt;
            binding.textResultado.setText(anos);
        }
    }

    //fecha optenida en el fragmento calendario
    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        fechaNacimiento = fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR);
        binding.editEdadSelec.setText(fechaNacimiento);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == DATOS_EDAD) {
            if (resultCode == RESULT_OK) {
                binding.textResultado.setText(data.getStringExtra("fecha"));
            }
        }
    }

    public ArrayList<Integer> calcularEdad() {

        String[] fechaArray = fechaNacimiento.split("/");
        int dia = Integer.valueOf(fechaArray[0]);
        int mes = Integer.valueOf(fechaArray[1]);
        int año = Integer.valueOf(fechaArray[2]);
        /*
         * A continuación obtenemos del sistema la fecha ACTUAL
         *
         * Al mes le sumamos 1 ya que en el formato de Calendar el mes empieza en 0
         * Enero=0, Febrero=1, Marzo=2, ... , Diciembre=11
         */
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;
        int day = today.get(Calendar.DAY_OF_MONTH);
// Y para terminar, usando la librería JodaTime calculamos la edad
        LocalDate dateNacimiento = new LocalDate(año, mes, dia);
        LocalDate dateActual = new LocalDate(year, month, day);
        Period period = new Period(dateNacimiento, dateActual, PeriodType.yearMonthDay());
        System.out.println(period.getYears() + " años and " +
                period.getMonths() + " meses and " + period.getDays() + " dias");
        int totalAños = period.getYears();
        int totalMeses = totalAños * 12 + period.getMonths();
        int totalDias = totalMeses * 30 + period.getDays();
        int totalHoras = totalDias * 24 + period.getHours();
        double totalMinutos = totalHoras * 60;
        double totalSegundos = totalMinutos * 60;

        ArrayList<Integer> datosOptenido = new ArrayList<>();
        datosOptenido.add(period.getYears());
        datosOptenido.add(period.getMonths());
        datosOptenido.add(period.getDays());
//        datosOptenido.add();
//        datosOptenido.add();
//
        return datosOptenido;
    }

}