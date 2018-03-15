package com.example.macbookair.stepcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserSettings extends AppCompatActivity  {

    ChangeActivities changeActivities;
    private DatabaseHandler sqlFinder;
    Button update;
    EditText name, stepgoal;
    String nam,nameToast,checkTextEdit;
    int goal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usersetting);
        Toolbar toolbar = findViewById(R.id.toolbar);

        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();

        name = findViewById(R.id.nameEditText);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        nameToast = sharedPreferences.getString("name", name.getText().toString());
        Toast.makeText(UserSettings.this, "Hello "+ nameToast, Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        checkTextEdit = name.getText().toString();






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

            ChangeActivities.StartNewActivity(UserSettings.this, HistoryList.class);

        }
        if (item.getItemId() == R.id.profil) {

            ChangeActivities.StartNewActivity(UserSettings.this, Profile.class);

        }
        if (item.getItemId() == R.id.home) {

            ChangeActivities.StartNewActivity(UserSettings.this, MainActivity.class);

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
//        nam = name.getText().toString();
//        String testar = stepgoal.getText().toString();
//        goal =  Integer.parseInt(testar);
            setName();
            setGoal();
//        StepCounter something = sqlFinder.addProfile(nam,goal);
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);


    }

    private void setName(){
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        nameToast = sharedPreferences.getString("name", name.getText().toString());
        Toast.makeText(UserSettings.this, "Hello "+ nameToast, Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        checkTextEdit = name.getText().toString();

        editor.putString("name", name.getText().toString());

        editor.commit();
    }
    private void setGoal(){
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        checkTextEdit = stepgoal.getText().toString();

        editor.putString("goalstep", stepgoal.getText().toString());

        editor.commit();
    }


}
