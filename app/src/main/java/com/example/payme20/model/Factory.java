package com.example.payme20.model;

import java.util.ArrayList;
import java.util.Map;

public final class Factory {
    public static Group createGroup(String groupName, ArrayList<Member> membersList){
        return new Group(groupName, membersList);
    }

    public static Member createMember(String memberName, String phoneNumber, int id){
        return new Member(memberName, phoneNumber, id);
    }

    public static Event createEvent(String eventName, Map<Member, Integer> memberAndAmount,Member payer, IDebtUpdater debtUpdater ){
        return new Event(eventName, memberAndAmount, payer, debtUpdater);
    }
}
