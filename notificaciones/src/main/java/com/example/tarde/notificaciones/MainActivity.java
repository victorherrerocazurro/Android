package com.example.tarde.notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Notification.Builder builderBigPicture;
    private Notification.Builder builderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builderBigPicture = new Notification.Builder(this);

        Intent intent = new Intent(this,MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_NO_CREATE);

        builderBigPicture.setTicker("Aviso")
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .setContentTitle("Notificación de aviso")
                .setContentText("Descripcion de la notificación")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_dialog_dialer))
                .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_dialog_dialer)))
                //.setStyle(new Notification.BigTextStyle().bigText("Descripcion de la notificación"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        builderProgress = new Notification.Builder(this);

        builderProgress
                .setTicker("Progreso")
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .setContentTitle("Cargando");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    int i = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notificacion_bigPicture) {
            notificationManager.notify(1, builderBigPicture.build());
            return true;
        } else if(id == R.id.action_notificacion_progress){
            builderProgress.setProgress(100, i, false);
            i+=10;
            Notification notification = builderProgress.build();

            notificationManager.notify(2, notification);
            return true;
        } else if(id == R.id.action_cancel_notificacion_progress){
            notificationManager.cancel(2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
