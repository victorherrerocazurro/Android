package com.example.tarde.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by tarde on 04/02/2015.
 */
public class DescargarImagenAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private ProgressDialog progreso;
    private ImageView destino;

    public DescargarImagenAsyncTask(ProgressDialog progreso, ImageView destino) {
        this.progreso = progreso;
        this.destino = destino;
    }

    //Unico metodo que se ejecuta en el hilo secundario

    @Override
    protected Bitmap doInBackground(String... params) {
        //Realizar la tarea de larga duracion
        try {
            URL url = new URL(params[0]);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            int tamañoImagen = con.getContentLength();
            byte[] imagen = new byte[tamañoImagen];
            byte[] buffer = new byte[1024];

            InputStream is = con.getInputStream();

            for(int bytesTotalesLeidos = 0; bytesTotalesLeidos < tamañoImagen; ){
                int bytesLeidos = is.read(buffer);

                System.arraycopy(buffer, 0, imagen, bytesTotalesLeidos, bytesLeidos);

                bytesTotalesLeidos += bytesLeidos;

                publishProgress(bytesTotalesLeidos*100/tamañoImagen);
            }

            return BitmapFactory.decodeByteArray(imagen, 0, tamañoImagen);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Metodos que se ejecutan en el hilo principal

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progreso.setProgress(0);
        progreso.show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(bitmap != null) {
            destino.setImageBitmap(bitmap);
        }

        progreso.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progreso.setProgress(values[0]);
    }
}
