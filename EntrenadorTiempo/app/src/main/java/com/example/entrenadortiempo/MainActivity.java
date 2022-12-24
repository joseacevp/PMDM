package com.example.entrenadortiempo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ComunicaMenu {
    private FragmentTransaction fragmentTransaction;
    private Fragment tiemposFragment, menuColoresFragment, informacionFragment;
    private boolean iniciado = false;
    int cuentaPrepa;
    int cuentaTrabajo;
    int cuentaDesc;
    int cuentaCiclos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiemposFragment = new TiemposFragment();
        menuColoresFragment = new MenuColoresFragment();
        informacionFragment = new InformacionFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.contenedorFragment, tiemposFragment).commit();
        //fragmentTransaction.addToBackStack(null).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * metodo para utilizar las opciones del menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        FragmentTransaction fragmentTransaction2;
        fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
        int id = menuItem.getItemId();
        if (id == R.id.itemColores) {
            fragmentTransaction2.replace(R.id.contenedorFragment, menuColoresFragment);
            fragmentTransaction2.addToBackStack(null);
        }
        if (id == R.id.itemRest) {
            System.out.println("realizar Reset");
        }
        if (id == R.id.itemInfo) {
            fragmentTransaction2.replace(R.id.contenedorFragment, informacionFragment);
            fragmentTransaction2.addToBackStack(null);
        }
        fragmentTransaction2.commit();
        return super.onOptionsItemSelected(menuItem);
    }

    //metodo de la interface comunica menu con los datos del fragment TiemposFragment
    @Override
    public void menu(int tiempoPre, int tiempoTrab, int tiempoDesc, int numCicl, boolean iniciado) {
        this.cuentaPrepa = tiempoPre;
        this.cuentaTrabajo = tiempoTrab;
        this.cuentaDesc = tiempoDesc;
        this.cuentaCiclos = numCicl;
        this.iniciado = iniciado;
        Toast.makeText(this, String.valueOf(cuentaCiclos), Toast.LENGTH_LONG).show();

    }


}