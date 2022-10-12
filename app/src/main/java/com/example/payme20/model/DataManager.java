package com.example.payme20.model;

import android.content.Context;

import com.example.payme20.GlobalApplication;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {


    private final Context context = GlobalApplication.getAppContext();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File groupFile;

    public DataManager(){
        String GROUPS_FILE = context.getFilesDir().getPath() + "/groups.json";
        groupFile = new File(GROUPS_FILE);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public List<Group> readGroups()  {
        List<Group> groups = new ArrayList<>();
        try {
            groups = objectMapper.readValue(groupFile, new TypeReference<List<Group>>() {});
            System.out.println(groups);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groups;
    }
    public void writeGroups(List<Group> g){
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(groupFile, g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void writeDataToJson(){
       File file = new File(getApplicationContext().getFilesDir().getPath() + "/" + "model.json");
        ObjectMapper om = new ObjectMapper();
        Member dino = new Member("dino", "0763921", 1);
        try {
            om.writeValue(file, dino);
            System.out.println("Added to file ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Member member = om.readValue(file, Member.class);
            System.out.println(member);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
