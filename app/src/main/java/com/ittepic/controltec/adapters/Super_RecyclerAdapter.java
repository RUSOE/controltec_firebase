package com.ittepic.controltec.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittepic.controltec.Main3Activity;
import com.ittepic.controltec.R;
import com.ittepic.controltec.utilidades.Practica;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Super_RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Manejar datos
    private Context mContext; //Para acceder a los recursos R
    private ArrayList<Practica> mPracticas;

    private ClickItemListener mListener;

    public void setOnItemClickListener(ClickItemListener listener){this.mListener=listener;}

    public Super_RecyclerAdapter(Context c, ArrayList<Practica> practicas,ClickItemListener listener) {
        this.mContext = c;
        this.mPracticas = practicas;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return vista_practica.create(mContext, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        vista_practica.bind(holder, mPracticas.get(position),mContext,mListener);
    }

    @Override
    public int getItemCount() {
        return mPracticas == null ? 0 : mPracticas.size();
    }

}


class vista_practica extends RecyclerView.ViewHolder{


    //Vistas de cada item
    public TextView textViewTituloPractica;

    public ImageView imageViewPractica;
    public TextView textViewTipoPractica;

    public Button btnAbrirPractica;
    public Button btnCompartir;


    public vista_practica(View v)
     {
         super(v);
         textViewTituloPractica = (TextView)v.findViewById(R.id.textView_tituloPractica);

         textViewTipoPractica = (TextView)v.findViewById(R.id.textView_tipoPractica);
         imageViewPractica = (ImageView)v.findViewById(R.id.imageView_Practica);
         btnAbrirPractica = (Button)v.findViewById(R.id.button_explore);
         btnCompartir = (Button)v.findViewById(R.id.button_share);
     }



    public static vista_practica create(Context c, ViewGroup parent)
    {
        return new vista_practica(LayoutInflater.from(c).inflate(R.layout.practica_item,parent,false));
    }

    public static void bind(RecyclerView.ViewHolder holder, final Practica practica, final Context c, final ClickItemListener mListener){
        //Set holder here
        vista_practica v = (vista_practica)holder;
        v.textViewTituloPractica.setText(practica.getTitle());

        v.textViewTipoPractica.setText(practica.getTipo());
        Picasso.get().load(practica.getFoto()).into(v.imageViewPractica);

        v.btnAbrirPractica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.ImageViewOnClick(view,practica.mId,practica.getTipo());
            }
        });

        v.btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.ConfirmarOnClick(view,practica.mId,practica);
            }
        });
    }
}

