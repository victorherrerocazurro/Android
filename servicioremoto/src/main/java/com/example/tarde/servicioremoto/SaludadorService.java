package com.example.tarde.servicioremoto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class SaludadorService extends Service {
    public SaludadorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        IServicioSaludador.Stub stub = new IServicioSaludador.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                /*Toast.makeText(
                        SaludadorService.this,
                        "El entero recibido es: " + anInt,
                        Toast.LENGTH_LONG
                ).show();*/
            }
        };
        return stub;
    }


}
