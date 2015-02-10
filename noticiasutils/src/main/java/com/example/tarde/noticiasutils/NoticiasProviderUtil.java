package com.example.tarde.noticiasutils;

import android.net.Uri;

/**
 * Created by tarde on 10/02/2015.
 */
public interface NoticiasProviderUtil {

    public static final Uri NOTICIA_URI = Uri.parse("content://com.curso.android.noticias/Noticia");
    public static final Uri AUTOR_URI = Uri.parse("content://com.curso.android.noticias/Autor");

    public static final String NOTICIAS_AUTHORITY = "com.curso.android.noticias";
    public static final String NOTICIA_ENTIDAD = "Noticia";
    public static final String AUTOR_ENTIDAD = "Autor";

    public static final String CAMPO_ID = "ID";
    public static final String CAMPO_TITULO = "TITULO";
    public static final String CAMPO_DESCRIPCION = "DESCRIPCION";
    public static final String CAMPO_CREADOR = "CREADOR";
    public static final String CAMPO_FECHA = "FECHA";
    public static final String CAMPO_LINK = "LINK";
    public static final String CAMPO_CONTENIDO = "CONTENIDO";
}
