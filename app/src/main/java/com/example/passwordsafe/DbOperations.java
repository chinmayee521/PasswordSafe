package com.example.passwordsafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Vector;

public class DbOperations extends SQLiteOpenHelper {


    private static final String name = "mydb";
    private static final int version = 1;
    private static final String table_name = "passwordsafe";
    private static final String desc_col = "description";
    private static final String uname_col = "username";
    private static final String pass_col = "password";

    public DbOperations(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table_name + " ( " + desc_col + " VARCHAR, " + uname_col + " VARCHAR, " + pass_col + " VARCHAR )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public void insertRecord(String desc, String uname, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(desc_col, desc);
        values.put(uname_col, uname);
        values.put(pass_col, pwd);

        db.insert(table_name, null, values);
        db.close();
    }

    public Vector getDescription() {
        String query = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Vector desc_list = new Vector();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            desc_list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        db.close();
        return desc_list;
    }

    public Vector recordAt(CharSequence title) {
        String query = "SELECT * FROM " + table_name + " WHERE description = '" + title + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        Vector vector = new Vector();
        for (int i = 0; i < 3; i++)
            vector.add(cursor.getString(i));
        return vector;
    }


    public void deleteRecord(String title) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + table_name + " WHERE description = '" + title +"'");
    }

    public void updatePassword(String password, String title) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE " + table_name + " SET " + pass_col + " = '" + password + "' WHERE " + desc_col + " = '" + title + "'");
    }

    public ArrayList<PwdSafe> readAllData() {
        //create db for reading our db
        SQLiteDatabase db = this.getReadableDatabase();

        //creating a cursor with query to read data from db
        Cursor cursorPwd = db.rawQuery("SELECT * FROM " + table_name, null);

        //creating a new arraylist
        ArrayList<PwdSafe> list = new ArrayList<>();

        //move cursor to 1st pos
        if (cursorPwd.moveToFirst()) {

            do {
                list.add(new PwdSafe(cursorPwd.getString(0),
                        cursorPwd.getString(1),
                        cursorPwd.getString(2)));
            } while (cursorPwd.moveToNext());


        }
        cursorPwd.close();
        return list;
    }

}



