package com.example.payme20.model;

import java.util.Map;

public final class Factory {
    public static Group createGroup(String groupName){
        return new Group(groupName);
    }

    public static Member createMember(String memberName, String phoneNumber){
        return new Member(memberName, phoneNumber);
    }

    public static Event createEvent(String eventName, Map<Member, Integer> memberAndAmount,Member payer, IDebtUpdater debtUpdater ){
        return new Event(eventName, memberAndAmount, payer, debtUpdater);
    }
}
