package com.example.payme20.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtCalculator {
    public int calcMemberTotalDebt(Member member, DebtHandler dh) {
        int totalDebt = 0;
        if(dh.getToMap().containsKey(member)) {
            for(Debt d : dh.getToMap().get(member)) {
                totalDebt += d.getDebtAmount();
            }
        }
        if(dh.getFromMap().containsKey(member)) {
            for (Debt d : dh.getFromMap().get(member)) {
                totalDebt -= d.getDebtAmount();
            }
        }
        return totalDebt;
    }
    public Map<Member, Integer> calcMemberSpecificDebt(List<Member> members, Member member, DebtHandler dh) {
        Map<Member, Integer> specificDebtsMap = new HashMap<>();
        initSpecificDebtsMap(specificDebtsMap, members, member);
        if(dh.getToMap().containsKey(member)) {
            for (Debt d : dh.getToMap().get(member)) {
                int previousDebt = specificDebtsMap.get(d.getDebtFrom());
                int newDebt = previousDebt + (int) d.getDebtAmount();
                specificDebtsMap.put(d.getDebtFrom(), newDebt);
            }
        }
        if(dh.getFromMap().containsKey(member)) {
            for (Debt d : dh.getFromMap().get(member)) {
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
