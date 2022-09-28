package com.example.payme20.ViewModels;

import com.example.payme20.model.Group;

import java.util.ArrayList;

public class GroupListViewModel {

    public String getGroupName(Group currentGroup){
        return currentGroup.getGroupName();
    }

    public ArrayList<Group> getGroupList(){
        ArrayList<Group> test = new ArrayList<Group>();
        test.add(new Group("Grekland"));
        test.add(new Group("Middag"));
        return test;
    }
}
