package com.example.tarde.serviciolocal;

import android.app.Service;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by tarde on 05/02/2015.
 */
public class MainBinder extends Binder {

    private MainService service;

    public MainBinder(MainService service) {
        this.service = service;
    }

    public MainService getService(){
       return service;
    }
}
