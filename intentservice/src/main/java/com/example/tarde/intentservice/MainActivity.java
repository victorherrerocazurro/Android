package com.example.tarde.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String nombre = intent.getStringExtra(SaludadorIntentService.EXTRA_PARAM_NOMBRE);

                switch (intent.getAction()){
                    case SaludadorIntentService.BROADCAST_ACTION_SALUDAR:
                        Toast.makeText(context, "Hola " + nombre + "!!!!", Toast.LENGTH_SHORT).show();
                        break;
                    case SaludadorIntentService.BROADCAST_ACTION_DESPEDIR:
                        Toast.makeText(context, "Adios " + nombre + "!!!!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(SaludadorIntentService.BROADCAST_ACTION_SALUDAR);
        intentFilter.addAction(SaludadorIntentService.BROADCAST_ACTION_DESPEDIR);

        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        registerReceiver(receiver,intentFilter);

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

        if (id == R.id.action_saludar) {
            SaludadorIntentService.startActionSaludar(this,"Victor");
            return true;
        } else if (id == R.id.action_despedir) {
            SaludadorIntentService.startActionDespedir(this,"Victor");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
