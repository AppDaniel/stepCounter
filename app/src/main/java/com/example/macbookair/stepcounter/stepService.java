package com.example.macbookair.stepcounter;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class stepService extends Service implements SensorEventListener {

    SensorManager sensorManager;
    int steps=0;



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor: sensors){
            Log.i("tag",sensor.getName());
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()== Sensor.TYPE_ACCELEROMETER){

            float [] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];


            float absoluteAcceleration = (x * x + y * y + z * z)/
                    (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

            if (absoluteAcceleration>=4){

                steps++;

//                Här måste vi skcika tillbaka steps!
//                showStep.setText("steg : " + steps);

            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        sensorManager.registerListener(this, sensorManager.
                getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        sensorManager.unregisterListener(this);
    }



}
