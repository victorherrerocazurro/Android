package com.example.tarde.contentresolver_clientecontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tarde.noticiasutils.NoticiasProviderUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver contentResolver = getContentResolver();

        ContentValues values = new ContentValues();

        values.put(NoticiasProviderUtil.CAMPO_ID, "12abc");
        values.put(NoticiasProviderUtil.CAMPO_TITULO, "Extra Extra!!");
        values.put(NoticiasProviderUtil.CAMPO_CREADOR, "Victor");
        values.put(NoticiasProviderUtil.CAMPO_DESCRIPCION, "Nueva Noticia en la Base de Datos");
        values.put(NoticiasProviderUtil.CAMPO_FECHA, new Date().getTime());
        values.put(NoticiasProviderUtil.CAMPO_LINK, "http://www.google.es");
        values.put(NoticiasProviderUtil.CAMPO_CONTENIDO, "Esta es la primera noticia que tenemos para insertar en la Base de datos de noticias");

        Uri elementoInsertado = contentResolver.insert(NoticiasProviderUtil.NOTICIA_URI, values);

        Toast.makeText(this, elementoInsertado.toString(),Toast.LENGTH_LONG).show();

        String[] projection = {NoticiasProviderUtil.CAMPO_ID,NoticiasProviderUtil.CAMPO_TITULO, NoticiasProviderUtil.CAMPO_CREADOR,
                NoticiasProviderUtil.CAMPO_DESCRIPCION, NoticiasProviderUtil.CAMPO_FECHA, NoticiasProviderUtil.CAMPO_LINK, NoticiasProviderUtil.CAMPO_CONTENIDO};

        Cursor cursor = contentResolver.query(elementoInsertado, projection, null, null, null);

        List<Noticia> noticias = cursorToNoticia(cursor);

        Toast.makeText(this,"Tamaño: " + noticias.size() + "| " + noticias.get(0), Toast.LENGTH_SHORT).show();

    }

    private List<Noticia> cursorToNoticia(Cursor cursor) {
        List<Noticia> resultado = new LinkedList<>();

        if(cursor.moveToFirst()){
            do{
                Noticia noticia = new Noticia();
                if(cursor.getColumnIndex(Noticia.CAMPO_ID) != -1) {
                    noticia.setId(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_ID)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_TITULO) != -1) {
                    noticia.setTitulo(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_TITULO)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_CREADOR) != -1) {
                    noticia.setCreador(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_CREADOR)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_DESCRIPCION) != -1) {
                    noticia.setDescripcion(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_DESCRIPCION)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_LINK) != -1) {
                    noticia.setLink(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_LINK)));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_FECHA) != -1) {
                    noticia.setFechaPublicacion(new Date(cursor.getLong(cursor.getColumnIndex(Noticia.CAMPO_FECHA))));
                }
                if(cursor.getColumnIndex(Noticia.CAMPO_CONTENIDO) != -1) {
                    noticia.setContenido(cursor.getString(cursor.getColumnIndex(Noticia.CAMPO_CONTENIDO)));
                }
                resultado.add(noticia);
            }while (cursor.moveToNext());
        }

        return resultado;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
