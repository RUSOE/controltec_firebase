package com.ittepic.controltec.utilidades;

public class Categorias {

    String mTipo;
    String urlicon;
    int numPracticas;

    public Categorias(String tipo,String urlicon,int numeroPracticas) {
        this.setNumPracticas(numeroPracticas);
        this.setTipo(tipo);
        this.setUrlicon(urlicon);
    }

    public String getTipo() {
        return this.mTipo;
    }

    public void setTipo(String mTipo) {
        this.mTipo = mTipo;
    }

    public String getUrlicon() {
        return this.urlicon;
    }

    public void setUrlicon(String urlicon) {
        this.urlicon = urlicon;
    }

    public int getNumPracticas() {
        return this.numPracticas;
    }

    public void setNumPracticas(int numPracticas) {
        this.numPracticas = numPracticas;
    }


}