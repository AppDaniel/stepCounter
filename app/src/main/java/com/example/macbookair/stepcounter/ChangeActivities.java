package com.example.macbookair.stepcounter;

import android.app.Activity;
import android.content.Intent;


public class ChangeActivities {

    public static void StartNewActivity(Activity fromClass, Class<? extends Activity> toClass) {
        fromClass.startActivity(new Intent(fromClass, toClass));
        fromClass.finish();

    }
}

