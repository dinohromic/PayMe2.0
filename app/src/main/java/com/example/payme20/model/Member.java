package com.example.payme20.model;

import java.util.Objects;

public class Member {
    private String userName;
    private String phoneNumber;

    public Member(String userName, String phoneNumber){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(userName, member.userName) && Objects.equals(phoneNumber, member.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, phoneNumber);
    }
}

