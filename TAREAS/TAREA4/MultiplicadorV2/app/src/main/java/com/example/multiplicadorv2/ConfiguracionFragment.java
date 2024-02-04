package com.example.multiplicadorv2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ConfiguracionFragment extends Fragment {

    View view;
    TextView heroeConf;
    SelectorAvatarFragment selectorAvatarFragment = new SelectorAvatarFragment();

    public ConfiguracionFragment() {
        // Required empty public constructor
    }


    public static ConfiguracionFragment newInstance(String param1, String param2) {
        ConfiguracionFragment fragment = new ConfiguracionFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_configuracion, container, false);
        heroeConf = view.findViewById(R.id.textoNombreAvaConfig);

        Button selectAvatar = view.findViewById(R.id.botonAvatarConfig);
        selectAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectorFragment(selectorAvatarFragment);
            }
        });

        Bundle datosRecividos = getArguments();
        if (datosRecividos != null) {
            heroeConf.setText("Selecciono Avatar: "+datosRecividos.getString("dato", ""));
        }
        return view;
    }

    private void selectorFragment(Fragment f) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //relaciona el fragment recibido "f" con el contenedor de fragment 'frame_container'
        transaction.replace(R.id.contenedor, f);
        transaction.commit();
    }
}