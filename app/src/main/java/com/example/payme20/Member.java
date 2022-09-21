package com.example.payme20;

import java.util.ArrayList;
import java.util.List;

public class Member implements IMember {
    private String userName;
    private String phoneNumber;
    private List<Debt> debtList = new ArrayList<>();


    public Member(String userName, String phoneNumber){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        // Ändra konstruktorn för debt också
        //this.debt = new Debt(this, new HashMap<>());
    }

    public List<Debt> getDebtList() {
        return debtList;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

