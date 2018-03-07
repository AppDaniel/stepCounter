package com.example.macbookair.stepcounter;

import android.app.Activity;
import android.content.Intent;


public class ChangeActivities {

    public static void StartNewActivity(Activity activity, Class<? extends Activity> classOut) {
        activity.startActivity(new Intent(activity, classOut));
        activity.finish();

    }
}

