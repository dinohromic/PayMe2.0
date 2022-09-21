package com.example.payme20;

import java.util.Map;

public class SplitDebtUpdater implements IDebtUpdater {
    @Override
    public void updateDebts(Map<Member, Double> eventMemberPaidAmount, Member payer) {
        double totalGroupCost = calcTotalGroupCost(eventMemberPaidAmount);
        double splitCost = calcDividedCost(totalGroupCost, eventMemberPaidAmount.size());
        updateEventMemberDebts(eventMemberPaidAmount, payer, splitCost);
    }

    private double calcTotalGroupCost(Map<Member, Double> eventMemberPaidAmount) {
        double total = 0;
        for (Map.Entry<Member, Double> mapValue: eventMemberPaidAmount.entrySet()) {
            total += mapValue.getValue();
        }
        return total;
    }

    private double calcDividedCost(double totalGroupCost, int memberSize){
        double dividedCost = 0;
        try{dividedCost = totalGroupCost / memberSize;}
        catch (ArithmeticException e){System.out.println("Division by zero");}
        return dividedCost;
    }

    private void updateEventMemberDebts(Map<Member, Double> eventMemberPaidAmount, Member memberToGetPaid, double amount){

        for (Map.Entry<Member, Double> debtMap: eventMemberPaidAmount.entrySet()) {
            Member memberToPay = debtMap.getKey();
            if(!(memberToPay.equals(memberToGetPaid))){
                memberToGetPaid.updateDebt(amount, memberToPay);
                memberToPay.updateDebt(-amount, memberToGetPaid);
            }

        }
    }
}
