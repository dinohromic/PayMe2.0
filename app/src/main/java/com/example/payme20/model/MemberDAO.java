package com.example.payme20.model;

public interface MemberDAO {
    public void addGroupMembers(Member member);
    public void getMemberFromDB(int id);
    public void deleteMembers(Member member);
    public int getCountAllMembers();


}