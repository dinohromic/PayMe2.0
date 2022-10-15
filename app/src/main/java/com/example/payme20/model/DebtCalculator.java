/* The responsibility of this class is to calculate different kinds of debt.
*/
package com.example.payme20.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The DebtCalculator calculates different kinds of debt
 */
public class DebtCalculator implements Serializable {

    /**
     * Calculates a members total debt
     * @param member to get total debt calculated
     * @param dh the debtHandler of the group the member belongs to
     * @return returns an integer of the total debt
     */
    public int calcMemberTotalDebt(Member member, DebtHandler dh) {
        int totalDebt = 0;
        totalDebt += totalDebtToReceive(member, dh);
        totalDebt += totalDebtToPay(member, dh);
        return totalDebt;
    }

    private int totalDebtToReceive(Member member, DebtHandler dh){
        int totalDebt = 0;
        if(dh.getIncomingDebtsMaps().containsKey(member.getUserName())) {
            for(Debt d : dh.getIncomingDebtsMaps().get(member.getUserName())) {
                totalDebt += d.getDebtAmount();}
        }
        return totalDebt;
    }

    private int totalDebtToPay(Member member, DebtHandler dh){
        int totalDebt = 0;
        if(dh.getOutgoingDebtsMap().containsKey(member.getUserName())) {
            for(Debt d : dh.getOutgoingDebtsMap().get(member.getUserName())) {
                totalDebt -= d.getDebtAmount();
            }
        }
        return totalDebt;
    }

    /**
     * Calculates the debt of a specific member in relation to all the members in the same group
     * @param members a list of all the members in the group
     * @param member the member whose debts are to be calculated
     * @param dh the debtHandler belonging to the group which the member is a part of
     * @return returns a map containing members as a key and a debt-value as an integer
     */
    public Map<String, Integer> calcMemberSpecificDebt(List<Member> members, Member member, DebtHandler dh) {
        Map<String, Integer> specificDebtsMap = new HashMap<>();
        initSpecificDebtsMap(specificDebtsMap, members, member);
        if(dh.getIncomingDebtsMaps().containsKey(member.getUserName())) {
            for (Debt d : dh.getIncomingDebtsMaps().get(member.getUserName())) {
                int previousDebt = specificDebtsMap.get(d.getDebtFrom().getUserName());
                int newDebt = previousDebt + (int) d.getDebtAmount();
                specificDebtsMap.put(d.getDebtFrom().getUserName(), newDebt);
            }
        }
        if(dh.getOutgoingDebtsMap().containsKey(member.getUserName())) {
            for (Debt d : dh.getOutgoingDebtsMap().get(member.getUserName())) {
                int previousDebt = specificDebtsMap.get(d.getDebtTo().getUserName());
                int newDebt = previousDebt - (int) d.getDebtAmount();
                specificDebtsMap.put(d.getDebtTo().getUserName(), newDebt);
            }
        }
        return specificDebtsMap;
    }

    /**
     * Initializes a map for debt by excluding the person the debt is to (No need to see the debt you have to yourself).
     * @param map the map to initialize
     * @param members the members to display debt to
     * @param member the member to get excluded from the map
     */
    private void initSpecificDebtsMap(Map<String, Integer> map, List<Member> members, Member member) {
        for(Member m : members) {
            if(!m.equals(member)) {
                map.put(m.getUserName(), 0);
            }
        }
    }
}
