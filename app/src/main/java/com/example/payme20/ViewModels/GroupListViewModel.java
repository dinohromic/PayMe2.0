package com.example.payme20.viewmodels;

import com.example.payme20.model.Group;

public class GroupListViewModel {

    public String getGroupName(Group currentGroup){
        return currentGroup.getGroupName();
    }
}
