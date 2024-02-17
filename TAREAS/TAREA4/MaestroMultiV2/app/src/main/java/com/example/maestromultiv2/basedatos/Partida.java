package com.example.maestromultiv2.basedatos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Partida  {
    String usuario,heroe,dificultad,nuero_tabla,fecha;
    ArrayList<String> lista_aciertos;

    public Partida(String usuario, String heroe, String dificultad, String nuero_tabla, String fecha, ArrayList<String> lista_aciertos) {
        this.usuario = usuario;
        this.heroe = heroe;
        this.dificultad = dificultad;
        this.nuero_tabla = nuero_tabla;
        this.fecha = fecha;
        this.lista_aciertos = lista_aciertos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHeroe() {
        return heroe;
    }

    public void setHeroe(String heroe) {
        this.heroe = heroe;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getNuero_tabla() {
        return nuero_tabla;
    }

    public void setNuero_tabla(String nuero_tabla) {
        this.nuero_tabla = nuero_tabla;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<String> getLista_aciertos() {
        return lista_aciertos;
    }

    public void setLista_aciertos(ArrayList<String> lista_aciertos) {
        this.lista_aciertos = lista_aciertos;
    }
}
