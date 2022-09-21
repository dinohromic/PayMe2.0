package com.example.payme20;

public class Debt {
    private IDebtHolder debtTo;
    private double debtAmount;

    public Debt(IDebtHolder debtHolder){
        this.debtTo = debtHolder;
        this.debtAmount = 0.0;
    }
    public void updateDebt(double debtAdd) {
        debtAmount += debtAdd;
    }
    public void resetDebt() {
        debtAmount = 0;
    }

    public double getDebtAmount(){
        return this.debtAmount;
    }

    public IDebtHolder getDebtHolder() {
        return debtTo;
    }
}
