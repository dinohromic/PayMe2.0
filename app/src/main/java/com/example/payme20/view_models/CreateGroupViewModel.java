package com.example.payme20.view_models;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

import java.util.ArrayList;

public class createGroupViewModel {
    ArrayList<Member> membersList = new ArrayList<>();
    private Group group;
    public createGroupViewModel(){
    }
    public void addMembers(String memberName, String memberNumbber, int id){
        membersList.add(Factory.createMember(memberName, memberNumbber, id));
    }
    public void createGroup(String groupName){
        this.group = Factory.createGroup(groupName,membersList);
    }
    public Group getGroup(){
        return group;
    }

}
