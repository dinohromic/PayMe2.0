package com.example.payme20;

import java.util.Map;

public class Factory {
    public static Group createGroup(String groupName){
        return new Group(groupName);
    }

    public static Member createMember(String memberName, String phoneNumber){
        return new Member(memberName, phoneNumber);
    }

    public static Event createEvent(String eventName, Map<Member, Double> memberAndAmount,Member payer, IDebtUpdater debtUpdater ){
        return new Event(eventName, memberAndAmount, payer, debtUpdater);
    }
}
