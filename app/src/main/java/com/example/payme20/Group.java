package com.example.payme20;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Group {
    private String groupName;
    private List<Member> groupMembers;
    private List<Event> groupEvents;

    public Group(String groupName) {
        this.groupName = groupName;
        this.groupEvents = new ArrayList<>();
        this.groupMembers = new ArrayList<>();
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
        if(groupMembers.size() != 0) {
            for (Member m : groupMembers) {
                m.addMemberToDebtList(member);
                member.addMemberToDebtList(m);
            }
        }
        groupMembers.add(member);
    }

    public void removeGroupMember(Member member){
        boolean allEventsWithMemberInactive = true;
        List<Event> eventsWithThisMember = new ArrayList<>();
        for(Event e : groupEvents) {
            if (e.getEventPaymentDetails().containsKey(member))
                eventsWithThisMember.add(e);
        }
        for(Event e : eventsWithThisMember) {
            if(e.getActiveStatus()) {
                allEventsWithMemberInactive = false;
                break;
            }
            allEventsWithMemberInactive = !e.getActiveStatus();
        }
        if(allEventsWithMemberInactive) {
            this.groupMembers.remove(member);
            for (Member m : groupMembers) {
                m.removeMemberFromDebtList(member);
            }
        }
//        else
//            ge förklaring att event måste markeras som klara först
    }

    public void addEvent(Event event) {
        groupEvents.add(event);
    }

    public void setAllEventsInactive(){
        for (Event event: this.groupEvents) {
            event.setEventInactive();
        }
    }
}
