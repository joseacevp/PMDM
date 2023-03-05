package com.example.tarea3segundo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea3segundo.databinding.ActivityBiciBinding;

public class BiciActivity extends AppCompatActivity implements View.OnClickListener {

    String fecha;
    String email;
    Bundle bundle;

    private ActivityBiciBinding binding;
    public static final int SELECCIONA_FECHA = 1;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBiciBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BikesContent.clearBikes();
        BikesContent.loadBikesFromJSON(this);
        System.out.println(BikesContent.ITEMS.size());


        binding.recyclerView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        myAdapter = new Adaptador(BikesContent.ITEMS,this);
        binding.recyclerView.setAdapter(myAdapter);
    }
        // PASO 2
        // Este método captura la selección del usuario
      public void terminar (BikesContent.Bike bike){
           System.out.println("TERMINAR: " + bike.toString());
           Intent i = new Intent();
           i.putExtra("PRODUCTO", bike.getEmail());

           // Los resultados se devuelven a través de un Intent invocando al método setResult()
           setResult(RESULT_OK,i);

           // Se finaliza la actividad invocando al método finish()
           //finish();
      }


    @Override
    public void onClick(View view) {
        //Recupera los datos de la fecha seleccionada
        try {
            bundle = getIntent().getExtras();
            fecha = bundle.getString("fechaKey");

            //Metodo para enviar Email
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Alquiler de Bicicleta");
            intent.putExtra(Intent.EXTRA_TEXT, "Hola me encantaria alquilar " + "tu maravillosa bicicleta el día " +fecha+  "\n Un saludo");
            startActivity(intent);
        }catch (Throwable e){
            Toast.makeText(getApplicationContext(),"fecha no seleccionada", Toast.LENGTH_LONG).show();
        }


    }
    /*
     * PASO 3
     * La actividad principal obtiene los resultados a través de este método,
     * que es la función callback
     */
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Si se seleccionó correctamente el producto ...
        if (requestCode == SELECCIONA_FECHA) {
            if (resultCode == RESULT_OK) {
                // El resultado se obtiene a través del objeto Intent
                email= data.getStringExtra("PRODUCTO").toString();
                Toast.makeText(getApplicationContext(),email, Toast.LENGTH_LONG).show();
            }
        }
    }

}