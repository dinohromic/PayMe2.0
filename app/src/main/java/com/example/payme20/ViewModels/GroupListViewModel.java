package com.example.payme20.ViewModels;

import com.example.payme20.model.Group;

public class GroupListViewModel {

    public String getGroupName(Group currentGroup){
        return currentGroup.getGroupName();
    }
}
