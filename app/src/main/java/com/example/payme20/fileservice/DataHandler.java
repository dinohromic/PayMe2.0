package com.example.payme20.fileservice;

import com.example.payme20.model.Group;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds the map of the groups and the id number.
 */
public enum DataHandler {
    INSTANCE;
    private Map<String, Group> groups = new HashMap<>();
    private int id = 0;

    public void addGroup(Group g) {
        groups.put(g.getGroupName(), g);
    }
    public Map<String,Group> getGroups() {
        return groups;
    }
    public int getId() {
        return id++;
    }


    public void refreshGroups(Map<String, Group> map) {
        this.groups.clear();
        this.groups.putAll(map);
    }

    public void refreshId(int readId) {
        this.id = readId;
    }
}
