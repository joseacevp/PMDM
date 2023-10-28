package com.example.basedatosoperariomaterial.Utilidades;

public class Utilidades {
    //Operario
    public static final String TABLA_OPERARIOS = "operarios";
    public static final String CAMPO_NOMBREOPERARIO = "nombreOperario";
    public static final String CAMPO_ID_OPERARIO = "idOperario";
    public static final String CAMPO_DEPARTAMENTO = "departamentoOperario";

    public static final String CREAR_TABLA_OPERARIOS = "CREATE TABLE "
            + TABLA_OPERARIOS
            + " ( "
            + CAMPO_ID_OPERARIO + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + CAMPO_NOMBREOPERARIO + " TEXT , "
            + CAMPO_DEPARTAMENTO + " TEXT "
            + " ) ";

    //Material
    public static final String TABLA_MATERIALES = "materiales";
    public static final String CAMPO_ID_MATERIAL = "idMaterial";
    public static final String CAMPO_NOMBRE_MATERIAL = "nombreMaterial";
    public static final String CAMPO_ID_MANODEOBRE = "idManoObra";


    public static final String CREAR_TABLA_MATERIALES = "CREATE TABLE "
            + TABLA_MATERIALES + " ( "
            + CAMPO_ID_MATERIAL  + " INTEGER ,"
            + CAMPO_NOMBRE_MATERIAL + " TEXT ,"
            + CAMPO_ID_MANODEOBRE + " INTEGER "
            + " ) ";

}
