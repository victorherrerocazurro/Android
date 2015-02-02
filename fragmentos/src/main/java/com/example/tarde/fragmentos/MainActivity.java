package com.example.tarde.fragmentos;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListadoFragment fragmentoListado;

    private DetalleFragment fragmentoDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        fragmentoListado = (ListadoFragment) fragmentManager
                .findFragmentById(R.id.fragmentoListado);

        fragmentoDetalle = (DetalleFragment) fragmentManager
                .findFragmentById(R.id.fragmentoDetalle);
    }

    //Definimos el registro del Listener en el onResume, para
    //evitar un nullPointer, que se produce cuando se registra
    //en el oncreate, ya que todavia no existe el atributo de
    //clase listado del fragmentoListado
    @Override
    protected void onResume() {
        super.onResume();
        //Definir el listener para el onclick sobre la lista
        fragmentoListado.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CorreoElectronico item = (CorreoElectronico) parent.getItemAtPosition(position);

        if (fragmentoDetalle != null){
            //Estamos en un tablet
            fragmentoDetalle.actualizarDetalle(item);
        } else {
            //Estasmos en un smartphone
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra(DetalleActivity.KEY_CORREO_ITEM, item);
            startActivity(intent);
        }
    }
}
