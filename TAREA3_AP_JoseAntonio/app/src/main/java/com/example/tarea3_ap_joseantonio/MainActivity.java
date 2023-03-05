package com.example.tarea3_ap_joseantonio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tarea3_ap_joseantonio.databinding.ActivityMainBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//main
@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private ActivityMainBinding binding;


    HomeFragment homeFragment = new HomeFragment();
    CalenFragment calenFragment = new CalenFragment();
    BicleFragment bicleFragment = new BicleFragment();
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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




    @Override
    public void onClick(View v) {

    }
}