package com.example.payme20;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Group {
    private String groupName;
    private List<Member> groupMembers;
    private List<Event> groupEvents;
    private List<Debt> debts;

    public Group(String groupName) {
        this.groupName = groupName;
        this.groupEvents = new ArrayList<>();
        this.groupMembers = new ArrayList<>();
        this.debts = new ArrayList<>();
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

    public void addEventDebtToGroup(List<Debt> eventDebts){
        this.debts.addAll(eventDebts);
    }

    public void addEvent(Event event) {
        groupEvents.add(event);
        debts.addAll(event.getDebtList());
    }

    public void setAllEventsInactive(){
        for (Event event: this.groupEvents) {
            event.setEventInactive();
        }
    }

    public void setEventInactive(Event event) {
        event.setEventInactive();
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public void removeEventDebts(Event event) {
        debts.removeAll(event.getDebtList());
    }
}
