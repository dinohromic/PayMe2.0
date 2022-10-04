package com.example.payme20.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Member implements Serializable {
    private int id;
    private String userName;
    private String phoneNumber;


    public Member(String userName, String phoneNumber, int id){

        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Member{" +
                "id" + id +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
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

