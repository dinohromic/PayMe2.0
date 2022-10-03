package com.example.payme20.view_models;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Member;

import java.util.ArrayList;

public class createGroupViewModel {
    ArrayList<Member> membersList;
    public createGroupViewModel(){
    }
    public void addMembers(String memberName, String memberNumbber){
        membersList.add(Factory.createMember(memberName,memberNumbber));
    }
    public void createGroup(String groupName){
        Factory.createGroup(groupName,membersList);
    }

}
