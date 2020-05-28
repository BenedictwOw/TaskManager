package com.myapplicationdev.android.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TASKS = "tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASKS +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }
    public long insertTasks(String name, String description) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_DESCRIPTION, description);
        // Insert the row into the TABLE_TASK
        long result = db.insert(TABLE_TASKS, null, values);
        db.close();
        // Close the database connection
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }
    public ArrayList<Task> getTasks() {
        //TODO return records in Strings
        ArrayList<Task> toDos = new ArrayList<Task>();
        String selectQuery = "SELECT " + COLUMN_ID
                +", "
                + COLUMN_NAME + ", "
                + COLUMN_DESCRIPTION
                + " FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String data = cursor.getString(2);
                Task obj = new Task(id, date, data);
                toDos.add(obj);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return toDos;
    }


}
