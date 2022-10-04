package com.example.payme20.model;

import java.util.List;

public interface MemberDAO {
    public void addGroupMembers(Member member);
    public void getMemberFromDB(int id);
    public void deleteMembers(Member member);
    public int getCountAllMembers();
    public List<Member> getALlMembersFromDB();


}
