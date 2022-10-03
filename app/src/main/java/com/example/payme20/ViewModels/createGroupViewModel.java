package com.example.payme20.ViewModels;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
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

    }

}
