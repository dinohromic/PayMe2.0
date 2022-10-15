package com.example.payme20.view_models;

import android.content.Context;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

import java.util.ArrayList;
import java.util.Objects;

public class CreateGroupViewModel {
    Context context;
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    ArrayList<Member> membersList;
    public CreateGroupViewModel(Context context, ArrayList<Member> membersList){
        this.context = context;
        this.membersList = membersList;
    }


    public boolean addMembers(String memberName, String memberNumber){
        if(this.membersList.size() > 0){
            for (Member member: this.membersList) {
                if (!Objects.equals(member.getUserName(), memberName)) {
                    payMeModel.createNewMember(memberName, memberNumber);
                    Member member1 = Factory.createMember(memberName, memberNumber, id);
                    this.membersList.add(member1);
                    return true;
                }
                return false;
            }
        }
        else {
            Member member = Factory.createMember(memberName, memberNumber, id);
            this.membersList.add(member);
            return true;
        }
        return false;
    }
    public void createGroup(String groupName){
        payMeModel.deserializeGroups();
        payMeModel.createNewGroup(groupName, membersList);
    }
}
