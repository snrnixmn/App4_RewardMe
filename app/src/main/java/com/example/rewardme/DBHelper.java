package com.example.rewardme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    //TODO Define the Database properties
    private static final String DATABASE_NAME = "kids.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_KIDS = "Kids";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_STARS = "stars";
    private static final String COLUMN_CHORESCOUNT = "choresCount";
    private static final String COLUMN_CHORES= "chores";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO CREATE TABLE Note
        String createTableSql = "CREATE TABLE " + TABLE_KIDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_STARS + " TEXT,"
                + COLUMN_CHORESCOUNT + " TEXT,"
                + COLUMN_CHORES + " )";

        db.execSQL(createTableSql);

        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KIDS);
        onCreate(db);
    }

    public void insertToDo(String name, String stars, String choresCount, String chores) {
        //TODO insert the data into the database

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the name as value
        values.put(COLUMN_NAME, name);
        // Store the column name as key and the stars as value
        values.put(COLUMN_STARS, stars);
        // Store the column name as key and the choresCount as value
        values.put(COLUMN_CHORESCOUNT, choresCount);
        // Store the column name as key and the chores as value
        values.put(COLUMN_CHORES, chores);
        // Insert the row into the TABLE_KIDS
        db.insert(TABLE_KIDS, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<Kids> getData() {
        //TODO return records in Java objects

        ArrayList<Kids> notes = new ArrayList<Kids>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_STARS + ", "
                + COLUMN_CHORESCOUNT + ", "
                + COLUMN_CHORES
                + " FROM " + TABLE_KIDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String stars = cursor.getString(2);
                String choresCount = cursor.getString(3);
                String chores = cursor.getString(4);
                Kids obj = new Kids(name, stars, choresCount, chores);
                notes.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public ArrayList<Kids> getChores() {
        ArrayList<Kids> kids = new ArrayList<Kids>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_CHORES
                + " FROM " + TABLE_KIDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String chores = cursor.getString(0);
                Kids note = new Kids(COLUMN_NAME, COLUMN_STARS, COLUMN_CHORESCOUNT, chores);
                kids.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return kids;
    }


    public int updateChores(Kids data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHORES, data.getChores());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getName())};
        int result = db.update(TABLE_KIDS, values, condition, args);
        db.close();
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

    public int updateStars(Kids data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STARS, data.getStarCount());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getName())};
        int result = db.update(TABLE_KIDS, values, condition, args);
        db.close();
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

    public int deleteKidData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_KIDS, condition, args);
        db.close();
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

}

