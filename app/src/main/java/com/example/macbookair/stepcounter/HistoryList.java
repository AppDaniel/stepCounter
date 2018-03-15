package com.example.macbookair.stepcounter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class HistoryList extends ListActivity {

    DatabaseHandler sqlFinder;
    ChangeActivities changeActivities;
    TextView mostSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();

        List<StepCounter> list = sqlFinder.putAllPersonInList();

        final ArrayAdapter<StepCounter> arrayAdapter = new ArrayAdapter<StepCounter>
                (this, android.R.layout.simple_list_item_1, list);

        setListAdapter(arrayAdapter);

        Button goHome = findViewById(R.id.backHome);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryList.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {

            ChangeActivities.StartNewActivity(HistoryList.this, MainActivity.class);

        }
        if (item.getItemId() == R.id.profil) {

            ChangeActivities.StartNewActivity(HistoryList.this, Profile.class);

        }

        return super.onOptionsItemSelected(item);
    }
}
