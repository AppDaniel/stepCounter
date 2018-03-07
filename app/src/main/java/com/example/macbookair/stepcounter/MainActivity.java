package com.example.macbookair.stepcounter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    public static int steps = 0;
    TextView showStep;
    private DatabaseHandler sqlFinder;
    ChangeActivities changeActivities;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();


        startService(new Intent(this, stepService.class));
        showStep = findViewById(R.id.amountSteps);
//        showDebugDBAddressLogToast(this);

        Button sendDataButton = findViewById(R.id.button);
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dateTime = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
                Steg step = sqlFinder.createDay(steps, dateTime);
            }
        });

        Button update = findViewById(R.id.updateBtn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String findSteps =  sqlFinder.findSteps();
                showStep.setText(findSteps);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        sqlFinder.insert();

    }

    @Override
    protected void onPause() {
        super.onPause();
        sqlFinder.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.historik) {

            changeActivities.StartNewActivity(MainActivity.this, HistrikLista.class);

        }
        if (item.getItemId() == R.id.profil) {

            changeActivities.StartNewActivity(MainActivity.this, HistrikLista.class);

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

