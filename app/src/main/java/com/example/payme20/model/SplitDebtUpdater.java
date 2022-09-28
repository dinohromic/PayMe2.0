package com.example.payme20.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SplitDebtUpdater implements IDebtUpdater {
    @Override
    public List<Debt> updateDebts(Map<Member, Integer> eventMemberPaidAmount, Member payer) {
        int totalGroupCost = calcTotalGroupCost(eventMemberPaidAmount);
        int splitCost = calcDividedCost(totalGroupCost, eventMemberPaidAmount.size());
        List<Member> membersList = new ArrayList<>();
        for (Map.Entry<Member, Integer> debtMap: eventMemberPaidAmount.entrySet()) {
            membersList.add(debtMap.getKey());
        }
        Map<Member, Integer> updatedMap = distributeRest(totalGroupCost, splitCost, eventMemberPaidAmount, membersList);
        return createEventDebtList(payer, updatedMap);
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
    private Map<Member, Integer> distributeRest(int totalGroupCost, int dividedcost, Map<Member, Integer> map, List<Member> membersList) {
        int memberSize = map.size();
        int rest = totalGroupCost - dividedcost*memberSize;
        Map<Member, Integer> updatedMap = new HashMap<>();
        Random r = new Random();
        List<Integer> indexList = new ArrayList<>();
        for (Map.Entry<Member, Integer> debtMap: map.entrySet()) {
            updatedMap.put(debtMap.getKey(), dividedcost);
        }
        while(indexList.size() < rest) {
            int index = r.nextInt(memberSize);
            if(!indexList.contains(index))
                indexList.add(index);
        }
        for(Integer i : indexList) {
            updatedMap.put(membersList.get(i), dividedcost + 1);
        }
        return updatedMap;
    }

    private List<Debt> createEventDebtList(Member memberToGetPaid, Map<Member, Integer> updatedMap){
        List<Debt> eventDebtList = new ArrayList<>();
        for (Map.Entry<Member, Integer> debtMap: updatedMap.entrySet()) {
            Member memberToPay = debtMap.getKey();
            if(!(memberToPay.equals(memberToGetPaid))){
                eventDebtList.add(new Debt(memberToGetPaid, memberToPay, debtMap.getValue()));
            }
        }
        return eventDebtList;
    }
}
