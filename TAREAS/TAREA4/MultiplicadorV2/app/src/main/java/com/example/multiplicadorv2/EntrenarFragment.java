package com.example.multiplicadorv2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EntrenarFragment extends Fragment {
    View view;

    public EntrenarFragment() {
        // Required empty public constructor
    }

    public static EntrenarFragment newInstance(String param1, String param2) {
        EntrenarFragment fragment = new EntrenarFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_entrenar, container, false);
        return view;
    }
}