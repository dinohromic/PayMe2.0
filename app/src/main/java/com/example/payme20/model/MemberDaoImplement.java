package com.example.payme20.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class MemberDaoImplement extends SQLiteOpenHelper implements MemberDAO {


    public MemberDaoImplement(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void addGroupMembers(Member member) {

    }

    @Override
    public void getMemberFromDB(int id) {

    }

    @Override
    public void deleteMembers(Member member) {

    }

    @Override
    public int getCountAllMembers() {
        return 0;
    }

    @Override
    public List<Member> getALlMembersFromDB() {
        return null;
    }


}
