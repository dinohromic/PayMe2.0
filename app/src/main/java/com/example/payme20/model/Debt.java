package com.example.payme20.model;

import android.content.Intent;

import java.io.Serializable;
import java.util.Objects;

public class Debt implements Serializable {
    private final Member debtTo;
    private final Member debtFrom;
    private final int debtAmount;

    public Debt(Member debtTo, Member debtFrom, Integer debtAmount){
        this.debtTo = Objects.requireNonNull(debtTo);
        this.debtFrom = debtFrom;
        this.debtAmount = debtAmount;
    }

    public int getDebtAmount(){
        return this.debtAmount;
    }

    public Member getDebtTo() {
        return new Member(debtTo.getUserName(), debtTo.getPhoneNumber());
    }
    public Member getDebtFrom() {
        return new Member(debtFrom.getUserName(), debtFrom.getPhoneNumber());
    }
}
