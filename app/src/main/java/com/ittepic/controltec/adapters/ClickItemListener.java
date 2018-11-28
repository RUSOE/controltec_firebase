package com.ittepic.controltec.adapters;

import android.view.View;

import com.ittepic.controltec.utilidades.Practica;

public interface ClickItemListener {
    void ImageViewOnClick(View v, int position,String tipo);

    void ConfirmarOnClick(View v, int position,Practica practica);
}