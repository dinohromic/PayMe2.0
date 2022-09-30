package com.example.payme20.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtCalculator implements Serializable {
    public int calcMemberTotalDebt(Member member, DebtHandler dh) {
        int totalDebt = 0;
        if(dh.getIncomingDebtsMaps().containsKey(member)) {
            for(Debt d : dh.getIncomingDebtsMaps().get(member)) {
                totalDebt += d.getDebtAmount();
            }
        }
        if(dh.getOutgoingDebtsMap().containsKey(member)) {
            for (Debt d : dh.getOutgoingDebtsMap().get(member)) {
                totalDebt -= d.getDebtAmount();
            }
        }
        return totalDebt;
    }
    public Map<Member, Integer> calcMemberSpecificDebt(List<Member> members, Member member, DebtHandler dh) {
        Map<Member, Integer> specificDebtsMap = new HashMap<>();
        initSpecificDebtsMap(specificDebtsMap, members, member);
        if(dh.getIncomingDebtsMaps().containsKey(member)) {
            for (Debt d : dh.getIncomingDebtsMaps().get(member)) {
                int previousDebt = specificDebtsMap.get(d.getDebtFrom());
                int newDebt = previousDebt + (int) d.getDebtAmount();
                specificDebtsMap.put(d.getDebtFrom(), newDebt);
            }
        }
        if(dh.getOutgoingDebtsMap().containsKey(member)) {
            for (Debt d : dh.getOutgoingDebtsMap().get(member)) {
                int previousDebt = specificDebtsMap.get(d.getDebtTo());
                int newDebt = previousDebt - (int) d.getDebtAmount();
                specificDebtsMap.put(d.getDebtTo(), newDebt);
            }
        }
        return specificDebtsMap;
    }
    private void initSpecificDebtsMap(Map<Member, Integer> map, List<Member> members, Member member) {
        for(Member m : members) {
            if(!m.equals(member)) {
                map.put(m, 0);
            }
        }
    }
}
