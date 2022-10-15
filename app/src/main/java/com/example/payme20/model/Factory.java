/* The responsibility of this class is to enable top-level modules to use the
concrete constructors within the model without having top-level dependencies on low-level modules.
* */
package com.example.payme20.model;

import java.util.List;
import java.util.Map;

public final class Factory {
    public static Group createGroup(String groupName, List<Member> membersList){
        return new Group(groupName, membersList);
    }

    public static Member createMember(String memberName, String phoneNumber){
        return new Member(memberName, phoneNumber);
    }

    public static Event createEvent(String eventName, Map<Member, Integer> memberAndAmount, Member payer, ICreateDebtList debtUpdater, String date){
        return new Event(eventName, memberAndAmount, payer, debtUpdater, date);
    }
}
