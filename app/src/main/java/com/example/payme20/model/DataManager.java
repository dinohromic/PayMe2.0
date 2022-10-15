package com.example.payme20.model;

import android.content.Context;

import com.example.payme20.GlobalApplication;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataManager manages the reading and writing of a groups data and stores it in a JSON file
 */
public class DataManager {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File groupFile;
    private final File idFile;
    private final DataHandler dh = DataHandler.INSTANCE;

    /**
     * Creates a Datamanager
     */
    public DataManager(){
        Context context = GlobalApplication.getAppContext();
        String GROUPS_FILE = context.getFilesDir().getPath() + "/groups.json";
        String ID_FILE = context.getFilesDir().getPath() + "/id.json";
        groupFile = new File(GROUPS_FILE);
        idFile = new File(ID_FILE);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
    public void writeToJSON() {
        writeGroups();
        writeId();
    }
    private void writeId() {
        int id = dh.getId();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(idFile, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int readId() {
        int id = 0;
        try {
            id = objectMapper.readValue(idFile, new TypeReference<Integer>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
    /**
     * Reads in a group from the JSON file
     * @return returns the group that has been read from the file
     */
    public Map<String, Group> readGroups()  {
        Map<String, Group> groups = new HashMap<>();
        try {
            groups = objectMapper.readValue(groupFile, new TypeReference<Map<String, Group>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groups;
    }

    /**
     * Writes a group to a JSON file
     */
    private void writeGroups(){
        Map<String, Group> groups = dh.getGroups();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(groupFile, groups);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
