package com.example.tarde.serviciolocal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private MainBinder mainBinder;
    private MainServiceConnection conn = new MainServiceConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this,MainService.class);

        switch (item.getItemId()){
            case R.id.action_start_service:
                startService(intent);
                break;
            case R.id.action_bind_service:
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.action_unbind_service:
                unbindService(conn);
                break;
            case R.id.action_hi_service:
                if(mainBinder != null) {
                    mainBinder.getService().saludo();
                }
                break;
            case R.id.action_stop_service:
                stopService(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class MainServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mainBinder = (MainBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mainBinder = null;
        }
    }
}
