package com.example.payme20.model;

import java.util.Objects;

public class Debt {
    private final Member debtTo;
    private final Member debtFrom;
    private final double debtAmount; // FÖRBJUDET MED DOUBLE, använda long eller int

    public Debt(Member debtTo, Member debtFrom, double debtAmount){
        this.debtTo = Objects.requireNonNull(debtTo);
        this.debtFrom = debtFrom;
        this.debtAmount = debtAmount;
    }

    public double getDebtAmount(){
        return this.debtAmount;
    }

    public Member getDebtTo() {
        return new Member(debtTo.getUserName(), debtTo.getPhoneNumber());
    }
    public Member getDebtFrom() {
        return new Member(debtFrom.getUserName(), debtFrom.getPhoneNumber());
    }
}
