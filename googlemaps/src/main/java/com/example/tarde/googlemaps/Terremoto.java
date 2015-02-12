package com.example.tarde.googlemaps;

import java.io.Serializable;

/**
 * Created by tarde on 12/02/2015.
 */
public class Terremoto implements Serializable{

    private String titulo;
    private float magnitud;
    private float latitud;
    private float longitud;

    public Terremoto(String titulo, float magnitud, float latitud, float longitud) {
        this.titulo = titulo;
        this.magnitud = magnitud;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
