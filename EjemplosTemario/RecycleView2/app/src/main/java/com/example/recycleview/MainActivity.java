package com.example.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    Elementos elementos = new Elementos("Nombre");
    RecyclerView r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r=findViewById(R.id.recycleview);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(new MyReciclerViewAdapter(elementos));

    }
}