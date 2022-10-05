package com.example.payme20.view_models;

import android.content.Context;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.MemberDaoImplement;

import java.util.ArrayList;

public class CreateGroupViewModel {
    Context context;
    MemberDaoImplement memberDaoImplement;
    ArrayList<Member> membersList = new ArrayList<>();
    private Group group;
    public CreateGroupViewModel(Context context){
        this.context = context;
    }


    public void addMembers(String memberName, String memberNumbber, int id){
        memberDaoImplement = new MemberDaoImplement(context);
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
