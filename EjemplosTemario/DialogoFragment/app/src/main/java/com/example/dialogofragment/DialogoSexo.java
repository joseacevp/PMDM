package com.example.dialogofragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogoSexo extends DialogFragment {

    RespuestaDialogoSexo respuesta;
/*Este metodo es llamado al hacer show() de la clase DialogFragment()*/
   @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       //Usamos la clase Builder para construir el diálogo
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       //Escribimos el título
       builder.setTitle("Pregunta muy importante");
       //Escribimos la pregunta o mensaje
       builder.setMessage("¿Eres una Chica");
       //Añadimos el botón de Si y su acción asociada
       builder.setPositiveButton("¡Si!", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               respuesta.onRespuesta("Es una Chica");
           }


       });
       //Añadimos el botón de No
       builder.setNegativeButton("¡No!", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               respuesta.onRespuesta("Es un Chico");
           }
       });
       //Crear el AlertDialog y devolverlo
       return builder.create();
    }
    public interface RespuestaDialogoSexo {
        //interface de comunicacion entre la actividad y el dialogo
        public void onRespuesta(String s);
    }

    //Se invoca cuando el fragmento se añade a la actividad
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        respuesta = (RespuestaDialogoSexo) context;
    }
}
