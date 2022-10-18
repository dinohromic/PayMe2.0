/*The responsibility of this class is to process input-data
in order to create debts and putting them in a list to return. */

package com.example.payme20.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * SplitCreateDebtList splits the events expenditures evenly and returns them in a list
 */
public class SplitCreateDebtList implements ICreateDebtList, Serializable {

    private final String name = "Split";
    public SplitCreateDebtList() {}
    @Override
    public List<Debt> createDebtList(Map<Member, Integer> eventMemberPaidAmount, Member payer) {
        int totalGroupCost = calcTotalGroupCost(eventMemberPaidAmount);
        int splitCost = calcDividedCost(totalGroupCost, eventMemberPaidAmount.size());
        List<Member> membersList = new ArrayList<>();
        for (Map.Entry<Member, Integer> debtMap: eventMemberPaidAmount.entrySet()) {
            membersList.add(debtMap.getKey());
        }
        Map<Member, Integer> updatedMap = distributeRest(totalGroupCost, splitCost, eventMemberPaidAmount, membersList);
        return createEventDebtList(payer, updatedMap);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * calcTotalGroupCost adds all members expenditures into one whole
     * @param eventMemberPaidAmount is a map that holds members and how much they paid in integers
     * @return returns an integer of the total cost
     */
    private int calcTotalGroupCost(Map<Member, Integer> eventMemberPaidAmount) {
        int total = 0;
        for (Map.Entry<Member, Integer> mapValue: eventMemberPaidAmount.entrySet()) {
            total += mapValue.getValue();
        }
        return total;
    }

    /**
     * calcDividedCost divides the total groups cost with the amount of members
     * @param totalGroupCost the total amount the group paid for
     * @param memberSize amount of members in the event
     * @return returns a divided cost
     */
    private int calcDividedCost(int totalGroupCost, int memberSize){
        int dividedCost = 0;
        try{dividedCost = totalGroupCost / memberSize;}
        catch (ArithmeticException e){System.out.println("Division by zero in SplitDebtUpdater.Class");}
        return dividedCost;
    }

    /**
     * distributeRest allocates the rest after dividing with integer
     * @param totalGroupCost the total amount the group paid for
     * @param dividedcost divided cost of the event
     * @param map is a map that holds members and how much they paid in integers
     * @param membersList members that is part of the event
     * @return returns a map containing updated debts and members
     */
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
            if(!indexList.contains(index)) {
                indexList.add(index);
            }
        }
        for(Integer i : indexList) {
            updatedMap.put(membersList.get(i), dividedcost + 1);
        }
        return updatedMap;
    }

    /**
     * createEventDebtList creates debt-object and adds to a list
     * @param memberToGetPaid the person that paid for the event
     * @param updatedMap updated map that contains members and amount they paid in the event
     * @return returns a list of debt-objects
     */
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
