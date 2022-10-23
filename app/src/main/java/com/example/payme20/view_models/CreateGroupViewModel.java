package com.example.payme20.view_models;

import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is responsible for the communication between GroupCreateView and the model
 */
public class CreateGroupViewModel {

    PayMeModel payMeModel = PayMeModel.INSTANCE;
    List<Member> membersList;

    public CreateGroupViewModel(){
        this.membersList = new ArrayList<>();
    }

    public void addMember(String memberName, String memberNumber){
        this.membersList.add(payMeModel.createNewMember(memberName, memberNumber));
    }
    public void createGroup(String groupName){
        payMeModel.createNewGroup(groupName, membersList);
    }

    public boolean isGroupNameAlreadyUsed(String s) {
        return payMeModel.getGroups().containsKey(s);
    }
}
