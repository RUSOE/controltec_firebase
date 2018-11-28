package com.ittepic.controltec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittepic.controltec.utilidades.ActualizarPracticas;
import com.ittepic.controltec.utilidades.Alumno;
import com.ittepic.controltec.utilidades.DialogoConfirmar;
import com.ittepic.controltec.utilidades.Practica;
import com.ittepic.controltec.utilidades.constantes;
import com.squareup.picasso.Picasso;

public class ActivityDetallesPractica extends AppCompatActivity {

    Intent mIntent;
    private static final String TAG = ActivityDetallesPractica.class.getSimpleName();
    private Context mContext;
    private Practica mPractica;

    private TextView mDetalles,mLinks;
    private ImageView mImagenDetalles;
    private String mTextLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_practica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = ActivityDetallesPractica.this;
        mIntent = getIntent();
        ActualizarPracticas practicas = new ActualizarPracticas(mIntent.getExtras().getString(constantes.STRING_TIPO_PRACTICA));
        mPractica = practicas.getPracticaById(Integer.valueOf(mIntent.getExtras().getString(constantes.STRING_ID_PRACTICA))-1);

        Log.d(TAG,"Practica obtenida " + mPractica.getTitle()+"\n tipo: "+ mPractica.getTipo());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(constantes.STRING_KEY_SETTINGS,MODE_PRIVATE);
                Alumno a = new Alumno(sharedPreferences.getString(constantes.SETTINGS_NAME, ""),
                                sharedPreferences.getString(constantes.SETTINGS_CARRERA, ""),
                                sharedPreferences.getInt(constantes.SETTINGS_NUM, 0),
                                sharedPreferences.getInt(constantes.SETTINGS_SEMESTRE,0),
                                sharedPreferences.getString(constantes.SETTINGS_CORREO, ""));
                new DialogoConfirmar(mContext,mPractica,a);
            }
        });

        mDetalles = (TextView)findViewById(R.id.detalles);
        mLinks=(TextView)findViewById(R.id.textView_Links);
        mImagenDetalles = (ImageView)findViewById(R.id.imagen_detalle) ;


        Picasso.get().load(mPractica.getFoto()).into(mImagenDetalles);


        String[] links = mPractica.getLinks().split(constantes.STRING_PARSER);
        mDetalles.setText(mPractica.getDescripcion());

        for (int i = 0; i < links.length ;i++){
            if(links[i]!=null) {
                mTextLinks = mTextLinks + " <a href=\"" + links[i] + "\">" + links[i] + "</a> \n";
            }
        }
        mLinks.setText(Html.fromHtml(mTextLinks));
        mLinks.setLinksClickable(true);
        mLinks.setMovementMethod (LinkMovementMethod.getInstance());

       getSupportActionBar().setTitle(mPractica.getTitle());
    }
}
