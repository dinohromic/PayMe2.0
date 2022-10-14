package com.example.payme20.view_models;

import android.content.Context;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

import java.util.ArrayList;

public class CreateGroupViewModel {
    Context context;
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    ArrayList<Member> membersList = new ArrayList<>();
    public CreateGroupViewModel(Context context){
        this.context = context;
    }


    public void addMembers(String memberName, String memberNumber){
        Member member = Factory.createMember(memberName, memberNumber);
        membersList.add(member);
    }
    public void createGroup(String groupName){
        payMeModel.deserializeGroups();
        payMeModel.createNewGroup(groupName, membersList);
    }
}
