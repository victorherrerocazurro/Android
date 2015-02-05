package com.example.tarde.serviciolocal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MainService extends Service {
    public MainService() {
    }

    //Responden a eventos que se producen desde el cliente
    //Siendo los eventos START, STOP, BIND


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Arrancado el servicio!",Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        MainBinder binder = new MainBinder(this);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"Desenlazado el servicio!",Toast.LENGTH_LONG).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Destruido el servicio!",Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    //A traves del Binder, tenemos acceso a las funcionalidades del servicio

    public void saludo(){
        Toast.makeText(this,"Hola Mundo!",Toast.LENGTH_LONG).show();
    }
}
