package com.example.payme20.view_models;

import android.content.Context;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

import java.util.ArrayList;

public class CreateGroupViewModel {
    Context context;

    ArrayList<Member> membersList = new ArrayList<>();
    private Group group;
    public CreateGroupViewModel(Context context){
        this.context = context;
    }


    public void addMembers(String memberName, String memberNumbber, String groupName){
        Member member = Factory.createMember(memberName, memberNumbber);

        membersList.add(member);

    }
    public void createGroup(String groupName){
        this.group = Factory.createGroup(groupName,membersList);
    }

    public Group getGroup(){
        return group;
    }

}
