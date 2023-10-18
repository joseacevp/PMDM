package com.example.sqlite.utilidades;

public class Utilidades {
    //constante campos tabla usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";

    //Scrip con los comandos SQL para crear la tabla
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " +
            TABLA_USUARIO + " (" +
            CAMPO_ID + " INTEGER, " +
            CAMPO_NOMBRE + " TEXT, " +
            CAMPO_TELEFONO + " TEXT)";

}
