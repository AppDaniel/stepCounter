package com.example.macbookair.stepcounter;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StepService extends Service implements SensorEventListener {

    SensorManager sensorManager;
    int steps;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onCreate() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.
                getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_NORMAL);
        DatabaseHandler db = new DatabaseHandler(this);
        String test = db.findSteps();


        steps = Integer.parseInt(test);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

//            float[] values = event.values;
//            float x = values[0];
//            float y = values[1];
//            float z = values[2];
//
//
//            float absoluteAcceleration = (x * x + y * y + z * z) /
//                    (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
//
//            if (absoluteAcceleration >= 4) {

                steps++;

                DatabaseHandler sqlFinder = new DatabaseHandler(this);
                sqlFinder.insert();

                String dateTime = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
                StepCounter step = sqlFinder.update(steps, dateTime);


//            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread = new Thread();
        thread.start();
        return Service.START_STICKY;
    }
}
