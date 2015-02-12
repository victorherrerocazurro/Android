package com.example.tarde.googlemaps;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_TERREMOTO = "terremoto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Funcionalidad para recoger de la intencion el terremoto
        /*Terremoto terremoto = (Terremoto) getIntent().getSerializableExtra(EXTRA_TERREMOTO);

        if(terremoto == null){
            finish();
        }*/

        Terremoto terremoto = new Terremoto("Terremoto en Santiago", 1.3f, 42.862130f, -8.554913f);

        TerremotoMapFragment mapFragment = (TerremotoMapFragment) getFragmentManager().findFragmentById(R.id.map);

        if(mapFragment != null){
            mapFragment.dibujarTerremoto(terremoto);
        }

        /*Fragment detalleFragment = getFragmentManager().findFragmentById(R.id.detalle);

        if(detalleFragment != null){
            detalleFragment.dibujeTerremoto(terremoto);
        }*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
