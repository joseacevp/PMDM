package com.example.tarea3v3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tarea3v3.databinding.ActivityCalendarioBinding;

public class BiciActivity extends AppCompatActivity implements CalendarioActivity.OnFechaSeleccionada{
    private ActivityCalendarioBinding binding;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;
    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarioBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());

        BikesContent.clearBikes();
        BikesContent.loadBikesFromJSON(this);
        System.out.println(BikesContent.ITEMS.size());

        /*
         * INICIO CREACIÓN DEL RecyclerView
         */

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        myAdapter = new Adaptador(BikesContent.ITEMS, this);//mlistener);
        binding.recyclerView.setAdapter(myAdapter);
        /*
         * FIN CREACIÓN DEL RecyclerView
         */

    }




    // PASO 2
    // Este método captura la selección del usuario
    public void terminar(BikesContent.Bike product){
        //System.out.println("TERMINAR: " + product.toString());
        Intent i = new Intent();
        i.putExtra("PRODUCTO", product.getEmail());
        String email=product.getEmail();
        // Los resultados se devuelven a través de un Intent invocando al método setResult()
        setResult(RESULT_OK,i);
        try {
            //Metodo para enviar Email
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Alquiler de Bicicleta");
            intent.putExtra(Intent.EXTRA_TEXT, "Hola me encantaria alquilar " + "tu maravillosa bicicleta el día " +fecha+ "\n Un saludo");
            startActivity(intent);
        }catch (Throwable e){
            Toast.makeText(getApplicationContext(),"fecha no seleccionada", Toast.LENGTH_LONG).show();
        }
        // Se finaliza la actividad invocando al método finish()
        //finish();
    }

    @Override
    public void onResultadoFecha(String fecha) {
        this.fecha = fecha;
    }
}