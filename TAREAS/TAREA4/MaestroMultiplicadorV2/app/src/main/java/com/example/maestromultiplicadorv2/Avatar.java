package com.example.maestromultiplicadorv2;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Avatar {
    private String nombre, descripcion;
    private int foto;

    public Avatar(String nombre, String descripcion, int foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
