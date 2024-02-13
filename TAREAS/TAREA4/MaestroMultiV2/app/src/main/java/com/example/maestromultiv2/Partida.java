package com.example.maestromultiv2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Partida implements Parcelable {
    String usuario,heroe,dificultad,nuero_tabla;
    ArrayList<String> lista_aciertos;

    public Partida(String usuario, String heroe, String dificultad, String nuero_tabla) {
        this.usuario = usuario;
        this.heroe = heroe;
        this.dificultad = dificultad;
        this.nuero_tabla = nuero_tabla;
        lista_aciertos = new ArrayList<>();
    }

    protected Partida(Parcel in) {
        usuario = in.readString();
        heroe = in.readString();
        dificultad = in.readString();
        nuero_tabla = in.readString();
        lista_aciertos = in.createStringArrayList();
    }
    void addAcierto(String s){
        lista_aciertos.add(s);
    }

    public static final Creator<Partida> CREATOR = new Creator<Partida>() {
        @Override
        public Partida createFromParcel(Parcel in) {
            return new Partida(in);
        }

        @Override
        public Partida[] newArray(int size) {
            return new Partida[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeString(heroe);
        dest.writeString(dificultad);
        dest.writeString(nuero_tabla);
        dest.writeStringList(lista_aciertos);
    }
}
