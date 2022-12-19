package com.example.entrenadortiempo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private Fragment tiemposFragment, menuColoresFragment, informacionFragment, principalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiemposFragment = new TiemposFragment();
        menuColoresFragment = new MenuColoresFragment();
        informacionFragment = new InformacionFragment();
        principalFragment = new PrincipalFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragment, principalFragment).commit();
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
        }if (id == R.id.itemRest){
            System.out.println("realizar Reset");
        }
        if(id == R.id.itemInfo){
            fragmentTransaction2.replace(R.id.contenedorFragment, informacionFragment);
            fragmentTransaction2.addToBackStack(null);
        }
        fragmentTransaction2.commit();
        return super.onOptionsItemSelected(menuItem);
    }

}