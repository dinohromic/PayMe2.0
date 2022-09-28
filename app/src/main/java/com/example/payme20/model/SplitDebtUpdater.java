package com.example.payme20.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitDebtUpdater implements IDebtUpdater {
    @Override
    public List<Debt> updateDebts(Map<Member, Integer> eventMemberPaidAmount, Member payer) {
        int totalGroupCost = calcTotalGroupCost(eventMemberPaidAmount);
        int splitCost = calcDividedCost(totalGroupCost, eventMemberPaidAmount.size());
        return createEventDebtList(eventMemberPaidAmount, payer, splitCost);
    }

    private int calcTotalGroupCost(Map<Member, Integer> eventMemberPaidAmount) {
        int total = 0;
        for (Map.Entry<Member, Integer> mapValue: eventMemberPaidAmount.entrySet()) {
            total += mapValue.getValue();
        }
        return total;
    }

    private int calcDividedCost(int totalGroupCost, int memberSize){
        int dividedCost = 0;
        try{dividedCost = totalGroupCost / memberSize;}
        catch (ArithmeticException e){System.out.println("Division by zero in SplitDebtUpdater.Class");}
        return dividedCost;
    }

    private List<Debt> createEventDebtList(Map<Member, Integer> eventMemberPaidAmount, Member memberToGetPaid, int debtAmount){
        List<Debt> eventDebtList = new ArrayList<>();
        for (Map.Entry<Member, Integer> debtMap: eventMemberPaidAmount.entrySet()) {
            Member memberToPay = debtMap.getKey();
            if(!(memberToPay.equals(memberToGetPaid))){
                eventDebtList.add(new Debt(memberToGetPaid, memberToPay, debtAmount));
            }
        }
        return eventDebtList;
    }
}
