package com.example.payme20.model;

import java.io.Serializable;
import java.util.Objects;

public class Debt implements Serializable {
    private final Member debtTo;
    private final Member debtFrom;
    private final int debtAmount;

    public Debt(Member debtTo, Member debtFrom, Integer debtAmount){
        this.debtTo = Objects.requireNonNull(debtTo);
        this.debtFrom = Objects.requireNonNull(debtFrom);
        this.debtAmount = debtAmount;
    }

    public int getDebtAmount(){
        return this.debtAmount;
    }

    public Member getDebtTo() {
        return new Member(debtTo.getUserName(), debtTo.getPhoneNumber(), debtTo.getId());
    }
    public Member getDebtFrom() {
        return new Member(debtFrom.getUserName(), debtFrom.getPhoneNumber(),debtTo.getId());
    }
}
