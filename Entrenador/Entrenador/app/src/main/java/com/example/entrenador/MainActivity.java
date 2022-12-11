package com.example.entrenador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * metodo para crear un menu en el activity main
     *
     * @param menu
     * @return
     */
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
        int id= menuItem.getItemId();
        if(id==R.id.ItemColores){
            System.out.println("opcion colores");

//            PrincipalFragment principalFragment = new PrincipalFragment();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragmentContainerViewBarra,principalFragment);
//            fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(menuItem);
    }


}