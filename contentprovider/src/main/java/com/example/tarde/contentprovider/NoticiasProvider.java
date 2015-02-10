package com.example.tarde.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.tarde.noticiasutils.NoticiasProviderUtil;

public class NoticiasProvider extends ContentProvider {

    private Context context;
    private SQLiteDatabase db;
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CODE_NOTICIAS = 1;
    private static final int CODE_NOTICIA = 2;
    private static final int CODE_AUTORES = 3;
    private static final int CODE_AUTOR = 4;

    private static final String TABLA = "NOTICIAS";

    static{
        //content://com.curso.android.noticias/Noticia -> Afecta a todo el contenido
        matcher.addURI(NoticiasProviderUtil.NOTICIAS_AUTHORITY, NoticiasProviderUtil.NOTICIA_ENTIDAD, CODE_NOTICIAS);
        //content://com.curso.android.noticias/Noticia/abc -> Afecta solo al item con ID = abc
        matcher.addURI(NoticiasProviderUtil.NOTICIAS_AUTHORITY, NoticiasProviderUtil.NOTICIA_ENTIDAD + "/*", CODE_NOTICIA);

        //content://com.curso.android.noticias/Autor -> Afecta a todo el contenido
        matcher.addURI(NoticiasProviderUtil.NOTICIAS_AUTHORITY, NoticiasProviderUtil.AUTOR_ENTIDAD, CODE_AUTORES);
        //content://com.curso.android.noticias/Autor/1 -> Afecta solo al item con ID = 1
        matcher.addURI(NoticiasProviderUtil.NOTICIAS_AUTHORITY, NoticiasProviderUtil.AUTOR_ENTIDAD + "/#", CODE_AUTOR);
    }

    @Override
    public boolean onCreate() {
        context = getContext();

        NoticiasSQLiteOpenHelper noticiasSQLiteOpenHelper
                = new NoticiasSQLiteOpenHelper(context, "NoticiasDB.s3db", null,
                getContext().getResources().getInteger(R.integer.DataBaseVersion));

        db = noticiasSQLiteOpenHelper.getWritableDatabase();
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (matcher.match(uri)){
            case CODE_NOTICIA:
                String[] whereArgs = {uri.getLastPathSegment()};
                String whereClause = NoticiasProviderUtil.CAMPO_ID + " = ?";
                return db.delete(TABLA, whereClause, whereArgs);
            case CODE_NOTICIAS:
                return db.delete(TABLA, selection, selectionArgs);
            case CODE_AUTOR:
                throw new UnsupportedOperationException("No se soporta el borrado de un Autor");
            case CODE_AUTORES:
                throw new UnsupportedOperationException("No se soporta el borrado de autores de froma masiva");
            default:
                throw new UnsupportedOperationException("Operacion no soportada");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)) {
            case CODE_NOTICIAS:
                db.insert(TABLA, NoticiasProviderUtil.CAMPO_TITULO, values);//insertOrThrow, lanza el SQLException cuando no se puede insertar, por ejemplo por clave duplicada
                return Uri.withAppendedPath(uri, values.getAsString(NoticiasProviderUtil.CAMPO_ID));
            default:
                throw new UnsupportedOperationException("Operacion no soportada");
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (matcher.match(uri)){
            case CODE_NOTICIA:
                String[] whereArgs = {uri.getLastPathSegment()};
                String whereClause = NoticiasProviderUtil.CAMPO_ID + " = ?";
                return db.query(false, TABLA, projection, whereClause, whereArgs, null, null, sortOrder, null);
            case CODE_NOTICIAS:
                return db.query(false, TABLA, projection, selection, selectionArgs, null, null, sortOrder, null);
            default:
                throw new UnsupportedOperationException("Operacion no soportada");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (matcher.match(uri)){
            case CODE_NOTICIA:
                String[] whereArgs = {uri.getLastPathSegment()};
                String whereClause = NoticiasProviderUtil.CAMPO_ID + " = ?";
                return db.update(TABLA, values, whereClause, whereArgs);
            case CODE_NOTICIAS:
                return db.update(TABLA, values, selection, selectionArgs);
            default:
                throw new UnsupportedOperationException("Operacion no soportada");
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)){
            case CODE_NOTICIA:
                return "vnd.android.cursor.item/vnd." + NoticiasProviderUtil.NOTICIAS_AUTHORITY + "." + NoticiasProviderUtil.NOTICIA_ENTIDAD;
            case CODE_NOTICIAS:
                return "vnd.android.cursor.dir/vnd." + NoticiasProviderUtil.NOTICIAS_AUTHORITY + "." + NoticiasProviderUtil.NOTICIA_ENTIDAD;
            case CODE_AUTOR:
                return "vnd.android.cursor.item/vnd." + NoticiasProviderUtil.NOTICIAS_AUTHORITY + "." + NoticiasProviderUtil.AUTOR_ENTIDAD;
            case CODE_AUTORES:
                return "vnd.android.cursor.dir/vnd." + NoticiasProviderUtil.NOTICIAS_AUTHORITY + "." + NoticiasProviderUtil.AUTOR_ENTIDAD;
            default:
                throw new UnsupportedOperationException("Tipo MIME no soportado");
        }
    }
}
