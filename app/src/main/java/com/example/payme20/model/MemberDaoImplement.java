package com.example.payme20.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.payme20.view_models.CreateGroupViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImplement extends SQLiteOpenHelper implements MemberDAO {
    private static final String MEMBER_TABLE = "MEMBER_TABLE";
    private static final String COLUMN_MEMBER_NAME = "MEMBER_NAME";
    private static final String COLUMN_MEMBER_PHONE_NUMBER = "MEMBER_PHONE_NUMBER";
    private static final String COLUMN_ID = "ID";
    private static final String DB_PATH = "members.db";
    private CreateGroupViewModel createGroupViewModel;


    public MemberDaoImplement(@Nullable Context context) {
        super(context, DB_PATH, null, 1);
    }

    public static Connection getConnection () throws SQLException {
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Connection connection = getConnection();
            String createTableStatement = "CREATE TABLE " + MEMBER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MEMBER_NAME + " TEXT, " + COLUMN_MEMBER_PHONE_NUMBER + " TEXT )";
            sqLiteDatabase.execSQL(createTableStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MEMBER_TABLE);
        onCreate(sqLiteDatabase);

    }

    @Override
    public void addGroupMembers(Member member) {
        System.out.println("Hejdå");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, member.getId());
        values.put(COLUMN_MEMBER_NAME, member.getUserName());
        values.put(COLUMN_MEMBER_PHONE_NUMBER, member.getPhoneNumber());
        db.insert(MEMBER_TABLE, null, values);
        db.close();

    }

    @Override
    public Member getMemberFromDB(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(MEMBER_TABLE, new String []{COLUMN_ID, COLUMN_MEMBER_NAME, COLUMN_MEMBER_PHONE_NUMBER},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Member(cursor.getString(0), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
    }



    @Override
    public void deleteMembers(Member member) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(MEMBER_TABLE, COLUMN_MEMBER_NAME +" =?", new String[]{String.valueOf(member.getUserName())});


    }

    @Override
    public int getCountAllMembers() {
        String count = "SELECT * FROM " + MEMBER_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(count, null);
        cursor.close();
        return cursor.getCount();
    }

    @Override
    public List<Member> getALlMembersFromDB(String userName, String phoneNumber, int id) {
        List<Member> memberList = new ArrayList<Member>();
        String selectQuery = "SELECT *FROM " +  MEMBER_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Member member = new Member(userName,phoneNumber, id);
                member.setUserName(cursor.getString(0));
                member.setPhoneNumber(cursor.getString(1));
                member.setId(Integer.parseInt(cursor.getString(2)));
                memberList.add(member);
            } while (cursor.moveToNext());
        }
        return memberList;
    }


}
