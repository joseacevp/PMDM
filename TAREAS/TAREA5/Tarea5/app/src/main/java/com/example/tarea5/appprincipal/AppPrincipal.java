package com.example.tarea5.appprincipal;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.example.tarea5.R;
import com.example.tarea5.appprincipal.ui.convocados.ConvocadosViewModel;
import com.example.tarea5.appprincipal.ui.equipo.Jugador;
import com.example.tarea5.appprincipal.ui.fechas.FechaViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea5.databinding.ActivityAppPrincipalBinding;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AppPrincipal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseFirestore mFirestore;
    private String fechaSeleccionada;
    private ArrayList<Jugador> jugadoresSeleccionados;
    private boolean hayfechaSeleccionada = false;
    private boolean haylistaConvocados = false;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Menu menu;
    private FechaViewModel fechaViewModel;
    private ConvocadosViewModel convocadosViewModel;
    private ActivityAppPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        comprobarDatos();
        mFirestore = FirebaseFirestore.getInstance();

        setSupportActionBar(binding.appBarAppPrincipal.toolbar);

        drawer = binding.drawerLayout;
        navigationView = binding.navView;


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_convocados, R.id.nav_equipo, R.id.nav_borrar_convocados,
                R.id.nav_seleccionar_fecha,
                R.id.nav_historico)
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
                if (hayfechaSeleccionada && haylistaConvocados) {
                    guardarDatos();
                } else {
                    Toast toast = Toast.makeText(AppPrincipal.this, "Faltan Datos para poder Guardar", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;
            }
        });
    }

    private void comprobarDatos() {
        fechaViewModel = new ViewModelProvider(this).get(FechaViewModel.class);
        fechaViewModel.getSelectedDateString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    hayfechaSeleccionada = true;
                } else {
                    hayfechaSeleccionada = false;
                }
            }
        });
        convocadosViewModel = new ViewModelProvider(this).get(ConvocadosViewModel.class);
        convocadosViewModel.getListaConvocados().observe(this, new Observer<ArrayList<Jugador>>() {
            @Override
            public void onChanged(ArrayList<Jugador> jugadors) {
                if (jugadors != null) {
                    haylistaConvocados = true;
                } else {
                    haylistaConvocados = false;
                }
            }
        });
    }

    private void guardarDatos() {
        fechaViewModel = new ViewModelProvider(this).get(FechaViewModel.class);
        fechaViewModel.getSelectedDateString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    fechaSeleccionada = s;
                }
            }
        });
        convocadosViewModel = new ViewModelProvider(this).get(ConvocadosViewModel.class);
        convocadosViewModel.getListaConvocados().observe(this, new Observer<ArrayList<Jugador>>() {
            @Override
            public void onChanged(ArrayList<Jugador> jugadors) {
                if (jugadors != null) {
                    jugadoresSeleccionados = jugadors;
                }
            }
        });

        for (int i = 0; i < jugadoresSeleccionados.size(); i++) {
            Jugador jugador = jugadoresSeleccionados.get(i);
            //guardar los datos del jugador
            Map<String, Object> map = new HashMap<>();
            map.put("nombre", jugador.getNombre());
            map.put("posicion", jugador.getNombre());
            map.put("foto", jugador.getFoto());
            map.put("fecha", fechaSeleccionada);

            mFirestore.collection("list_jugadores").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(AppPrincipal.this, "Grabado", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AppPrincipal.this, "Fallo Grabar", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_app_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}