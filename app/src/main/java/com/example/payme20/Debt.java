package com.example.payme20;

public class Debt {
    private IDebtHolder debtTo;
    private double debtAmount;

    public Debt(IDebtHolder debtHolder){
        this.debtTo = debtHolder;
        this.debtAmount = 0.0;
    }
    private void updateDebt(Member main, Double amount, Member reciver){
      //  main

    }

    public double getDebtAmount(){
        return this.debtAmount;
    }
}
