package com.example.payme20.model;

import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A member has a username, a phone number and is identified with a unique ID
 */
public class Member implements Serializable {
    private int id;
    private String userName;
    private String phoneNumber;

    /**
     * Create a new member
     * @param userName Create the member with the given user name
     * @param phoneNumber Create the member with the given phone number
     * @param id Create the member with the given ID
     */
    public Member(String userName, String phoneNumber, int id){

        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    /**
     * Get the user name of a member
     * @return returns the username of the member called upn
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the phone number of a member
     * @return returns the phone number of the member called upon
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the user name of a member
     * @param userName the new user name of the member
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Set the phone number of a member
     * @param phoneNumber the new phone number of the member
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the ID of a member
     * @return returns the ID of the member called upon
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of a member
     * @param id the new ID of the member
     */
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

