package com.example.payme20.view_models;

import androidx.lifecycle.ViewModel;

import com.example.payme20.model.DetailedCreateDebtList;
import com.example.payme20.model.Group;
import com.example.payme20.model.ICreateDebtList;
import com.example.payme20.model.Member;
import com.example.payme20.model.MemberSerializer;
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
    private final Map<Integer, Member> groupMembers = new HashMap<>();
    private List<Member> eventMembers = new ArrayList<>();
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
            for (Map.Entry<Integer, Member> memberMap: groupMembers.entrySet()) {
                Member member = memberMap.getValue();
                eventMembers.add(member);
            }
        }
    }

    private void initMemberMap() {
        if(group.getGroupMembers().size() != 0) {
            for(Member m : group.getGroupMembers()) {
                groupMembers.put(m.getId(), m);
            }
        }
    }

    public Map<Integer, Member> getGroupMembers() {
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
    public void removeEventMember(Member member) {
        for(Member m : eventMembers) {
            if(m.equals(member)) {
                eventMembers.remove(m);
                memberAndAmount.remove(groupMembers.get(m.getId()));
                break;
            }
        }
    }
    public void addEventMember(Member m) {
        eventMembers.add(m);
    }

    public List<Member> getEventMembers() {
        return eventMembers;
    }

    public void createEvent() {
        model.createNewGroupEvent(group.getGroupName(), memberAndAmount, eventName, payer, debtUpdater, date);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setMemberPayment(int amount, int id) {
        if(amount <= 0)
            return;
        memberAndAmount.put(groupMembers.get(id), amount);
        System.out.println(memberAndAmount);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Group getGroup() {
        return group;
    }
}
