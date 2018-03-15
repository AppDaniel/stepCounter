package com.example.macbookair.stepcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    final static String TABLE_STEPS = "tablesteps";
    final static String COLUMN_ID = "id";
    final static String COLUMN_STEP = "step";
    final static String COLUMN_DATETIME = "datetime";
    final static String COLUMN_STEPGOAL = "stepgoal";
    final static String COLUMN_NAME = "name";
//    final static String[] allColumns = {COLUMN_ID, COLUMN_STEP, COLUMN_DATETIME, COLUMN_NAME, COLUMN_STEPGOAL};
    private SQLiteDatabase database;
    final static String[] allColumns = {COLUMN_ID, COLUMN_STEP, COLUMN_DATETIME};

    public DatabaseHandler(Context context) {
        super(context, "database.db", null, 15);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        final String DATABASE_CREAT = "create table tablesteps (id integer primary key" +
//                " autoincrement, step text, datetime text, name text, stepgoal integer)";
        final String DATABASE_CREAT = "create table tablesteps (id integer primary key" +
                " autoincrement, step text, datetime text)";
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

    StepCounter createDay(Integer step, String date) {

        ContentValues contentValues = new ContentValues();
        ContentValues uppdateDB = new ContentValues();
        StepCounter stepCounter1 = new StepCounter();

        contentValues.put(COLUMN_STEP, step);
        contentValues.put(COLUMN_DATETIME, date);

        String stepToSendBack = Integer.toString(step);

        long id = database.insert(TABLE_STEPS, null, contentValues);

        stepCounter1.setId(id);
        stepCounter1.setSteps(stepToSendBack);
        return stepCounter1;
    }


    List<StepCounter> putAllPersonInList() {
        List<StepCounter> steps = new ArrayList<>();
        Cursor cursor = database.query(TABLE_STEPS, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            StepCounter date = rowToObject(cursor);

            steps.add(date);
            cursor.moveToNext();
        }
        cursor.close();
        return steps;
    }

    private StepCounter rowToObject(Cursor cursor) {

        StepCounter step = new StepCounter();

        step.setId(cursor.getLong(0));

        step.setSteps(cursor.getString(1));

        step.setDate(cursor.getString(2));

//        step.setName(cursor.getString(3));
//
//        step.setStepgoal(cursor.getInt(4));

        return step;

    }

    StepCounter update(Integer steps, String date) {
        ContentValues uppdateDB = new ContentValues();

        int datum = Integer.parseInt(date.toString());

        uppdateDB.put("datetime", date);
        uppdateDB.put("step", steps);
        database.update(TABLE_STEPS, uppdateDB, "datetime=" + datum, null);

        return null;
    }

    StepCounter addProfile(String name, Integer stepgoal) {

        ContentValues uppdateDB = new ContentValues();
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues1 = new ContentValues();
        String dateTime = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        int datum = Integer.parseInt(dateTime.toString());
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_STEPGOAL, stepgoal);

        uppdateDB.put("name", name);
        uppdateDB.put("stepgoal", stepgoal);
        database.update(TABLE_STEPS, uppdateDB, "datetime=" + datum, null);

        return null;

    }

    public String sortMosteSteps() {

        String mostSteps = "";
        Cursor cursor = database.query(TABLE_STEPS, allColumns, null, null,
                null, null, COLUMN_STEP + " ASC");

        database = this.getWritableDatabase();

        if (cursor.moveToFirst()) {
            do {
                StepCounter stepCounter = new StepCounter();
                mostSteps = stepCounter.setSteps(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        return mostSteps;
    }

    public String findSteps() {

        String amountOfStepsDb = testa(1);
        return amountOfStepsDb;
    }

    public String findTodaysDate() {

        String checkTheDate = testa(2);
        return checkTheDate;
    }

//    public String findName() {
//
//        String findTheName = testa(3);
//        return findTheName;
//    }

//    public String findNameYesterday() {
//
//        String yesterDayDateTime = getYesterdayDateString();
//        String checkTheDate = "";
//
//        String allRecipesQuery = "SELECT * FROM " + TABLE_STEPS + " WHERE "
//                + COLUMN_DATETIME + " = " + yesterDayDateTime;
//
//        database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(allRecipesQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                StepCounter stepCounter = new StepCounter();
//                checkTheDate = stepCounter.setName(cursor.getString(3));
//            } while (cursor.moveToNext());
//
//        }
//        return checkTheDate;
//    }

//    public String findStepGoalYesterday() {
//
//        String yesterdayGoal = testa(4);
//        return yesterdayGoal;
//    }
//
//    public String getGoal() {
//
//        String getGoal = testa(4);
//        return getGoal;
//    }

    private String testa(int num) {

        String amountOfStepsDb = "";

        String dateTime = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        String allRecipesQuery = "SELECT * FROM " + TABLE_STEPS + " WHERE "
                + COLUMN_DATETIME + " = " + dateTime;

        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(allRecipesQuery, null);


        if (cursor.moveToFirst()) {
            do {
                StepCounter stepCounter = new StepCounter();
                amountOfStepsDb = stepCounter.setSteps(cursor.getString(num));

            } while (cursor.moveToNext());
        }
        return amountOfStepsDb;
    }


    public String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(yesterday());
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    void deleteAll() {
        database.delete(TABLE_STEPS, null, null);

    }

}

