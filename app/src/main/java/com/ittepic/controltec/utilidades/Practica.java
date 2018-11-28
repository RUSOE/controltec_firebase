package com.ittepic.controltec.utilidades;

import java.util.ArrayList;

public class Practica {
    private String mLinks;
    public String mTitulo;
    public String mFoto;
    public String mDescripcion;
    public String mTipo;
    public int mId;


    public Practica()
    {
        //Constructor default
    }

    public Practica(String titulo, String UrlFoto, String descripcion, String tipo, int identificadorpractica, String links)
    {
        this.mTitulo = titulo;
        this.mFoto = UrlFoto;
        this.mDescripcion = descripcion;
        this.mTipo = tipo;
        this.mId = identificadorpractica;
        this.mLinks = links;
    }
    public String getTitle() {
        return this.mTitulo;
    }

    public void setTitle(String mTitle) {
        this.mTitulo = mTitle;
    }

    public String getFoto() {
        return this.mFoto;
    }

    public void setFoto(String mFoto) {
        this.mFoto = mFoto;
    }

    public String getDescripcion() {return this.mDescripcion; }

    public void setDescripcion(String mDescripcion) {this.mDescripcion = mDescripcion; }

    public String getTipo() {return this.mTipo;   }

    public void setTipo(String mTipo) { this.mTipo = mTipo;    }

    public int getId() { return this.mId; }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getLinks() {
        return this.mLinks;
    }

    public void setLinks(String mLinks) {
        this.mLinks = mLinks;
    }
}
