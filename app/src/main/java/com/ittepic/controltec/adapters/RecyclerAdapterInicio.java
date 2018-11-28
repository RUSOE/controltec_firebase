package com.ittepic.controltec.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittepic.controltec.R;
import com.ittepic.controltec.utilidades.Categorias;
import com.ittepic.controltec.utilidades.Practica;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterInicio extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Manejar datos
    private Context mContext; //Para acceder a los recursos R
    ArrayList<Categorias> mCategorias = new ArrayList<Categorias>();

    private ClickItemListenerHome mListener;

    public RecyclerAdapterInicio(Context c, ArrayList<Categorias> categorias,ClickItemListenerHome listener) {
        this.mContext = c;
        this.mCategorias = categorias;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return vista_categoria.create(mContext, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        vista_categoria.bind(holder, mCategorias.get(position),mContext,mListener);
    }

    @Override
    public int getItemCount() {
        return mCategorias == null ? 0 : mCategorias.size();
    }

}

class vista_categoria extends RecyclerView.ViewHolder{


    //Vistas de cada item
    public TextView textViewTituloCategoria;
    public TextView textViewNumerodePracticas;
    public ImageView imageIconoCategoria;

    public Button btnAbrirCategoria;


    public vista_categoria(View v)
     {
         super(v);
         textViewTituloCategoria = (TextView)v.findViewById(R.id.textView_categoria);
         textViewNumerodePracticas = (TextView)v.findViewById(R.id.textView_numerodepracticas);
         imageIconoCategoria = (ImageView)v.findViewById(R.id.imageView_categoria_icono);
         btnAbrirCategoria = (Button)v.findViewById(R.id.button_abrircategoria);
     }



    public static vista_categoria create(Context c, ViewGroup parent)
    {
        return new vista_categoria(LayoutInflater.from(c).inflate(R.layout.categoria_item,parent,false));
    }

    public static void bind(RecyclerView.ViewHolder holder, final Categorias categorias, final Context c, final ClickItemListenerHome mListener){
        //Set holder here
        vista_categoria v = (vista_categoria) holder;
        v.textViewTituloCategoria.setText(categorias.getTipo());
        v.textViewNumerodePracticas.setText("Practicas : "+ categorias.getNumPracticas());

        Picasso.get().load(categorias.getUrlicon()).into(v.imageIconoCategoria);

        v.btnAbrirCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.ButtonViewOnClick(view,categorias.getTipo());
            }
        });
    }
}

