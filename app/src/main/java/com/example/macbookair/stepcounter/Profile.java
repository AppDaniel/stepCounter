package com.example.macbookair.stepcounter;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.security.acl.Permission;

/**
 * Created by macbookair on 2018-03-08.
 */

public class Profile extends AppCompatActivity {

    ChangeActivities changeActivities;
    TextView mostSteps, nameText, goalText;
    DatabaseHandler sqlFinder;
    ImageView imageView;
    final static int MY_REQUEST_CODE = 200;
    PermissionHandler permissionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Toolbar toolbar = findViewById(R.id.toolbar);

        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();

        permissionHandler = new PermissionHandler();
        permissionHandler.checkPermissions(Profile.this, MY_REQUEST_CODE);

        imageView = findViewById(R.id.imageView);
        Drawable myDraw = getResources().getDrawable(R.drawable.steps);
        imageView.setImageDrawable(myDraw);

        String mosteSteps = sqlFinder.sortMosteSteps();
        mostSteps = findViewById(R.id.mostStepstextView);
        mostSteps.setText(mosteSteps);

//        String name = sqlFinder.findName();
        nameText = findViewById(R.id.nameTextViewP);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String userName = sharedPreferences.getString("name", "?");
        if (userName.isEmpty()){
            nameText.setText("No Name");
        }else
            nameText.setText(userName);


//        nameText.setText(name);

//        String goal = sqlFinder.getGoal();
        goalText = findViewById(R.id.goalTextViewP);
//        goalText.setText(goal);

        String goalStep = sharedPreferences.getString("goalstep", "?");
        if (goalStep.isEmpty()){
            goalText.setText("10000");
        }else
            goalText.setText(goalStep);

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

            ChangeActivities.StartNewActivity(Profile.this, HistoryList.class);

        }
        if (item.getItemId() == R.id.home) {

            ChangeActivities.StartNewActivity(Profile.this, MainActivity.class);

        }
        if (item.getItemId() == R.id.action_settings) {

            ChangeActivities.StartNewActivity(Profile.this, UserSettings.class);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_REQUEST_CODE: {
                Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
                boolean readAccepted = false;
                boolean writeAccepted = false;
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    readAccepted = true;
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED)
                    writeAccepted = true;
                if (readAccepted && writeAccepted)
                    Toast.makeText(this, "Ok!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Not Ok!", Toast.LENGTH_LONG).show();


            }

        }


    }
}

