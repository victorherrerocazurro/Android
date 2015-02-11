package com.example.tarde.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FuncionalidadRepetidaBroadcastReceiver extends BroadcastReceiver {

    public static final String EXTRA_DATO = "dato";

    public FuncionalidadRepetidaBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(MainActivity.ACTION);

        intent2.putExtra(EXTRA_DATO,"Te saludo una y otra vez!");

        context.sendBroadcast(intent2);
    }
}
