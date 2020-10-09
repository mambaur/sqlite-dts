package com.example.sqlite_dts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "student";
    private static final String KEY_FIRSTNAME = "name";
    private static final String KEY_ID = "id";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " +
            TABLE_STUDENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_FIRSTNAME +" TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table", CREATE_TABLE_STUDENTS);
    }

    public long addStudentDetail(String student){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIRSTNAME, student);

        long insert = sqLiteDatabase.insert(TABLE_STUDENTS, null, contentValues);
        return insert;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '"+TABLE_STUDENTS+"'");
        onCreate(db);
    }
}
