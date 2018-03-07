package com.example.macbookair.stepcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by macbookair on 2018-03-03.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    final static String TABLE_STEPS = "tablesteps";
    final static String COLUMN_ID = "id";
    final static String COLUMN_STEP = "step";
    final static String COLUMN_DATETIME = "datetime";

    private SQLiteDatabase database;

    public DatabaseHandler(Context context) {
        super(context, "database.db", null, 13);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String DATABASE_CREAT = "create table tablesteps (id integer primary key autoincrement, step text, datetime text)";
        sqLiteDatabase.execSQL(DATABASE_CREAT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STEPS);
        onCreate(sqLiteDatabase);


    }

    void insert() {

        database = getWritableDatabase();

    }

    Steg createDay(Integer step, String date) {


        ContentValues contentValues = new ContentValues();
        ContentValues uppdateDB = new ContentValues();
        Steg steg1 = new Steg();

        contentValues.put(COLUMN_STEP, step);
        contentValues.put(COLUMN_DATETIME, date);

        String stepToSendBack = Integer.toString(step);

        long id = database.insert(TABLE_STEPS, null, contentValues);


        steg1.setId(id);
        steg1.setSteps(stepToSendBack);
//    }
        return steg1;
    }

    List<Steg> putAllPersonInList() {
        List<Steg> steps = new ArrayList<>();
        String[] allColumns = {COLUMN_ID, COLUMN_STEP,COLUMN_DATETIME};
        Cursor cursor = database.query(TABLE_STEPS, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            Steg date = rowToObject(cursor);

            steps.add(date);
            cursor.moveToNext();
        }
        cursor.close();
        return steps;
    }

    private Steg rowToObject(Cursor cursor) {

        Steg step = new Steg();

        step.setId(cursor.getLong(0));

        step.setSteps(cursor.getString(1));

        step.setDate(cursor.getString(2));

        return step;

    }

    Steg update(Integer steps, String date) {
        ContentValues uppdateDB = new ContentValues();


        int datum = Integer.parseInt(date.toString());

        uppdateDB.put("datetime", date);
        uppdateDB.put("step", steps);
        database.update(TABLE_STEPS, uppdateDB, "datetime=" + datum, null);


        return null;
    }


//    kan änvända denna för att räkna ut hur många rader vi har, kanske för
//    antal dagar man användt appen..
    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STEPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


        public String findSteps()
        {

            String dateTime = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
            String testtest ="";
            String allRecipesQuery = "SELECT * FROM "+ TABLE_STEPS + " WHERE " + COLUMN_DATETIME + " = " + dateTime;

            database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(allRecipesQuery, null);


            if (cursor.moveToFirst())
            {
                do {
                    Steg steg = new Steg();
                    testtest = steg.setSteps(cursor.getString(1));

                }while (cursor.moveToNext());
            }
            return testtest;
        }

}

