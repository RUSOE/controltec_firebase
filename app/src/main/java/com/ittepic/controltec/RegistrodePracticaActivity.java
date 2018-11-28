package com.ittepic.controltec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ittepic.controltec.utilidades.Alumno;
import com.ittepic.controltec.utilidades.DialogoConfirmar;
import com.ittepic.controltec.utilidades.constantes;

public class RegistrodePracticaActivity extends AppCompatActivity {

    private EditText inputNombre, inputCarrera, inputNumControl, inputSemestre, inputCorreo, inputFecha;
    private TextInputLayout inputLayoutNombre, inputLayoutCarrera, inputLayoutNumControl,inputLayoutSemestre, inputLayoutCorreo, inputLayoutFecha;
    private Button btnConfirmar;

    private TextView mMensaje;
    private CardView mCardMessage;
    private Intent mIntent;
    private String mMensajeText;
    private static final String TAG = RegistrodePracticaActivity.class.getSimpleName();

    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrode_practica);
        this.c = RegistrodePracticaActivity.this;

        initViews();
        initTextListeners();
        setMensaje();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_registro);
        setSupportActionBar(toolbar);
        inputNombre = (EditText)findViewById(R.id.input_name);
        inputLayoutNombre = (TextInputLayout)findViewById(R.id.input_layout_name);
        inputCarrera = (EditText)findViewById(R.id.input_carrera);
        inputLayoutCarrera = (TextInputLayout)findViewById(R.id.input_layout_carrera);
        inputNumControl = (EditText)findViewById(R.id.input_numcontrol);
        inputLayoutNumControl = (TextInputLayout)findViewById(R.id.input_layout_numcontrol);
        inputSemestre = (EditText)findViewById(R.id.input_semestre);
        inputLayoutSemestre = (TextInputLayout)findViewById(R.id.input_layout_semestre);
        inputCorreo = (EditText)findViewById(R.id.input_email);
        inputLayoutCorreo = (TextInputLayout)findViewById(R.id.input_layout_email);
        inputFecha = (EditText)findViewById(R.id.input_fecha);
        inputLayoutFecha = (TextInputLayout)findViewById(R.id.input_layout_fecha);

        btnConfirmar=(Button)findViewById(R.id.btn_confirmar_formulario);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmar();
            }
        });

        mMensaje = (TextView)findViewById(R.id.textView_message_cardView);
        mCardMessage = (CardView)findViewById(R.id.card_message);

        mIntent = getIntent();

        //Fecha y Hora se generan por default
        inputFecha.setText(DialogoConfirmar.getFechayHora());

    }

    private void initTextListeners(){
        inputNombre.addTextChangedListener(new TextListener(inputNombre,inputLayoutNombre));
        inputCarrera.addTextChangedListener(new TextListener(inputCarrera,inputLayoutCarrera));
        inputNumControl.addTextChangedListener(new TextListener(inputNumControl,inputLayoutNumControl));
        inputSemestre.addTextChangedListener(new TextListener(inputSemestre,inputLayoutSemestre));
        inputCorreo.addTextChangedListener(new TextListener(inputCorreo,inputLayoutCorreo));
    }

    private void setMensaje(){

        String s = mIntent.getExtras().getString(constantes.STRING_EXTRA_MENSAJE_CARDVIEW);

        if (s != null) {
            if(s.equals(constantes.MENSAJE_POSITIVO)){
                mMensajeText = mIntent.getExtras().getString(constantes.STRING_MENSAJE);
                mCardMessage.setVisibility(View.VISIBLE);
                mMensaje.setText(mMensajeText);
            }else if(s.equals(constantes.MENSAJE_NEGATIVO)){
                mCardMessage.setVisibility(View.GONE);
            }
        }
    }

    private boolean validar(EditText editText, TextInputLayout inputLayout) {
        //Este metodo comprueba que la casilla de texto no se encuentre vacia
        if (editText.getText().toString().trim().isEmpty()) {
            inputLayout.setError(getString(R.string.mensaje_error));
            requestFocus(editText);
            return false;
        } else {
            inputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void confirmar() {
        String mNombre = inputNombre.getText().toString();
        String mCarrera = inputCarrera.getText().toString();
        String mCorreoInstitucional = inputCorreo.getText().toString();
        Integer mNumeroDeControl = Integer.valueOf(inputNumControl.getText().toString());
        Integer mSemestre = Integer.valueOf(inputSemestre.getText().toString());

        if (!validar(inputNombre, inputLayoutNombre)) {return; }
        if (!validar(inputCarrera, inputLayoutCarrera)) { return; }
        if (!validar(inputCorreo, inputLayoutCorreo)) { return;}
        if (!validar(inputSemestre, inputLayoutSemestre)) { return; }
        if (!validar(inputNumControl, inputLayoutNumControl)) { return; }

        String s = mIntent.getExtras().getString(constantes.STRING_EXTRA_MENSAJE_CARDVIEW);
        if (s != null) {
            if (s.equals(constantes.MENSAJE_POSITIVO)) {

                actualizardatos(mNombre, mCarrera, mCorreoInstitucional, mNumeroDeControl, mSemestre);
                //Se envia el correo en caso de estar en modo edicion

                DialogoConfirmar.mailFromEdited(mMensajeText,
                        RegistrodePracticaActivity.this,
                        new Alumno(mNombre,
                                mCarrera,
                                mNumeroDeControl,
                                mSemestre,
                                mCorreoInstitucional));
                Log.d(TAG, "Mensaje enviado");

            } else if (s.equals(constantes.MENSAJE_NEGATIVO)) {
                //se actualizan los datos del alumno
                actualizardatos(mNombre, mCarrera, mCorreoInstitucional, mNumeroDeControl, mSemestre);
                Log.d(TAG, "Datos Actualizados");
                Toast.makeText(c,"Datos actualizados",Toast.LENGTH_LONG).show();
            }
        }

    }

    private void actualizardatos(String mNombre, String mCarrera, String mCorreoInstitucional, Integer mNumeroDeControl, Integer mSemestre) {
        SharedPreferences preferences = getSharedPreferences(constantes.STRING_KEY_SETTINGS,c.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(constantes.SETTINGS_NAME, mNombre);
        editor.putInt(constantes.SETTINGS_NUM, mNumeroDeControl);
        editor.putString(constantes.SETTINGS_CARRERA, mCarrera);
        editor.putInt(constantes.SETTINGS_SEMESTRE, mSemestre);
        editor.putString(constantes.SETTINGS_CORREO, mCorreoInstitucional);
        editor.apply();
    }

    private class TextListener implements TextWatcher {

            private EditText mEditText;
            private TextInputLayout mTextInputLayout;

            TextListener(EditText editText, TextInputLayout textInputLayout)
            {
                this.mEditText = editText;
                this.mTextInputLayout = textInputLayout;
            }
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void afterTextChanged(Editable editable) {
               validar(mEditText,mTextInputLayout);
            }
        }

    }