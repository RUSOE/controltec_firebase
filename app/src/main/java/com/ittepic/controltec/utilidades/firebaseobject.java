package com.ittepic.controltec.utilidades;

public class firebaseobject {

    public Alumno mAlumno;
    public Practica mPractica;
    public String mDetalles;

    public firebaseobject(Alumno a, Practica p, String detalles){
        this.mAlumno = a;
        this.mPractica = p;
        this.mDetalles = detalles;
    }
    public firebaseobject(Alumno a,String detalles){
        this.mAlumno = a;
        this.mDetalles = detalles;
    }

    public Alumno getmAlumno() {
        return mAlumno;
    }

    public void setmAlumno(Alumno mAlumno) {
        this.mAlumno = mAlumno;
    }

    public Practica getmPractica() {
        return mPractica;
    }

    public void setmPractica(Practica mPractica) {
        this.mPractica = mPractica;
    }

    public String getmDetalles() {
        return mDetalles;
    }

    public void setmDetalles(String mDetalles) {
        this.mDetalles = mDetalles;
    }
}
