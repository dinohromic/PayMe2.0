/* The responsibility of this class is to hold data about a group and being able to
modify that data, i.e adding or removing group members, adding or removing events.
*/
package com.example.payme20.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds data about the group and adds/removes members or events
 */
public class Group implements Serializable {
    private String groupName;
    private List<Member> groupMembers;
    private List<Event> groupEvents;
    private DebtHandler debtHandler;
    private int id;

    /**
     * Create a new Group
     * @param groupName the name of the group
     * @param membersList a list of members to be in the group
     */
    public Group(String groupName, List<Member> membersList, int id) {
        this.groupName = groupName;
        this.groupEvents = new ArrayList<>();
        this.groupMembers = membersList;
        this.debtHandler = new DebtHandler();
        this.id = id;
    }
    public Group() {}

    public String getGroupName() {
        return groupName;
    }

    public List<Member> getGroupMembers() {
        return groupMembers;
    }

    public List<Event> getGroupEvents() {
        return groupEvents;
    }

    public DebtHandler getDebtHandler() {
        return debtHandler;
    }

    public int getId() {
        return id;
    }

    public void addNewGroupMember(Member member) {
        groupMembers.add(member);
    }

    /**
     * Removes a member from the group but only if the member isn't part of any active event
     * @param member the member to remove from the group
     * @return returns true if the member was removed (not in any active events) and false if member was not remove (in active event)
     */
    public boolean inactivateGroupMember(Member member){
        boolean allEventsWithMemberInactive = true;
        List<Event> eventsWithThisMember = new ArrayList<>();
        for(Event e : groupEvents) {
            if(e.getEventPaymentDetails().containsKey(member)) {
                eventsWithThisMember.add(e);
            }
        }
        for(Event e : eventsWithThisMember) {
            if(e.getActiveStatus()) {
                allEventsWithMemberInactive = false;
                break;
            }
        }
        if(allEventsWithMemberInactive) {
            member.setActiveStatus(false);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds all the debts from a event to the groups debtHandler
     * @param eventDebts a list of debts from a event
     */
    public void addEventDebtToGroup(List<Debt> eventDebts){
        for(Debt d : eventDebts) {
            debtHandler.addDebt(d);
        }
    }

    public void addEvent(Event event) {
        groupEvents.add(event);
        addEventDebtToGroup(event.getDebtList());
    }

    public void setAllEventsInactive(){
        for (Event event: this.groupEvents) {
            event.setEventInactive();
        }
    }

    public void removeEventDebts(Event event) {
        for(Debt d : event.getDebtList()) {
            debtHandler.removeDebt(d);
        }
    }
}
