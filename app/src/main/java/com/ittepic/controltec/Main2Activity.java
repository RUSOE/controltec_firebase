package com.ittepic.controltec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ittepic.controltec.adapters.ClickItemListener;
import com.ittepic.controltec.adapters.Super_RecyclerAdapter;
import com.ittepic.controltec.utilidades.ActualizarPracticas;
import com.ittepic.controltec.utilidades.Alumno;
import com.ittepic.controltec.utilidades.DialogoConfirmar;
import com.ittepic.controltec.utilidades.Practica;
import com.ittepic.controltec.utilidades.constantes;

public class Main2Activity extends AppCompatActivity {

    private BottomSheetDialogFragment mDialogConfirmar;

    ActualizarPracticas mList;

    RecyclerView mVistaLista;
    LinearLayoutManager mManager;

    Context mContext = Main2Activity.this;
    Intent mIntent;

    public static final String TAG = Main2Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
        //Se crean las practicas
        View  bottomsheet = findViewById(R.id.framelayout_bottom_sheet);
        mIntent = getIntent();
        mList = new ActualizarPracticas(mIntent.getExtras().getString(constantes.STRING_EXTRA_CATEGORIA));
        //Debug para comprobar en la consola que se han creado las practicas
        for (int i = 0; i<= mList.getSize()-1; i++) { Log.d(Main2Activity.class.getSimpleName(), mList.getPracticas().get(i).mTitulo); }



        mVistaLista.setAdapter(new Super_RecyclerAdapter(mContext, mList.getPracticas(), new ClickItemListener() {
            @Override
            public void ImageViewOnClick(View v, int position, String tipo) {
                Log.d(TAG, "Imagen tocada de la posicion :  "+position);
                Intent i =  new Intent(Main2Activity.this,ActivityDetallesPractica.class);
                i.putExtra(constantes.STRING_ID_PRACTICA,position+"");
                i.putExtra(constantes.STRING_TIPO_PRACTICA,tipo);
                startActivity(i);
            }

            @Override
            public void ConfirmarOnClick(View v, int position, Practica practica) {
                SharedPreferences sharedPreferences = getSharedPreferences(constantes.STRING_KEY_SETTINGS,MODE_PRIVATE);
                Alumno a = new Alumno(sharedPreferences.getString(constantes.SETTINGS_NAME, ""),
                        sharedPreferences.getString(constantes.SETTINGS_CARRERA, ""),
                        sharedPreferences.getInt(constantes.SETTINGS_NUM, 0),
                        sharedPreferences.getInt(constantes.SETTINGS_SEMESTRE,0),
                        sharedPreferences.getString(constantes.SETTINGS_CORREO, ""));
                new DialogoConfirmar(mContext,practica,a);
            }
        }));
    }

    private void initViews()
    {
        //Se inician las vistas de esta activity aqui para solo manejar el control en las funciones principales
        mVistaLista = (RecyclerView) findViewById(R.id.recyclerView_practicas);
        mManager = new LinearLayoutManager(Main2Activity.this);
        mVistaLista.setLayoutManager(mManager);
    }
}
