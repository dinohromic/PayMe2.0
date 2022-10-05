package com.example.payme20.view_models;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.MemberDaoImplement;

import java.util.ArrayList;

public class CreateGroupViewModel {
    MemberDaoImplement memberDaoImplement;
    ArrayList<Member> membersList = new ArrayList<>();
    private Group group;
    public CreateGroupViewModel(){
    }
    public void addMember(){

    }
    public void addMembers(String memberName, String memberNumbber, int id){
        Member member = Factory.createMember(memberName, memberNumbber, id);
        membersList.add(member);
        System.out.println(member);
        memberDaoImplement.addGroupMembers(member);
    }
    public void createGroup(String groupName){
        this.group = Factory.createGroup(groupName,membersList);
    }

    public Group getGroup(){
        return group;
    }

}
