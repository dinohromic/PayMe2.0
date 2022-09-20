package com.example.payme20;

public class Member implements IMember {
    private String userName;
    private String phoneNumber;
    private Debt debt;


    public Member(String userName, String phoneNumber, Debt debt){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.debt = debt;
    }

    public Debt getDebt() {
        return debt;
    }


    public void setDebt(Debt debt) {
        this.debt = debt;
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

