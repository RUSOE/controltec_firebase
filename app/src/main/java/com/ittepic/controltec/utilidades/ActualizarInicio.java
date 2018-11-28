package com.ittepic.controltec.utilidades;

import java.util.ArrayList;

public class ActualizarInicio {

    ArrayList<Categorias> mCategorias = new ArrayList<Categorias>();


    public ActualizarInicio(ArrayList<Categorias> mCategorias) {
        this.mCategorias = mCategorias;
    }


    public ActualizarInicio(){

        Categorias a = new Categorias(constantes.CATEGORIA_NEUMATICA_BASICA,
                                        constantes.ICONO_NEUMATICA_BASICA,
                                        new ActualizarPracticas(constantes.CATEGORIA_NEUMATICA_BASICA).getSize());

        Categorias b = new Categorias(constantes.CATEGORIA_NEUMATICA_AVANZADA,
                                        constantes.ICONO_NEUMATICA_AVANZADA,
                                        new ActualizarPracticas(constantes.CATEGORIA_NEUMATICA_AVANZADA).getSize());

        Categorias c = new Categorias(constantes.CATEGORIA_ELECTRONEUMATICA_BASICA,
                                        constantes.ICONO_ELECTRONEUMATICA_BASICA,
                                        new ActualizarPracticas(constantes.CATEGORIA_ELECTRONEUMATICA_BASICA).getSize());

        Categorias d = new Categorias(constantes.CATEGORIA_ELECTRONEUMATICA_AVANZADA,
                                        constantes.ICONO_ELECTRONEUMATICA_AVANZADA,
                                        new ActualizarPracticas(constantes.CATEGORIA_ELECTRONEUMATICA_AVANZADA).getSize());

        Categorias e = new Categorias(constantes.CATEGORIA_HIDRAULICA_BASICA,
                                        constantes.ICONO_HIDRAULICA_BASICA,
                                        new ActualizarPracticas(constantes.CATEGORIA_HIDRAULICA_BASICA).getSize());

        Categorias f = new Categorias(constantes.CATEGORIA_HIDRAULICA_AVANZADA,
                                        constantes.ICONO_HIDRAULICA_AVANZADA,
                                        new ActualizarPracticas(constantes.CATEGORIA_HIDRAULICA_AVANZADA).getSize());

        Categorias g = new Categorias(constantes.CATEGORIA_ELECTROHIDRAULICA_BASICA,
                                        constantes.ICONO_ELECTROHIDRAULICA_BASICA,
                                        new ActualizarPracticas(constantes.CATEGORIA_ELECTROHIDRAULICA_BASICA).getSize());

        Categorias h = new Categorias(constantes.CATEGORIA_ELECTROHIDRAULICA_AVANZADA,
                                        constantes.ICONO_ELECTROHIDRAULICA_AVANZADA,
                                        new ActualizarPracticas(constantes.CATEGORIA_ELECTROHIDRAULICA_AVANZADA).getSize());

        Categorias i = new Categorias(constantes.CATEGORIA_ELECTROHIDRAULICA_PROPORCIONAL,
                                        constantes.ICONO_ELECTROHIDRAULICA_PROPORCIONAL,
                                        new ActualizarPracticas(constantes.CATEGORIA_ELECTROHIDRAULICA_PROPORCIONAL).getSize());


        this.mCategorias.add(a);    //Neumatica basica
        this.mCategorias.add(b);    //Neumatica avanzada
        this.mCategorias.add(c);    //Electroneumatica basica
        this.mCategorias.add(d);    //Electroneumatica avanzada
        this.mCategorias.add(e);    //Hidraulica basica
        this.mCategorias.add(f);    //Hidraulica avanzada
        this.mCategorias.add(g);    //Electrohidraulica basica
        this.mCategorias.add(h);    //Electrohidraulica avanzada
        this.mCategorias.add(i);    //Electrohidraulica proporcional

    }

    public ArrayList<Categorias> getCategorias() {
        return this.mCategorias;
    }

    public int getSize(){return this.mCategorias.size();}
}

