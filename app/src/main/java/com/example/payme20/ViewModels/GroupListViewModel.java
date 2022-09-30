package com.example.payme20.ViewModels;

import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.google.android.material.shape.MarkerEdgeTreatment;

import java.util.ArrayList;
import java.util.List;

public class GroupListViewModel {

    public String getGroupName(Group currentGroup){
        return currentGroup.getGroupName();
    }

    public ArrayList<Group> getGroupList(){
        ArrayList<Group> test = new ArrayList<Group>();
        List<Member> members = new ArrayList<>();
        Member test1 = new Member("Arne", "112");
        Member test2 = new Member("Anders", "114 14");
        members.add(test1);
        members.add(test2);
        test.add(new Group("Grekland", members));
        test.add(new Group("Middag", members));
        return test;
    }
}
