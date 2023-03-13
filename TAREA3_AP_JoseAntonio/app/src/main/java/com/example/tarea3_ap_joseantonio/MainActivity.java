package com.example.tarea3_ap_joseantonio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea3_ap_joseantonio.databinding.ActivityMainBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//main
@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private ActivityMainBinding binding;
    public static final int SELECCIONA_PRODUCTO = 1;
    String fecha;
    HomeFragment homeFragment = new HomeFragment();
    CalenFragment calenFragment = new CalenFragment();
    BicleFragment bicleFragment = new BicleFragment();
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fecha = getIntent().getStringExtra("FECHA");
       

        //p1
        binding.bottomNavigation.setOnClickListener(this);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(homeFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()) {
                case R.id.homeFragment:
                    loadFragment(homeFragment);
                    return true;
                case R.id.biciFragment:
                    loadFragment(bicleFragment);
                    return true;
                case R.id.calendarioFragment:
                    loadFragment(calenFragment);
                    return true;
              }

            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_cotainer,fragment);
        transaction.commit();
    }



    // PASO 1
    @Override
    public void onClick(View v) {

        try {
            //Metodo para enviar Email
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"email"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Alquiler de Bicicleta");
            intent.putExtra(Intent.EXTRA_TEXT, "Hola me encantaria alquilar " + "tu maravillosa bicicleta el día " + fecha +  "\n Un saludo");
            startActivity(intent);
        }catch (Throwable e){
            Toast.makeText(this,"fecha no seleccionada", Toast.LENGTH_LONG).show();
        }
    }
    /*
     * PASO 3
     * La actividad principal obtiene los resultados a través de este método, que es la función callback
     */
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Si se seleccionó correctamente el producto ...
        if (requestCode == SELECCIONA_PRODUCTO) {
            if (resultCode == RESULT_OK) {
              //  String fecha = data.getStringExtra("PRODUCTO");

            }
        }
    }
}