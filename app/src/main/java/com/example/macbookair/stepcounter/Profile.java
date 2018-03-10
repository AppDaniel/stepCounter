package com.example.macbookair.stepcounter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by macbookair on 2018-03-08.
 */

public class Profile extends AppCompatActivity {

    ChangeActivities changeActivities;
    TextView mostSteps, nameText, goalText;
    DatabaseHandler sqlFinder;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();

        imageView = findViewById(R.id.imageView);
        Drawable myDraw = getResources().getDrawable(R.drawable.steps);
        imageView.setImageDrawable(myDraw);

        String mosteSteps = sqlFinder.sortMosteSteps();
        mostSteps = findViewById(R.id.mostStepstextView);
        mostSteps.setText(mosteSteps);

        String name = sqlFinder.findName();
        nameText = findViewById(R.id.nameTextViewP);
        nameText.setText(name);

        String goal = sqlFinder.getGoal();
        goalText = findViewById(R.id.goalTextViewP);
        goalText.setText(goal);
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

            changeActivities.StartNewActivity(Profile.this, HistoryList.class);

        }
        if (item.getItemId() == R.id.home) {

            changeActivities.StartNewActivity(Profile.this, MainActivity.class);

        }
        if (item.getItemId() == R.id.action_settings) {

            changeActivities.StartNewActivity(Profile.this, UserSettings.class);

        }

        return super.onOptionsItemSelected(item);
    }
}
