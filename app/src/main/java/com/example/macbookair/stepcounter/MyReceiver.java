package com.example.macbookair.stepcounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action != null) {
            Boolean checkAirPlaneMode = intent.getBooleanExtra("state", false);
            if (checkAirPlaneMode) {
                Toast.makeText(context, "Don't worrie! StepCounter still counts your step", Toast.LENGTH_LONG).show();

            } else
                Toast.makeText(context, "You are back!", Toast.LENGTH_SHORT).show();
        }
    }
}
