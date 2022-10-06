package com.example.payme20.view_models;

import com.example.payme20.model.Group;
import com.example.payme20.model.PayMeModel;

public class GroupPageViewModel {
    private String groupName;
    private Group group;
    private PayMeModel payMeModel = PayMeModel.INSTANCE;

    public GroupPageViewModel(Group group){
        this.group = group;
    }

    public Group getGroup(){
        return this.group;
    }

    public String totalExpenditure(){
        return "Total expenditure: " + calculateTotalExpenditure() + " kr";
    }
    private int calculateTotalExpenditure(){
        int total = payMeModel.calcTotalExpenditureForGroup(this.group);
        return total;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
