package com.example.tarde.basededatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tarde on 09/02/2015.
 */
public class NoticiasDao {

    private SQLiteDatabase db;

    public NoticiasDao(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertar(Noticia noticia){
        db.insert(Noticia.TABLA,Noticia.CAMPO_TITULO,noticiaToContentValues(noticia));
    }

    public void editar(Noticia noticia){
        String whereClause = Noticia.CAMPO_ID + " = '?'";
        String[] whereArgs = {noticia.getId()};
        db.update(Noticia.TABLA,noticiaToContentValues(noticia),whereClause,whereArgs);
    }

    public void borrar (Noticia noticia){
        String whereClause = Noticia.CAMPO_ID + " = '?'";
        String[] whereArgs = {noticia.getId()};
        db.delete(Noticia.TABLA,whereClause,whereArgs);
    }

    public void borrar (String id){
        String whereClause = Noticia.CAMPO_ID + " = '?'";
        String[] whereArgs = {id};
        db.delete(Noticia.TABLA,whereClause,whereArgs);
    }

    public Noticia consultar(String id){
        String[] proyeccion = {Noticia.CAMPO_ID,Noticia.CAMPO_TITULO, Noticia.CAMPO_CREADOR};
        String whereClause = Noticia.CAMPO_ID + " = '?'";
        String[] whereArgs = {id};
        Cursor cursor = db.query(false, Noticia.TABLA, proyeccion, whereClause, whereArgs, null, null, null, null);
        return noticiaToCursor(cursor).get(0);
    }

    public List<Noticia> consultar(){
        String[] proyeccion = {Noticia.CAMPO_ID,Noticia.CAMPO_TITULO, Noticia.CAMPO_CREADOR};
        Cursor cursor = db.query(false, Noticia.TABLA, proyeccion, null, null, null, null, null, null);
        return noticiaToCursor(cursor);
    }

    private ContentValues noticiaToContentValues(Noticia noticia){
        ContentValues resultado = new ContentValues();

        if (noticia.getId() != null) {
            resultado.put(Noticia.CAMPO_ID, noticia.getId());
        } else {
            resultado.putNull(Noticia.CAMPO_ID);
        }
        resultado.put(Noticia.CAMPO_TITULO,noticia.getTitulo());
        resultado.put(Noticia.CAMPO_CREADOR,noticia.getCreador());
        resultado.put(Noticia.CAMPO_LINK,noticia.getLink());
        resultado.put(Noticia.CAMPO_DESCRIPCION,noticia.getDescripcion());
        resultado.put(Noticia.CAMPO_FECHA,noticia.getFechaPublicacion().getTime());
        resultado.put(Noticia.CAMPO_CONTENIDO,noticia.getContenido());

        return resultado;
    }

    private List<Noticia> noticiaToCursor(Cursor cursor){
        List<Noticia> resultado = new LinkedList<>();

        if(cursor.moveToFirst()){
            do{
                Noticia noticia = new Noticia();
                if(cursor.getColumnIndex(Noticia.CAMPO_ID) != -1) {
                    noticia.setId(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_ID)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_TITULO) != -1) {
                    noticia.setTitulo(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_TITULO)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_CREADOR) != -1) {
                    noticia.setCreador(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_CREADOR)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_DESCRIPCION) != -1) {
                    noticia.setDescripcion(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_DESCRIPCION)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_LINK) != -1) {
                    noticia.setLink(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_LINK)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_FECHA) != -1) {
                    noticia.setFechaPublicacion(new Date(cursor.getLong(cursor.getColumnIndex(Noticia.CAMPO_FECHA))));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_CONTENIDO) != -1) {
                    noticia.setContenido(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_CONTENIDO)));
                }
                resultado.add(noticia);
            }while (cursor.moveToNext());
        }

        return resultado;
    }
}