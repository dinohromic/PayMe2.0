package com.example.payme20.view_models;

import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

public class createNewMembersViewModel {

    private Group group;

    public createNewMembersViewModel(Group group){
        this.group = group;
    }

    public void addNewGroupMember (String name, String num){
        int id = Integer.parseInt(num);
        Member newMember = new Member(name,num,id);
        group.addNewGroupMember(newMember);
    }

    public Group getGroup() {
        return group;
    }
}
