package com.example.payme20.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseSaver extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GroupManager";
    private static final String TABLE_MEMBER = "Members";
    private static final String KEY_NAME = "Name";
    private static final String KEY_NUMBER = "Phone number";
    private static final String KEY_ID = "id";
    private static final String SQLCON = "jdbc:sqlite:members.sqlite:sqlite";

    public DataBaseSaver(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public static Connection getConnection () throws SQLException {
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLCON);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_GROUP_TABLE = "CREATE TABLE " + TABLE_MEMBER + "(" + KEY_ID + "PRIMARY_KEY, " +
                KEY_NAME + "TEXT, " + KEY_NUMBER + "TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_GROUP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addGroupMembers(Member member ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //if( GroupCreate == true) CREATE TABLE
        values.put(KEY_NAME, member.getUserName());
        values.put(KEY_NUMBER, member.getPhoneNumber());
        db.insert(TABLE_MEMBER, null, values);
        db.close();

    }


}
