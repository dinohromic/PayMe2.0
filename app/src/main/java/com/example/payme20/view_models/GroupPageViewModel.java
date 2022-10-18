package com.example.payme20.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.PayMeModel;

public class GroupPageViewModel extends ViewModel {
    private Group group;
    private PayMeModel payMeModel = PayMeModel.INSTANCE;
    private MutableLiveData<String> totalExpenditure = new MutableLiveData<>();

    public GroupPageViewModel(String groupKey){
        this.group = payMeModel.getGroups().get(groupKey);
    }

    public Group getGroup(){
        //this.group = payMeModel.getGroups().get(group.getGroupName());
        return this.group;
    }

    public String totalExpenditure(){
        return "Total expenditure: " + calculateTotalExpenditure() + " kr";
    }
    private int calculateTotalExpenditure(){
        return payMeModel.calcTotalExpenditureForGroup(this.group);
    }
    public void setTotalExpenditureText() {
        totalExpenditure.setValue("Total expenditure: " + calculateTotalExpenditure() + " kr");
    }
    public LiveData<String> getTotalExpenditureText() {
        return totalExpenditure;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setEventActive(Event event) {
        payMeModel.activateEvent(event, group);
    }

    public void setEventInactive(Event event) {
        payMeModel.inactivateEvent(event, group);
    }

    public void setAllEventsInactive(){
        payMeModel.inactivateAllEvents(group);
    }
}
