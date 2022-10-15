/* The responsibility of this class is hold data about
members, this being usernames and phone numbers.
*/
package com.example.payme20.model;


import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonValue;
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
    /**
     * Create a new member
     * @param userName Create the member with the given user name
     * @param phoneNumber Create the member with the given phone number
     */
    public Member(String userName, String phoneNumber){

        this.userName = userName;
        this.phoneNumber = phoneNumber;

    }
    public Member(String str) {
        String[] keys = str.split("and");
        this.userName = keys[0].trim();
        this.phoneNumber = keys[1].trim();
    }
    public Member() {}

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
     * Set the toString of the Member object to userName and phoneNumber
     * @return returns the toString of userName and phoneNumber
     */
    @NonNull
    @Override
    @JsonValue
    public String toString() {
        return userName + " and " + phoneNumber;
    }

    /**
     * Validates if two objects have the same value
     * @param o is of type Object
     * @return returns true or false whether the objects are equal or not
     */
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

