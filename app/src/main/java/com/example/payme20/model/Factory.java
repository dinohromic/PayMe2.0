/* The responsibility of this class is to enable top-level modules to use the
concrete constructors within the model without having top-level dependencies on low-level modules.
* */
package com.example.payme20.model;

import java.util.List;
import java.util.Map;

/**
 * The Factory class creates objects
 */
public final class Factory {

    /**
     * Creates a group and returns the reference
     * @param groupName name of the group
     * @param membersList list of all members that will belong to the group
     * @return returns the created group
     */
    public static Group createGroup(String groupName, List<Member> membersList){
        return new Group(groupName, membersList);
    }

    /**
     * Creates a member and returns the reference
     * @param memberName name of the member
     * @param phoneNumber phone number of the member
     * @param id
     * @return returns the created member
     */
    public static Member createMember(String memberName, String phoneNumber, int id){
        return new Member(memberName, phoneNumber, id);
    }

    /**
     * Creates an event and returns the reference
     * @param eventName name of the event
     * @param memberAndAmount an Map containing a Member instances as a key and an amount as the value
     * @param payer an instance of Member of who paid for the event
     * @param debtUpdater the calculation to use to calculate event expenditures
     * @param date the date the event took place
     * @return returns the created event
     */
    public static Event createEvent(String eventName, Map<Member, Integer> memberAndAmount, Member payer, ICreateDebtList debtUpdater, String date){
        return new Event(eventName, memberAndAmount, payer, debtUpdater, date);
    }
}
