package com.example.tarde.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Advertencia")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNeutralButton("Aceptar",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Se acepto",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

        View actividad = findViewById(R.id.btActividad);

        actividad.setOnClickListener(this);

        View fragmento = findViewById(R.id.btFragmento);

        fragmento.setOnClickListener(this);

        View personalizado = findViewById(R.id.btPersonalizado);

        personalizado.setOnClickListener(this);

        View selecionMultiple = findViewById(R.id.btSeleccionMultiple);

        selecionMultiple.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btActividad:
                showDialog(1);
                break;
            case R.id.btFragmento:
                AdvertenciaDialogFragment dialogFragment = new AdvertenciaDialogFragment();
                dialogFragment.show(getFragmentManager(),"Dialogo");
                break;
            case R.id.btPersonalizado:
                PersonalizadoDialogFragment personalizadoDialogFragment = new PersonalizadoDialogFragment();
                personalizadoDialogFragment.show(getFragmentManager(),"Personalizado");
                break;
            case R.id.btSeleccionMultiple:
                SeleccionMultipleDialogFragment seleccionMultipleDialogFragment = new SeleccionMultipleDialogFragment();
                seleccionMultipleDialogFragment.show(getFragmentManager(),"Multiple");
                break;
        }
    }
}
