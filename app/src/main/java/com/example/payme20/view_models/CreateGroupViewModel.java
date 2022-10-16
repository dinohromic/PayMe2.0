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
                    this.membersList.add(payMeModel.createNewMember(memberName, memberNumber));
                    return true;
                }
                return false;
            }
        }
        else {
            this.membersList.add(payMeModel.createNewMember(memberName, memberNumber));
            return true;
        }
        return false;
    }
    public void createGroup(String groupName){
        payMeModel.createNewGroup(groupName, membersList);
    }
}
