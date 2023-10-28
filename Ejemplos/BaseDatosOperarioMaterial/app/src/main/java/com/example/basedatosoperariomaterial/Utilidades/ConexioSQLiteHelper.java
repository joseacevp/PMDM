package com.example.basedatosoperariomaterial.Utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ConexioSQLiteHelper extends SQLiteOpenHelper {

    //Crea la base de datos
    public ConexioSQLiteHelper(@Nullable Context context, @Nullable String name,
                               @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Genera las Tablas de la base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_OPERARIOS);
        sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_MATERIALES);
    }

    //Actualiza la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_OPERARIOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_MATERIALES);
        onCreate(sqLiteDatabase);
    }
}
