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
        this.groupMembers.remove(member);
        for (Member m : groupMembers) {
            m.addMemberToDebtList(member);
            member.addMemberToDebtList(m);
        }
    }

    public void setAllEventsInactive(){
        for (Event event: this.groupEvents) {
            event.setEventInactive();
        }
    }
}
