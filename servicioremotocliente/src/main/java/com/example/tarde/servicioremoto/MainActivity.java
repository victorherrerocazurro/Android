package com.example.tarde.servicioremoto;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Messenger messenger;
    private IServicioSaludador servicioSaludador;

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

        switch (item.getItemId()){
            case R.id.action_bind_service:
                Intent intent = new Intent("com.example.tarde.servicioremoto.SALUDO");
                bindService(intent,new RemoteServiceConnection(), Context.BIND_AUTO_CREATE);
                return true;
            case R.id.action_hi_service:
                Message msg = Message.obtain(null,0);
                Bundle bundle = new Bundle();
                bundle.putString("saludo", "Hola Mundo en remoto!!!!!!");
                msg.setData(bundle);
                try {
                    messenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.action_bind_servicio_saludador:
                //Intent intent1 = new Intent("com.example.tarde.servicio.saludador.SALUDO");
                Intent intent1 = new Intent(this,IServicioSaludador.class);
                bindService(intent1,new SaludadorServiceConnection(), Context.BIND_AUTO_CREATE);
                return true;
            case R.id.action_saludar_servicio_saludador:
                try {
                    servicioSaludador.basicTypes(12,13l,true,12.4f,12.5,"Texto");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class RemoteServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    class SaludadorServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            servicioSaludador = IServicioSaludador.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
