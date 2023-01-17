package com.example.tarea3_ap_joseantonio;

public class Bikes {
    private int imagen;
    private String localidad;
    private String descripcion;
    private int icono;

    public Bikes() {
    }

    public Bikes(int imagen, String localidad, String descripcion, int icono) {
        this.imagen = imagen;
        this.localidad = localidad;
        this.descripcion = descripcion;
        this.icono = icono;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
