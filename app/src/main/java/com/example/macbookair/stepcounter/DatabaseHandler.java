package com.example.macbookair.stepcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookair on 2018-03-03.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    final static String TABLE_STEPS = "tablesteps";
    final static String COLUMN_ID = "id";
    final static String COLUMN_STEP = "step";
    final static String COLUMN_DATETIME = "datetime";

    private SQLiteDatabase database;

    public DatabaseHandler (Context context){
        super(context,"database.db", null, 13);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        final String DATABASE_CREAT ="CREATE TABLE tablesteps (step TEXT, datetime INTEGER, id INTEGER PRIMARY KEY)";
//        final String DATABASE_CREAT ="create table tablesteps (id integer primary key autoincrement, step text not null)";
        final String DATABASE_CREAT ="create table tablesteps (id integer primary key autoincrement, step text, datetime text)";
        sqLiteDatabase.execSQL(DATABASE_CREAT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STEPS );
        onCreate(sqLiteDatabase);


    }

    void insert(){

        database = getWritableDatabase();

    }

    Steg createDay(String step, String date ){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STEP, step);
        contentValues.put(COLUMN_DATETIME, date);



        long id = database.insert(TABLE_STEPS, null, contentValues);





        Steg steg = new Steg();
        steg.setId(id);

        return steg;

    }

    List<Steg> putAllPersonInList(){
        List<Steg> steps = new ArrayList<>();
        String[] allColumns = {COLUMN_ID,COLUMN_STEP, COLUMN_DATETIME};
//        String[] allColumns = {COLUMN_ID,COLUMN_STEP};
         Cursor cursor = database.query(TABLE_STEPS, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            Steg steg = rowToObject(cursor);

            steps.add(steg);
            cursor.moveToNext();
        }
        cursor.close();
        return steps;
    }

    private Steg rowToObject(Cursor cursor){

        Steg step = new Steg();

        step.setId(cursor.getLong(0));

        step.setSteps(cursor.getString(1));

        step.setDate(cursor.getString(2));

        return step;

    }

    void deleteFirstName(Steg steg){
        Long id = steg.getId();
        database.delete(TABLE_STEPS, COLUMN_ID+ "="+ id, null);

    }

    void update(Integer steps, Integer amountOfrows){
        ContentValues uppdateDB = new ContentValues();


//        UPDATE table set col = 1 WHERE id = (SELECT MAX(id) FROM table)

        String updateQuery = "Update TABLE_STEPS set COLUMN_STEP = 1 WEHRE id=(SELECT max(id)";
        Log.i("tag",updateQuery);

        uppdateDB.put("step",steps);
        database.update(TABLE_STEPS,uppdateDB, "step=" + 78, null);


    }
    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STEPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }



}

