package com.example.tarde.servicioremoto;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class RemoteService extends Service {
    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Messenger messenger = new Messenger(new RemoteHandler());
        return messenger.getBinder();
    }

    class RemoteHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 0:
                    String mensaje = msg.getData().getString("saludo");
                    saludo(mensaje);
                    break;
                case 1:
                    break;
            }
        }
    }

    public void saludo(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
