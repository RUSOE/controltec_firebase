package com.ittepic.controltec.utilidades;

public class Alumno {
    String mNombre;
    String mCarrera;
    int mNumeroDeControl;
    int mSemestre;
    String mCorreoInstitucional;

    public Alumno(String mNombre, String mCarrera, int mNumeroDeControl, int mSemestre, String mCorreoInstitucional) {
        this.mNombre = mNombre;
        this.mCarrera = mCarrera;
        this.mNumeroDeControl = mNumeroDeControl;
        this.mSemestre = mSemestre;
        this.mCorreoInstitucional = mCorreoInstitucional;
    }

    public String getNombre() {
        return this.mNombre;
    }

    public void setNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public String getCarrera() {
        return this.mCarrera;
    }

    public void setCarrera(String mCarrera) {
        this.mCarrera = mCarrera;
    }

    public int getNumeroDeControl() {
        return this.mNumeroDeControl;
    }

    public void setNumeroDeControl(int mNumeroDeControl) {
        this.mNumeroDeControl = mNumeroDeControl;
    }

    public int getSemestre() {
        return this.mSemestre;
    }

    public void setSemestre(int mSemestre) {
        this.mSemestre = mSemestre;
    }

    public String getCorreoInstitucional() {
        return this.mCorreoInstitucional;
    }

    public void setCorreoInstitucional(String mCorreoInstitucional) {
        this.mCorreoInstitucional = mCorreoInstitucional;
    }
}
