package com.example.payme20.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DataHandler {
    INSTANCE;
    private Map<String,Group> groups = new HashMap<>();

    public void addGroup(Group g) {
        groups.put(g.getGroupName(), g);
    }
    public Map<String,Group> getGroups() {
        return groups;
    }


    public void refreshGroups(Map<String, Group> map) {
        this.groups.clear();
        this.groups.putAll(map);
    }
}
