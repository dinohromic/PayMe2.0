package com.example.payme20;

import java.util.ArrayList;
import java.util.List;

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
    public void addNewGroupMember(String userName, String phoneNumber) {
        Member member = new Member(userName, phoneNumber);
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

    public void setAllEventsInactive(){
        for (Event event: this.groupEvents) {
            event.setEventInactive();
        }
    }
}
