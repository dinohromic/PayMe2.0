package com.example.payme20.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {
    private String groupName;
    private List<Member> groupMembers;
    private List<Event> groupEvents;
    private DebtHandler debtHandler;

    public Group(String groupName, List<Member> membersList) {
        this.groupName = groupName;
        this.groupEvents = new ArrayList<>();
        this.groupMembers = membersList;
        this.debtHandler = new DebtHandler();
    }
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

    public void addNewGroupMember(Member member) {

        groupMembers.add(member);
    }

    public boolean removeGroupMember(Member member){
        boolean allEventsWithMemberInactive = true;
        List<Event> eventsWithThisMember = new ArrayList<>();
        for(Event e : groupEvents) {
            if(e.getEventPaymentDetails().containsKey(member))
                eventsWithThisMember.add(e);
        }
        for(Event e : eventsWithThisMember) {
            if(e.getActiveStatus()) {
                allEventsWithMemberInactive = false;
                break;
            }
        }
        if(allEventsWithMemberInactive) {
            this.groupMembers.remove(member);
            return true;
        }
        else
            return false;
    }

    private void addEventDebtToGroup(List<Debt> eventDebts){
        for(Debt d : eventDebts)
            debtHandler.addDebt(d);
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

    public void setEventInactive(Event event) {
        event.setEventInactive();
    }


    public void removeEventDebts(Event event) {
        for(Debt d : event.getDebtList())
            debtHandler.removeDebt(d);
    }

}
