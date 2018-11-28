package com.ittepic.controltec.utilidades;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ittepic.controltec.MainActivity;
import com.ittepic.controltec.R;
import com.ittepic.controltec.RegistrodePracticaActivity;

public class DialogoAviso {

    public DialogoAviso(final Context c) {
        final View bottomSheetLayout = LayoutInflater.from(c).inflate(R.layout.bottomsheetdialogconfirmar_layout, null);
        TextView mDetalles =(TextView) bottomSheetLayout.findViewById(R.id.textView_Detalles_BottomSheet);
        TextView mTitulo = (TextView) bottomSheetLayout.findViewById(R.id.textView_titulo_BottomSheet);

        String titulo = "Actualiza tus datos para poder usar la Aplicacion."; //+ practica.getTitle() + " : "+ practica.getTipo();


        final String body = "De esta manera no tendras que reescribir tus datos cada que realices un registro. ";
        (bottomSheetLayout.findViewById(R.id.button_confirmar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // se envia el correo y se actualiza la nube
                Intent i = new Intent(c,RegistrodePracticaActivity.class);
                i.putExtra(constantes.STRING_EXTRA_MENSAJE_CARDVIEW,constantes.MENSAJE_NEGATIVO);
                c.startActivity(i);
            }
        });


        Button mBtn = bottomSheetLayout.findViewById(R.id.button_editarenvio);
        mBtn.setVisibility(View.GONE);
        mDetalles.setText(body);
        mTitulo.setText(titulo);
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(c);
        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.show();
    }

}
