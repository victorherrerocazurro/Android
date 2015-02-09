package com.example.tarde.basededatos;

import java.util.Date;

/**
 * Created by tarde on 09/02/2015.
 */
public class Noticia {

    public static final String TABLA = "NOTICIAS";
    public static final String CAMPO_ID = "ID";
    public static final String CAMPO_TITULO = "TITULO";
    public static final String CAMPO_DESCRIPCION = "DESCRIPCION";
    public static final String CAMPO_CREADOR = "CREADOR";
    public static final String CAMPO_FECHA = "FECHA";
    public static final String CAMPO_LINK = "LINK";
    public static final String CAMPO_CONTENIDO = "CONTENIDO";

    private String id;
    private String titulo;
    private String link;
    private String creador;
    private String descripcion;
    private Date fechaPublicacion;
    private String contenido;

    public Noticia() {
    }

    public Noticia(String id, String titulo, String link, String creador, String descripcion, Date fechaPublicacion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.link = link;
        this.creador = creador;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.contenido = contenido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
