package com.example.payme20;

import java.util.HashMap;

public class Member implements IMember {
    private String userName;
    private String phoneNumber;
    private Debt debt;


    public Member(String userName, String phoneNumber){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        // Ändra konstruktorn för debt också
        this.debt = new Debt(this, new HashMap<>());
    }

    public Debt getDebt() {
        return debt;
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

