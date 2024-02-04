package com.example.multiplicadorv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multiplicadorv2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SelectorAvatarFragment selectorAvatarFragment= new SelectorAvatarFragment();
    ConfiguracionFragment configuracionFragment = new ConfiguracionFragment();
    EntrenarFragment entrenarFragment = new EntrenarFragment();
    EstadisticasFragment estadisticasFragment = new EstadisticasFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        BottomNavigationView navigation = findViewById(R.id.barraNavegacion);

        //metodo mOnNavegationListener control de eventos al seleccionar las opciones del menu bar
        navigation.setOnNavigationItemSelectedListener(mOnNavegationListener);
        selectorFragment(configuracionFragment);

    }

    private void selectorFragment(Fragment f) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //relaciona el fragment recibido "f" con el contenedor de fragment 'frame_container'
        transaction.replace(R.id.contenedor, f);
        transaction.commit();
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavegationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.id_fragmento_configuracion:
                    //metodo loadFragment para lanzar las pantallas
                    selectorFragment(configuracionFragment);
                    break;
                case R.id.id_fragmento_entrenar:
                    selectorFragment(entrenarFragment);
                    break;
                case R.id.id_fragmento_estadisticas:
                    selectorFragment(estadisticasFragment);
                    break;
            }

            return false;
        }
    };

}