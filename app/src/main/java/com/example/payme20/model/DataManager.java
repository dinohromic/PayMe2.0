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

public class DataManager {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File groupFile;

    public DataManager(){
        Context context = GlobalApplication.getAppContext();
        String GROUPS_FILE = context.getFilesDir().getPath() + "/groups.json";
        groupFile = new File(GROUPS_FILE);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public Map<String, Group> readGroups()  {
        SimpleModule sm = new SimpleModule("GroupDeserializer", new Version(1, 0, 0, null, null, null));
        sm.addDeserializer(Group.class, new GroupDeserializer());
        //objectMapper.registerModule(sm);
        Map<String, Group> groups = new HashMap<>();
        try {
            groups = objectMapper.readValue(groupFile, new TypeReference<Map<String, Group>>() {});
            //System.out.println(groups.get(0).getGroupMembers().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groups;
    }
    public void writeGroups(Map<String, Group> g){
        SimpleModule sm = new SimpleModule("GroupSerializer", new Version(1, 0, 0, null, null, null));
        sm.addSerializer(Group.class, new GroupSerializer());
        //objectMapper.registerModule(sm);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(groupFile, g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
