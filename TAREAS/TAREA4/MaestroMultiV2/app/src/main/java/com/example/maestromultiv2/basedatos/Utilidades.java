package com.example.maestromultiv2.basedatos;

public class Utilidades {
    //constantes campos tablas
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String NOMBRE = "nombre";
    public static final String PASSWORD = "password";

    //almacena constantes repersentando los campos y las tablas de la base de datos
    //para evitar repetir los mismos parametros muchas veces
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "
            + TABLA_USUARIOS
            + " ( "
            + NOMBRE
            + " TEXT,"
            + PASSWORD
            + "  TEXT)";


}
