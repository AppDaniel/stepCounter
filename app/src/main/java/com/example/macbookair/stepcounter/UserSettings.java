package com.example.macbookair.stepcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserSettings extends AppCompatActivity  {

    ChangeActivities changeActivities;
    private DatabaseHandler sqlFinder;
    Button update;
    EditText name, stepgoal;
    String nam;
    int goal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usersetting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();

        name = findViewById(R.id.nameEditText);
        stepgoal = findViewById(R.id.goalStepEditText);
        update = findViewById(R.id.saveSettings);

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

            changeActivities.StartNewActivity(UserSettings.this, HistoryList.class);

        }
        if (item.getItemId() == R.id.profil) {

            changeActivities.StartNewActivity(UserSettings.this, Profile.class);

        }
        if (item.getItemId() == R.id.home) {

            changeActivities.StartNewActivity(UserSettings.this, MainActivity.class);

        }


        return super.onOptionsItemSelected(item);
    }

    public void onDeleteClick(View v) {
        Intent endIntent = new Intent(this, StepService.class);
        stopService(endIntent);
        sqlFinder.deleteAll();


    }

    public void onStopClick(View v) {
        Intent endIntent = new Intent(this, StepService.class);
        stopService(endIntent);

    }
    public void onUpdateClick(View v) {
        nam = name.getText().toString();
        String testar = stepgoal.getText().toString();
        goal =  Integer.parseInt(testar);
        StepCounter something = sqlFinder.addProfile(nam,goal);
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);

    }


}
