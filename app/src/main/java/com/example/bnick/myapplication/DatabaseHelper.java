package com.example.bnick.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Contact2018_2.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";
    public static final String PHONE = "PhoneNumber";
    public static final String ADDRESS = "ContactAddress";

    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_PHONE_NUMBER = "number";
    public static final String COLUMN_HOME_ADDRESS = "address";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT," + COLUMN_PHONE_NUMBER + " TEXT," +
                    COLUMN_HOME_ADDRESS + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("MyContactApp", "DatabaseHelper: constructed DatabaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "DatabaseHelper: constructed DatabaseHelper");
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "DatabaseHelper: constructed DatabaseHelper");
        db.execSQL(SQL_DELETE_ENTRIES);

    }

    //Add a name
    public boolean insertData(String name, String number, String address){
        Log.d("MyContactApp", "DatabaseHelper: upgrading database");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CONTACT, name);
        contentValues.put(COLUMN_PHONE_NUMBER, name);
        contentValues.put(COLUMN_HOME_ADDRESS,name);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            Log.d("MyContactApp", "DatabaseHelper: Contact insert - FAILED");
            return false;
        }
        else{
            Log.d("MyContactApp", "DatabaseHelper: Contact insert - FAILED");
            return true;
        }
    }

    public Cursor getAllData(){
        Log.d("MyContactApp", "DatabaseHelper: pulling all records from db");
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor res = DB.rawQuery("select * from " + TABLE_NAME, null);
        return res;


    }
}
