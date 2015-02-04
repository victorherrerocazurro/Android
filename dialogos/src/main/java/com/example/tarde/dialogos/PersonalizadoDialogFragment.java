package com.example.tarde.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by tarde on 04/02/2015.
 */
public class PersonalizadoDialogFragment extends DialogFragment {

    private AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        builder = new AlertDialog.Builder(getActivity());

        View view = getActivity()
                .getLayoutInflater().inflate(R.layout.dialog_personalizado, null);

        builder.setView(view)
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String dato1 = ((EditText) ((AlertDialog) dialog)
                                .findViewById(R.id.etDato1)).getText().toString();
                        String dato2 = ((EditText) ((AlertDialog) dialog)
                                .findViewById(R.id.etDato2)).getText().toString();

                        Toast.makeText(getActivity(),
                                "Dato1= " + dato1 + "; Dato2= " + dato2,
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return builder.create();
    }
}
