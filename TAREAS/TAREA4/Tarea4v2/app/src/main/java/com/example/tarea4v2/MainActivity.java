package com.example.tarea4v2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea4v2.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Evitar el giro automático
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//recive el usuario de la actividad login
        Bundle datoRecivido = this.getIntent().getExtras();
        if (datoRecivido != null) {
            String datoString = datoRecivido.getString("nombreUsuarioInicio");
            usuario = datoString.toString();
            guardarPreferencias();
            System.out.println(usuario);
        }

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        System.out.println("usuario recivido: " + usuario);
        if (usuario.equals("Administrador")) {

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_configurar, R.id.nav_entrenar, R.id.nav_estadisticas,R.id.enviarEstadisticasFragment)
                    .setOpenableLayout(drawer)
                    .build();
        } else {
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_configurar, R.id.nav_entrenar)
                    .setOpenableLayout(drawer)
                    .build();
        }
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void guardarPreferencias() {

        SharedPreferences preferencias = getApplicationContext().getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("usuario", usuario);
        editor.commit();
    }
}