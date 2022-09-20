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
}
