package com.example.macbookair.stepcounter;

import android.app.ListActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends ListActivity implements SensorEventListener {



        private SensorManager sensorManager;
        public static int steps=0;
        TextView showStep;
        private DatabaseHandler sqlFinder;
        ArrayList stringData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        startService(new Intent(this, stepService.class));



        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        startService(new Intent(this, stepService.class));


//        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

//        for (Sensor sensor: sensors){
//            Log.i("tag",sensor.getName());
//        }

//        showDebugDBAddressLogToast(this);
        showStep = findViewById(R.id.amountSteps);



        List<Steg> list = sqlFinder.putAllPersonInList();

        final ArrayAdapter<Steg> arrayAdapter = new ArrayAdapter<Steg>
                (this, android.R.layout.simple_list_item_1, list);

        setListAdapter(arrayAdapter);

         Button updateB  = findViewById(R.id.button6);
         updateB.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 int profile_counts = sqlFinder.getProfilesCount();
                sqlFinder.update(steps, profile_counts);
                arrayAdapter.notifyDataSetChanged();

             }
         });



        Button sendDataButton = findViewById(R.id.button);
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String step = (showStep.getText().toString());


                String dateTime = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Steg steg = sqlFinder.createDay(step,dateTime);

                arrayAdapter.add(steg);
                arrayAdapter.notifyDataSetChanged();

            }
        });


        Button deleteButton = findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!arrayAdapter.isEmpty()) {

                    Steg name = arrayAdapter.getItem(0);
                    sqlFinder.deleteFirstName(name);
                    arrayAdapter.remove(name);
                    arrayAdapter.notifyDataSetChanged();
                    stringData.remove(name);
                }
            }
        });

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

                showStep.setText("steg : " + steps);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.
                getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        sqlFinder.insert();

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.registerListener(this, sensorManager.
                getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        sqlFinder.close();
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

//    public static void showDebugDBAddressLogToast(Context context) {
//        if (BuildConfig.DEBUG) {
//            try {
//                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
//                Method getAddressLog = debugDB.getMethod("getAddressLog");
//                Object value = getAddressLog.invoke(null);
//                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
//
//            } catch (Exception ignore) {
//
//            }
//        }
//    }

}

