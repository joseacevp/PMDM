package com.example.tarea03_ap_joseantonio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    //declaracion de variables globales fragmentos o vistas
    MenuFragment homeFragment = new MenuFragment();
    FechasFragment fechasFragment = new FechasFragment();
    BicisFragment bicisFragment = new BicisFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancia de BottonNavigation
        BottomNavigationView navegador = findViewById(R.id.botones_navegacion);
        navegador.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    //metodo listener para controlar los eventos del menu navegacion
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.homeFrament:
                    cargarFragment(homeFragment);//navega al fragmento casa
                    return true;
                case R.id.fechaFragment:
                    cargarFragment(fechasFragment);//navega al fragmento fecha
                    return true;
                case R.id.bicisFragment:
                    cargarFragment(bicisFragment);//navega al fragmento bicicletas
                    return true;
            }
            return false;
        }
    };
    //metodo par sustituir el fragmento alojado en el contenedor de fragmetos
  public void cargarFragment(Fragment fragment){
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.framen_contenedor, fragment);
      transaction.commit();
  }
}