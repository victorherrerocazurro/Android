package com.example.tarde.menucontextual;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private PeliculasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Pelicula> lista = new LinkedList<Pelicula>();
        lista.add(new Pelicula("Lo que el viento se llevo", 1939, new String[]{"Viviang Leigth", "Clark Gable"}));
        lista.add(new Pelicula("Titanic", 1997, new String[]{"Leonardo DiCaprio", "Kate Winslet"}));

        ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new PeliculasAdapter(this, R.layout.list_item_pelicula, lista);

        listView.setAdapter(adapter);

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        switch (v.getId()){
            case R.id.listView:
                //Queremos escribir en la cabecera algo que represente al item.
                int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
                Adapter adapter = ((AdapterView) v).getAdapter();
                menu.setHeaderTitle(adapter.getItem(position).toString());
                getMenuInflater().inflate(R.menu.menu_listview, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;

        switch (item.getItemId()){
            case R.id.action_add:
                //Logica que da de alta un nuevo elemento
                Intent intent = new Intent(this, NuevaPeliculaActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.action_edit:
                //Logica que edita
                Intent intent1 = new Intent(this, NuevaPeliculaActivity.class);
                intent1.putExtra("position",position);
                startActivityForResult(intent1, 2);
                break;
            case R.id.action_remove:
                adapter.removePelicula(position);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Pelicula pelicula = (Pelicula) data.getSerializableExtra("pelicula");
            int position = data.getIntExtra("position", -1);

            switch (requestCode) {
                case 1:
                    adapter.addPelicula(pelicula);
                    break;
                case 2:
                    adapter.editPelicula(position, pelicula);
                    break;
            }
        }
    }
}
