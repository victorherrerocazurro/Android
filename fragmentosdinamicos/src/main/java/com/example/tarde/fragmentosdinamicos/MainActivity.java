package com.example.tarde.fragmentosdinamicos;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction tx = getFragmentManager().beginTransaction();

        tx.add(R.id.contenedor, new BuscadorFragment());

        tx.commit();

    }
}
