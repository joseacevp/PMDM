package com.example.tarea5.appprincipal;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.tarea5.R;
import com.example.tarea5.appprincipal.ui.fechas.FechaViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea5.databinding.ActivityAppPrincipalBinding;

public class AppPrincipal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private boolean fechaSeleccionada = false;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Menu menu;
    private FechaViewModel fechaViewModel;
    private ActivityAppPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fechaViewModel = new ViewModelProvider(this).get(FechaViewModel.class);
        fechaViewModel.getSelectedDateString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    fechaSeleccionada = true;
                } else {
                    fechaSeleccionada = false;
                }
            }
        });

        setSupportActionBar(binding.appBarAppPrincipal.toolbar);

        drawer = binding.drawerLayout;
        navigationView = binding.navView;


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_convocados, R.id.nav_equipo, R.id.nav_borrar_convocados,R.id.nav_seleccionar_fecha)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_app_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
// Configurar listener para las opciones del Navigation Drawer
        Menu menu = navigationView.getMenu();

        menu.findItem(R.id.nav_guardar).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                if (fechaSeleccionada) {

                } else {
                    Toast toast = Toast.makeText(AppPrincipal.this, "No se seleccionó ninguna Fecha", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0); // Establece la gravedad en el centro de la pantalla
                    toast.show();
                }
                return true;
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_app_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}