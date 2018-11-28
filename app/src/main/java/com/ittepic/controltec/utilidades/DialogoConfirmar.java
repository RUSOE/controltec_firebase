package com.ittepic.controltec.utilidades;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittepic.controltec.MainActivity;
import com.ittepic.controltec.R;
import com.ittepic.controltec.RegistrodePracticaActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class DialogoConfirmar {


    public Alumno mAlumno;
    private firebaseobject mFirebaseObject;

    public DialogoConfirmar(final Context c, final Practica practica,Alumno alumno) {
        final
        View bottomSheetLayout = LayoutInflater.from(c).inflate(R.layout.bottomsheetdialogconfirmar_layout, null);
        TextView mDetalles =(TextView) bottomSheetLayout.findViewById(R.id.textView_Detalles_BottomSheet);
        TextView mTitulo = (TextView) bottomSheetLayout.findViewById(R.id.textView_titulo_BottomSheet);

        String titulo = "Solicitud para registrar practica"; //+ practica.getTitle() + " : "+ practica.getTipo();

        mAlumno = alumno;
        mFirebaseObject =  new firebaseobject(alumno,practica,getDetalles(alumno));
        final String body = "Practica a realizar: "+practica.getTitle()+"\n"+"\n"+"Tecnologia: "+practica.getTipo();
        (bottomSheetLayout.findViewById(R.id.button_confirmar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // se envia el correo y se actualiza la nube
                mailTo(practica, body, c,mAlumno);
            }
        });

        SharedPreferences sharedPreferences = c.getSharedPreferences(constantes.STRING_KEY_SETTINGS,MODE_PRIVATE);
        String s = sharedPreferences.getString(constantes.SETTINGS_NAME, "null");
        if(s=="null")
        {
            Button b = bottomSheetLayout.findViewById(R.id.button_confirmar);
            b.setVisibility(View.GONE);
        }

        (bottomSheetLayout.findViewById(R.id.button_editarenvio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(c,RegistrodePracticaActivity.class);
               i.putExtra(constantes.STRING_EXTRA_MENSAJE_CARDVIEW,constantes.MENSAJE_POSITIVO);
               i.putExtra(constantes.STRING_MENSAJE,body);
               c.startActivity(i);
            }
        });


        mDetalles.setText(body);
        mTitulo.setText(titulo);
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(c);
        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.show();
    }


    private void mailTo(Practica practica, String body, Context c,Alumno alumno) {
        SendFirebase(new firebaseobject(alumno,practica,getDetalles(alumno)));
        String mailto = "mailto:"+ constantes.CORREO_ENCARGADO +
                "?cc=" + alumno.getCorreoInstitucional() +
                "&subject=" + Uri.encode("SOLICITUD DE LABORATORIO "+alumno.getNumeroDeControl()) +
                "&body=" + Uri.encode(body + getDetalles(alumno));

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            c.startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
    }

    public static void mailFromEdited(String body, Context c,Alumno alumno) {

        SendFirebase(new firebaseobject(alumno,getDetalles(alumno)));
        String mailto = "mailto:"+ constantes.CORREO_ENCARGADO +
                "?cc=" + alumno.getCorreoInstitucional() +
                "&subject=" + Uri.encode("SOLICITUD DE LABORATORIO "+alumno.getNumeroDeControl()) +
                "&body=" + Uri.encode(body +
                getDetalles(alumno));

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            c.startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
    }

    private static void SendFirebase(firebaseobject fo){
        DatabaseReference solicituddatabase = FirebaseDatabase.getInstance().getReference(constantes.FIREBASE_KEY_SOLICITUDES);
        String id = solicituddatabase.push().getKey();
        solicituddatabase.child(id).setValue(fo);
    }

    @NonNull
    private static String getDetalles(Alumno alumno) {
        return "\n Detalles de solicitud : " +
                "\n Nombre: " + alumno.getNombre() +
                "\n Semestre : " + alumno.getSemestre() +
                "\n Numero de control : " + alumno.getNumeroDeControl() +
                getFechayHora();

    }

    public static String getFechayHora(){
        return "\n Hora :" + getHora() +
                "\n Fecha : " + getDia();
    }

    public static String getHora() {
        Date hora = new Date();
        String formato = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(formato);
        return dateFormat.format(hora);
    }

    public static String getDia(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
    }

}
