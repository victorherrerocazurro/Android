package com.example.tarde.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tarde on 09/02/2015.
 */
public class NoticiasSQLiteOpenHelper extends SQLiteOpenHelper{

    private Context context;

    public NoticiasSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //Referencia al contexto para poder recoger los recursos que contiene los scripts
        this.context = context;
    }

    //Gestion o Administracion de la BD
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLiteDataBase semejante al Session
        ejecutarScript(db, R.array.scriptCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch (oldVersion){
            case 1:
                ejecutarScript(db, R.array.scriptUpgrade_1_3);
                break;
            case 2:
                ejecutarScript(db, R.array.scriptUpgrade_2_3);
        }
    }

    private void ejecutarScript(SQLiteDatabase db, int resScript) {

        String[] script = context.getResources().getStringArray(resScript);

        db.beginTransaction();

        try {
            for (String sentencia : script){
                db.execSQL(sentencia);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
