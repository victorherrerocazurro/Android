package com.example.tarde.menucontextual;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class NuevaPeliculaActivity extends ActionBarActivity implements View.OnClickListener{

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_pelicula);

        position = getIntent().getIntExtra("position",-1);

        // TODO gestionar la persistencia de la posicion del item editado, para retornarlo
        // al finalizar la edicion
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_pelicula, menu);
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("pelicula", new Pelicula());// TODO Leer los EditText de la Vista
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
