package com.example.payme20.view_models;

import com.example.payme20.model.Group;
import com.example.payme20.model.PayMeModel;
/**
 * This class is responsible for the communication between CreateNewMembersView and the model
 */
public class CreateNewMembersViewModel {

    private final Group group;
    private final PayMeModel payMeModel = PayMeModel.INSTANCE;

    public CreateNewMembersViewModel(String group){
        this.group = payMeModel.getGroups().get(group);
    }

    public void addNewGroupMember (String name, String num){
        payMeModel.addNewMemberToGroup(group, name, num);
    }

    public Group getGroup() {
        return group;
    }
}
