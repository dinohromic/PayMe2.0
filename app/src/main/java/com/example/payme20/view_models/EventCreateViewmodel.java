package com.example.payme20.view_models;

import androidx.lifecycle.ViewModel;

import com.example.payme20.model.DetailedCreateDebtList;
import com.example.payme20.model.Group;
import com.example.payme20.model.ICreateDebtList;
import com.example.payme20.model.Member;
import fileservice.MemberSerializer;
import com.example.payme20.model.PayMeModel;
import com.example.payme20.model.SplitCreateDebtList;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventCreateViewmodel extends ViewModel {
    @JsonSerialize(keyUsing = MemberSerializer.class)
    private Map<Member, Integer> memberAndAmount = new HashMap<>();
    private String eventName;
    private ICreateDebtList debtUpdater;
    private Member payer;
    private Group group;
    private final Map<String, Member> groupMembers = new HashMap<>();
    private List<String> eventMembers = new ArrayList<>();
    private String date;
    PayMeModel model = PayMeModel.INSTANCE;

    public EventCreateViewmodel(String groupName) {
        this.group = model.getGroups().get(groupName);
        this.debtUpdater = new SplitCreateDebtList();
        initMemberMap();
        initEventMemberList();
    }

    private void initEventMemberList() {
        if(group.getGroupMembers().size() != 0) {
            for (Map.Entry<String, Member> memberMap: groupMembers.entrySet()) {
                String memberName = memberMap.getKey();
                eventMembers.add(memberName);
            }
        }
    }

    private void initMemberMap() {
        if(group.getGroupMembers().size() != 0) {
            for(Member m : group.getGroupMembers()) {
                groupMembers.put(m.getUserName(), m);
            }
        }
    }

    public Map<String, Member> getGroupMembers() {
        return groupMembers;
    }

    public void setDebtUpdater(String str) {
        switch (str) {
            case "split":
                debtUpdater = new SplitCreateDebtList();
                break;
            case "detailed":
                debtUpdater = new DetailedCreateDebtList();
                break;
        }
    }
    public void setPayer(Member member) {
        payer = member;
    }
    public void removeEventMember(String str) {
        for(String s : eventMembers) {
            if(s.equals(str)) {
                eventMembers.remove(s);
                memberAndAmount.remove(groupMembers.get(s));
                break;
            }
        }
    }
    public void addEventMember(String str) {
        eventMembers.add(str);
    }

    public List<String> getEventMembers() {
        return eventMembers;
    }

    public void createEvent() {
        model.createNewGroupEvent(group.getGroupName(), memberAndAmount, eventName, payer, debtUpdater, date);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setMemberPayment(int amount, String name) {
        if(amount <= 0)
            return;
        memberAndAmount.put(groupMembers.get(name), amount);
        System.out.println(memberAndAmount);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Group getGroup() {
        return group;
    }
}
