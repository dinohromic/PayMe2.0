package com.example.payme20.model;

import java.util.ArrayList;
import java.util.List;

public enum DataHandler {
    INSTANCE;
    private List<Group> groups = new ArrayList<>();

    public void addGroup(Group g) {
        groups.add(g);
    }
    public List<Group> getGroups() {
        return groups;
    }


    public void refreshGroups(List<Group> groups) {
        this.groups.clear();
        this.groups.addAll(groups);
    }
}
