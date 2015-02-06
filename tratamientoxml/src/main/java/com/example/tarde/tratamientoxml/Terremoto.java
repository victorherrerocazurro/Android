package com.example.tarde.tratamientoxml;

import java.util.Date;

/**
 * Created by tarde on 06/02/2015.
 */
public class Terremoto {
    private String id;
    private String titulo;
    private Date fecha;
    private String link;
    private float magnitud;
    private float latitud;
    private float longitud;

    public Terremoto(String id, String titulo, Date fecha, String link, float magnitud, float latitud, float longitud) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.link = link;
        this.magnitud = magnitud;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Terremoto() {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public float getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(float magnitud) {
        this.magnitud = magnitud;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
