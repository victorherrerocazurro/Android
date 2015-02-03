package com.example.tarde.adapters;

import java.io.Serializable;

/**
 * Created by tarde on 03/02/2015.
 */
public class Pelicula implements Serializable {
    private String titulo;
    private int año;
    private String[] actores;

    public Pelicula() {
    }

    public Pelicula(String titulo, int año, String[] actores) {
        this.titulo = titulo;
        this.año = año;
        this.actores = actores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String[] getActores() {
        return actores;
    }

    public void setActores(String[] actores) {
        this.actores = actores;
    }
}
