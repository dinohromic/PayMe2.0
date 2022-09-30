package com.example.payme20.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseSaver extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GroupManager";
    private static final String TABLE_MEMBER = "Members";
    private static final String KEY_NAME = "Name";
    private static final String KEY_NUMBER = "Phone number";
    private static final String KEY_ID = "id";
    private static final String SQLCON = "jdbc:sqlite:members.sqlite:sqlite";

    public DataBaseSaver(@Nullable Context context) {
        super(context, "members.db", null, DATABASE_VERSION);
    }

//    public static Connection getConnection () throws SQLException {
//        try{
//            Class.forName("org.sqlite.JDBC");
//            return DriverManager.getConnection(SQLCON);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_GROUP_TABLE = "CREATE TABLE " + TABLE_MEMBER + "(" + KEY_ID + "INTEGER PRIMARY_KEY, " +
                KEY_NAME + "TEXT, " + KEY_NUMBER + "INT)";
        sqLiteDatabase.execSQL(CREATE_GROUP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);

        onCreate(sqLiteDatabase);
    }

    public boolean addGroupMembers(Member member ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // for(int i = 0, i < members, i ++){
        //      if( GroupCreate == true) CREATE TABLE;
        values.put(KEY_NAME, member.getUserName());
        values.put(KEY_NUMBER, member.getPhoneNumber());
        long insert = db.insert(TABLE_MEMBER, null, values);
        return insert != -1;
        //db.close();

    }

//    public void deleteMember(Member member){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        sqLiteDatabase.delete(TABLE_MEMBER, KEY_NAME +" =?", new String[]{String.valueOf(member.getUserName())});
//
//    }

    //Return to this method, the parameters are wrong. They are only set there for "new Member()"s satisfaction
    // Another problem is that the if statement is wrong because this method needs the the list of the members from ViewModel
//    public List<Member> getAllMembers(String userName, String phoneNumber){
//        List<Member> memberList = new ArrayList<Member>();
//        String selectQuery = "SELECT *FROM " +  TABLE_MEMBER;
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()){
//            do{
//                Member member = new Member(userName,phoneNumber);
//                member.setUserName(cursor.getString(0));
//                member.setPhoneNumber(cursor.getString(1));
//                memberList.add(member);
//            } while (cursor.moveToNext());
//        }
//        return memberList;
//
//    }


}
