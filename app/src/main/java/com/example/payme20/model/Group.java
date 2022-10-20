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
    private Group() {}

    /**
     * Gets the name of the group
     * @return the groupname
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Gets the members in the group
     * @return the groupmembers as a list
     */
    public List<Member> getGroupMembers() {
        return groupMembers;
    }

    /**
     * Gets the events in the group
     * @return the groupevents as a list
     */
    public List<Event> getGroupEvents() {
        return groupEvents;
    }

    /**
     * Get the Debthandler of the group
     * @return the group's debthandler
     */
    public DebtHandler getDebtHandler() {
        return debtHandler;
    }

    /**
     * Get the id of the group
     * @return the group's id
     */
    public int getId() {
        return id;
    }

    /**
     * Adds a Member to the groups list of members
     * @param member the member to be added
     */
    public void addNewGroupMember(Member member) {
        groupMembers.add(member);
    }

    /**
     * Removes a member from the group but only if the member isn't part of any active event
     * @param member the member to remove from the group
     * @return returns true if the member was removed (not in any active events) and false if member was not remove (in active event)
     */
    public boolean isMemberInActiveEvents(Member member){
        for(Event e : groupEvents) {
            if(e.getEventPaymentDetails().containsKey(member) && e.getActiveStatus()) {
                return true;
            }
        }
        return false;
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

    /**
     * Adds an event to the groups list of events. It also adds the debts of that event to the groups total debts
     * @param event teh event to be added
     */
    public void addEvent(Event event) {
        groupEvents.add(event);
        addEventDebtToGroup(event.getDebtList());
    }

    /**
     * Inactivates all events in the group
     */
    public void setAllEventsInactive(){
        for (Event event: this.groupEvents) {
            event.setEventInactive();
        }
    }

    /**
     * Removes the debts for a specific event from the groups total debts
     * @param event the event which debts is to be removed
     */
    public void removeEventDebts(Event event) {
        for(Debt d : event.getDebtList()) {
            debtHandler.removeDebt(d);
        }
    }
}
