package com.example.basedatosoperariomaterial.entidades;

import java.io.Serializable;

public class Operario implements Serializable {
    private Integer idOperario;
    private String nombre,departamento;

    public Operario(int idOperario, String nombre, String departamento) {
        this.idOperario = idOperario;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public Operario() {
    }

    public int getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(int idOperario) {
        this.idOperario = idOperario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
