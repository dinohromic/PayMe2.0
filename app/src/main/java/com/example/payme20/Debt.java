package com.example.payme20;

public class Debt {
    private Member debtTo;
    private Member debtFrom;
    private double debtAmount;;

    public Debt(Member debtTo, Member debtFrom, double debtAmount){
        this.debtTo = debtTo;
        this.debtFrom = debtFrom;
        this.debtAmount = debtAmount;
    }

    public double getDebtAmount(){
        return this.debtAmount;
    }

    public Member getDebtTo() {
        return debtTo;
    }
    public Member getDebtFrom() {
        return debtFrom;
    }
}
