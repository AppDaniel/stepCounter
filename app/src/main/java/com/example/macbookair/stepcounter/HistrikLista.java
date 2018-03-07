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

import java.util.List;

/**
 * Created by macbookair on 2018-03-05.
 */

public class HistrikLista extends ListActivity {

    DatabaseHandler sqlFinder;
    ChangeActivities changeActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_list);

        sqlFinder = new DatabaseHandler(this);
        sqlFinder.insert();

        List<Steg> list = sqlFinder.putAllPersonInList();

        final ArrayAdapter<Steg> arrayAdapter = new ArrayAdapter<Steg>
                (this, android.R.layout.simple_list_item_1, list);

        setListAdapter(arrayAdapter);

        Button goHome = findViewById(R.id.backHome);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HistrikLista.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.action_settings)
        {

            changeActivities.StartNewActivity(HistrikLista.this, MainActivity.class);

        }

        return super.onOptionsItemSelected(item);
    }
}
