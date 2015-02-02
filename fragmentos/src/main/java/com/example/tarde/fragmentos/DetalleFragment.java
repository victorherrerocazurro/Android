package com.example.tarde.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by tarde on 02/02/2015.
 */
public class DetalleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View guardar = getActivity().findViewById(R.id.btGuardar);

        View cancelar = getActivity().findViewById(R.id.btCancelar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO implementar cuando se expliquen los Adapter
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO implementar cuando se expliquen los Adapter
            }
        });
    }

    public void actualizarDetalle(CorreoElectronico item) {

        EditText remitente = (EditText) getActivity().findViewById(R.id.etRemitente);
        EditText asunto = (EditText) getActivity().findViewById(R.id.etAsunto);
        EditText contenido = (EditText) getActivity().findViewById(R.id.etContenido);

        remitente.setText(item.getRemitente());
        asunto.setText(item.getAsunto());
        contenido.setText(item.getContenido());
    }
}
