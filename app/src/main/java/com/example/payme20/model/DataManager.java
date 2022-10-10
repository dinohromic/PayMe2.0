package com.example.payme20.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    ArrayList<Member> memberArrayList;
    private Object List;

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
    public void writeData(Member memberArrayList){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("members.json"),memberArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
