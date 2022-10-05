package com.example.payme20.view_models;

import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

public class createNewMembersViewModel {

    private Group group;
    private PayMeModel payMeModel = PayMeModel.INSTANCE;

    public createNewMembersViewModel(Group group){
        this.group = group;
    }

    public void addNewGroupMember (String name, String num){

        Member newMember = payMeModel.createNewMember(name,num,-1);
        payMeModel.addMember(group, newMember);
    }

    public Group getGroup() {
        return group;
    }
}
