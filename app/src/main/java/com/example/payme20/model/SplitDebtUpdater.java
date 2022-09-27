package com.example.payme20.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitDebtUpdater implements IDebtUpdater {
    @Override
    public List<Debt> updateDebts(Map<Member, Double> eventMemberPaidAmount, Member payer) {
        double totalGroupCost = calcTotalGroupCost(eventMemberPaidAmount);
        double splitCost = calcDividedCost(totalGroupCost, eventMemberPaidAmount.size());
        return createEventDebtList(eventMemberPaidAmount, payer, splitCost);
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
        catch (ArithmeticException e){System.out.println("Division by zero in SplitDebtUpdater.Class");}
        return dividedCost;
    }

    private List<Debt> createEventDebtList(Map<Member, Double> eventMemberPaidAmount, Member memberToGetPaid, double debtAmount){
        List<Debt> eventDebtList = new ArrayList<>();
        for (Map.Entry<Member, Double> debtMap: eventMemberPaidAmount.entrySet()) {
            Member memberToPay = debtMap.getKey();
            if(!(memberToPay.equals(memberToGetPaid))){
                eventDebtList.add(new Debt(memberToGetPaid, memberToPay, debtAmount));
            }
        }
        return eventDebtList;
    }
}
