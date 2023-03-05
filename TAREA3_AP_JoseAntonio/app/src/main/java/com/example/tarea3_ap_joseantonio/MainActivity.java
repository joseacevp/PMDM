package com.example.tarea3_ap_joseantonio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//main
@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment = new HomeFragment();
    CalenFragment calenFragment = new CalenFragment();
    BicleFragment bicleFragment = new BicleFragment();
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}