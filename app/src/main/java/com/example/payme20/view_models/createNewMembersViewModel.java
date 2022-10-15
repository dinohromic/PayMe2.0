package com.example.payme20.view_models;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

public class createNewMembersViewModel {

    private Group group;
    private PayMeModel payMeModel = PayMeModel.INSTANCE;

    public createNewMembersViewModel(Group group){
        this.group = payMeModel.getGroups().get(group.getGroupName());
    }

    public void addNewGroupMember (String name, String num){
        //Member newMember = Factory.createMember(name, num, id);
        payMeModel.addNewMemberToGroup(group, name, num);
    }

    public Group getGroup() {
        return group;
    }
}
