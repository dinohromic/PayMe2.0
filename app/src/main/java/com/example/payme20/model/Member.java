/* The responsibility of this class is hold data about
members, this being usernames and phone numbers.
*/
package com.example.payme20.model;


import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;
import org.json.JSONObject;

/**
 * A member has a username, a phone number and is identified with a unique ID
 */
public class Member implements Serializable {

    private String userName;
    private String phoneNumber;
    private final int id;
    private boolean activeStatus;
    /**
     * Create a new member
     * @param userName Create the member with the given user name
     * @param phoneNumber Create the member with the given phone number
     */
    public Member(String userName, String phoneNumber, int id){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.activeStatus = true;
    }

    /**
     * Used when deserializing a Member
     * @param str the toString of the member, from JSON file
     */
    public Member(String str) {
        String[] keys = str.split("and");
        this.userName = keys[0].trim();
        this.phoneNumber = keys[1].trim();
        this.id = Integer.parseInt(keys[2].trim());
        this.activeStatus = Boolean.parseBoolean(keys[3].trim());
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
     * Get the id of a member
     * @return returns the id of the member
     */
    public int getId() {
        return id;
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
     * Gets the active status of the member
     * @return returns true if the member is active, else false
     */
    public boolean getActiveStatus() {
        return activeStatus;
    }

    /**
     * Sets the activestatus for a member
     * @param activeStatus true if member is to be set active, and false if member is to be set inactive
     */
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    /**
     * Set the toString of the Member object to userName and phoneNumber
     * @return returns the toString of userName and phoneNumber
     */
    @NonNull
    @Override
    @JsonValue
    public String toString() {
        return userName + " and " + phoneNumber + " and " + id + " and " + activeStatus;
    }

    /**
     * Validates if two objects have the same id
     * @param o is of type Object
     * @return returns true or false whether the objects are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return id == member.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

