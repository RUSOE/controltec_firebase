package com.ittepic.controltec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ittepic.controltec.adapters.ClickItemListenerHome;
import com.ittepic.controltec.adapters.RecyclerAdapterInicio;
import com.ittepic.controltec.utilidades.ActualizarInicio;
import com.ittepic.controltec.utilidades.ActualizarPracticas;
import com.ittepic.controltec.utilidades.DialogoAviso;
import com.ittepic.controltec.utilidades.DialogoConfirmar;
import com.ittepic.controltec.utilidades.constantes;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView mProfilePicture;
    ActualizarInicio mList;

    RecyclerView mRecyclerInicio;
    LinearLayoutManager mManager;

    Context mContext = MainActivity.this;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esta funcion inicializa todas las vistas
        initViews();

        //Logica de Recycler view
        mManager = new LinearLayoutManager(mContext);
        mList = new ActualizarInicio();
        for (int i = 0; i<= mList.getSize()-1; i++) { Log.d(TAG, mList.getCategorias().get(i).getNumPracticas()+""); }
        mRecyclerInicio.setLayoutManager(mManager);
        mRecyclerInicio.setAdapter(new RecyclerAdapterInicio(mContext, mList.getCategorias(), new ClickItemListenerHome() {
            @Override
            public void ButtonViewOnClick(View v, String tipo) {
                Log.d(TAG,"tipo "+tipo);
                Intent i = new Intent(mContext,Main2Activity.class);
                i.putExtra(constantes.STRING_EXTRA_CATEGORIA,tipo);
                startActivity(i);
            }
        }));
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").centerCrop().into(mProfilePicture);

        SharedPreferences sharedPreferences = getSharedPreferences(constantes.STRING_KEY_SETTINGS,MODE_PRIVATE);
        String s = sharedPreferences.getString(constantes.SETTINGS_NAME, "null");
        if(s=="null")
        {
            Context c = MainActivity.this;
            new DialogoAviso(c);
        }
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mProfilePicture = (ImageView) findViewById(R.id.imageViewProfile);
        mRecyclerInicio = (RecyclerView)findViewById(R.id.recyclerView_inicio);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_enviar) {
            // Handle the camera action
        } else if (id == R.id.menu_compartir) {

        }else if (id == R.id.menu_preferencias) {

            Intent i = new Intent(mContext,RegistrodePracticaActivity.class);
            i.putExtra(constantes.STRING_EXTRA_MENSAJE_CARDVIEW,constantes.MENSAJE_NEGATIVO);
            startActivity(i);

        }else if (id == R.id.menu_youtube) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
