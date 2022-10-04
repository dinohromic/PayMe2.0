package com.example.payme20.view_models;

import androidx.lifecycle.ViewModel;

import com.example.payme20.model.Debt;
import com.example.payme20.model.DetailedDebtUpdater;
import com.example.payme20.model.Group;
import com.example.payme20.model.IDebtUpdater;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import com.example.payme20.model.SplitDebtUpdater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventCreateViewmodel extends ViewModel {
    private Map<Member, Integer> memberAndAmount = new HashMap<>();
    private String eventName;
    private IDebtUpdater debtUpdater;
    private Member payer;
    private Group group;
    private final Map<String, Member> groupMembers = new HashMap<>();
    private List<String> eventMembers = new ArrayList<>();
    private String date;
    PayMeModel model = PayMeModel.INSTANCE;

    public EventCreateViewmodel(Group group) {
        this.group = group;
        this.debtUpdater = new SplitDebtUpdater();
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
                debtUpdater = new SplitDebtUpdater();
                break;
            case "detailed":
                debtUpdater = new DetailedDebtUpdater();
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
        model.createNewGroupEvent(group, memberAndAmount, eventName, payer, debtUpdater, date);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setMemberPayment(int amount, String name) {
        memberAndAmount.put(groupMembers.get(name), amount);
        System.out.println(memberAndAmount);
    }

    public void setDate(String date) {
        this.date = date;
    }

}
