package com.example.demo04contentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo04contentprovider.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    ActivityMainBinding binding;
    private boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Solicitud de permisos
        if(checkSelfPermission("android.permission.READ_CONTACTS") != PackageManager.PERMISSION_GRANTED
                ||
                checkSelfPermission("android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED ) {

            requestPermissions(new String[]{
                            "android.permission.READ_CONTACTS",
                            "android.permission.SEND_SMS"},
                    PETICION_PERMISOS);
        }
        else tengo_permisos = true;
try{
    
}catch (Exception e){
    
}
        binding.listViewContactos.setOnItemLongClickListener(this);
        binding.editTextContacto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(tengo_permisos){
                    try {
                        ArrayList<String> lista_contactos = buscarContacto(binding.editTextContacto.getText().toString());
                        Log.d("onEditorAction: ", lista_contactos.size()+"");
                        Log.d("onEditorAction: ", lista_contactos.get(0)+"");
                        // mandamos el adaptador a la lista de contactos

                        binding.listViewContactos.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.fila,lista_contactos));
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "SIN RESULTADOS", Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }
        });
        binding.listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemLongClick(adapterView,view,i,l);
                
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PETICION_PERMISOS)
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                tengo_permisos=true;
            else
                tengo_permisos=false;
    }

    @SuppressLint("Range")
    private ArrayList<String> buscarContacto(String contacto) {
        String proyeccion[] = {
                                ContactsContract.Contacts._ID,
                                ContactsContract.Contacts.DISPLAY_NAME,
                                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                                ContactsContract.Contacts.PHOTO_ID
                                };
        String filtro = ContactsContract.Contacts.DISPLAY_NAME + " like ?";
        String args_filtro[] = {"%" + contacto + "%"};

        ArrayList<String> lista_contactos = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                proyeccion, filtro, args_filtro, null);

        // Con el cursor, recorrer la lista de contactos extraída, agregando los elementos a un ArrayList
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Obtener el nombre del contacto
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                // Si tiene teléfono lo agregamos a la lista de contactos
                if (Integer.parseInt(cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    lista_contactos.add(name);
                }
            } // FIN while
        } // FIN if

        cursor.close();
        return lista_contactos;
    }

    @SuppressLint("Range")
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(this, "Pulso", Toast.LENGTH_SHORT).show();
        TextView textView = (TextView) view;
        String nombreContacto = textView.getText().toString();
        String proyeccion[] = {ContactsContract.Contacts._ID};
        String filtro = ContactsContract.Contacts.DISPLAY_NAME + " = ?";
        String args_filtro[] = {nombreContacto};

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                proyeccion, filtro, args_filtro, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String identificador = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts._ID));
                enviarSMS(identificador, "Hola, ¿quedamos?");
            }
        }

        cursor.close();
        return true;
    }

    @SuppressLint("Range")
    private void enviarSMS(String identificador, String mensaje) {
        ContentResolver contentResolver = getContentResolver();
        SmsManager smsManager = SmsManager.getDefault();
        Cursor cursorTelefono = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{identificador},
                null
                );

        while (cursorTelefono.moveToNext()) {
            String telefono = cursorTelefono.getString(
                    cursorTelefono.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
            try {
                smsManager.sendTextMessage(telefono, null, mensaje, null, null);
                Log.d("enviarSMS(): ", "SMS enviado");
            } catch (Exception e){
                Log.d("enviarSMS(): ", "No se pudo enviar el SMS");
                e.printStackTrace();
            }
        } // FIN while

        cursorTelefono.close();
    }

}