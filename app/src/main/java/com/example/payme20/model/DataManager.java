package com.example.payme20.model;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    ArrayList<Member> memberArrayList;
    private Object List;
    Context context;

    public DataManager(){

    }

    public Member readData()  {
        Member members = new Member();
        try {
            members = new ObjectMapper().readerFor(Member.class).readValue(new File("members.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }
    public void writeData(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("members.json"),o);
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
