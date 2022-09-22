package com.example.payme20;

public class Debt {
    private final Member debtTo;
    private final Member debtFrom;
    private final double debtAmount;;

    public Debt(Member debtTo, Member debtFrom, double debtAmount){
        this.debtTo = debtTo;
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
