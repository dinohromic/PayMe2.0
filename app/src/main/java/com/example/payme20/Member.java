package com.example.payme20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Member implements IDebtHolder {
    private String userName;
    private String phoneNumber;
    private List<Debt> debtList;
    private Map<Member, Double> debtMap;

    public Member(String userName, String phoneNumber){
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.debtList = new ArrayList<>();
        this.debtMap = new HashMap<>();
        // Ändra konstruktorn för debt också
        //this.debt = new Debt(this, new HashMap<>());
    }

    public void addMemberToDebtList(IDebtHolder member) {
        debtList.add(new Debt(member));
    }
    public double getTotalDebt() {
        double debt = 0;
        if(debtList.size() != 0) {
            for (Debt d : debtList) {
                debt += d.getDebtAmount();
            }
        }
        return debt;
    }
    public void updateDebt(double debtAdd, Member member) {
        for(Debt d : debtList) {
            if(d.getDebtHolder().equals(member));
                d.updateDebt(debtAdd);
        }
    }
    public void resetDebts() {
        for(Debt d : debtList) {
            d.resetDebt();
        }
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

