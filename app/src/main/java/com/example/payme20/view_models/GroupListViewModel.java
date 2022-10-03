package com.example.payme20.view_models;

import com.example.payme20.model.DetailedDebtUpdater;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupListViewModel {

    public String getGroupName(Group currentGroup){
        return currentGroup.getGroupName();
    }

    public ArrayList<Group> getGroupList(){
        ArrayList<Group> test = new ArrayList<Group>();
        List<Member> members = new ArrayList<>();
        Member test1 = new Member("Arne", "112");
        Member test2 = new Member("Anders", "114 14");
        Member test3 = new Member("ahmed","231");
        Member test4 = new Member("Jihad","231");
        Member test5 = new Member("Ola", "2345");

        Map<Member, Integer> costMap = new HashMap<>();
        costMap.put(test1, 100);
        costMap.put(test2, 120);
        costMap.put(test3, 140);
        costMap.put(test4, 160);
        costMap.put(test5, 180);

        members.add(test1);
        members.add(test2);
        members.add(test3);
        members.add(test4);
        members.add(test5);

        Group grekland = new Group("Grekland", members);
        PayMeModel model = new PayMeModel();
        model.createNewGroupEvent(grekland, costMap,"fun", test1, new DetailedDebtUpdater());

        test.add(grekland);
        test.add(new Group("Middag", members));
        return test;
    }
}