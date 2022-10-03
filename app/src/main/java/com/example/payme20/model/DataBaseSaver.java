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
    private static final String MEMBER_TABLE = "MEMBER_TABLE";
    private static final String COLUMN_MEMBER_NAME = "MEMBER_NAME";
    private static final String COLUMN_MEMBER_PHONE_NUMBER = "MEMBER_PHONE_NUMBER";
    private static final String COLUMN_ID = "ID";


    public DataBaseSaver(@Nullable Context context) {
        super(context, "members.db", null, 1);
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
        String createTableStatement = "CREATE TABLE " + MEMBER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MEMBER_NAME + " TEXT, " + COLUMN_MEMBER_PHONE_NUMBER + " TEXT )";
        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MEMBER_TABLE);

        onCreate(sqLiteDatabase);
    }

    public void addGroupMembers(Member member ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEMBER_NAME, member.getUserName());
        values.put(COLUMN_MEMBER_PHONE_NUMBER, member.getPhoneNumber());
        db.insert(MEMBER_TABLE, null, values);

        db.close();

    }
    //This method is to extract a member from the database
    public Member getMemberFromDB(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(MEMBER_TABLE, new String []{COLUMN_ID, COLUMN_MEMBER_NAME, COLUMN_MEMBER_PHONE_NUMBER},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Member(cursor.getString(0), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
    }

    public void deleteMember(Member member){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(MEMBER_TABLE, COLUMN_MEMBER_NAME +" =?", new String[]{String.valueOf(member.getUserName())});

    }
    //This method is a read only, where we count the amount of members in the table
    public int countAmountOfMembers(){
        String count = "SELECT * FROM " + MEMBER_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(count, null);
        cursor.close();
        return cursor.getCount();
    }

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
