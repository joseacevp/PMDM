package com.example.basedatosoperariomaterial.entidades;

import java.io.Serializable;

public class Material implements Serializable {
    private Integer idMaterial;
    private String nombreMaterial;

    public Material(Integer idMaterial, String nombreMaterial) {
        this.idMaterial = idMaterial;
        this.nombreMaterial = nombreMaterial;
    }

    public Material() {
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }
}

