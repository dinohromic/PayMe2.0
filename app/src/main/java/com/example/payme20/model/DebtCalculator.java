package com.example.payme20.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtCalculator {
    public double calcMemberTotalDebt(List<Debt> debts, Member member) {
        double totalDebt = 0;
        for(Debt d : debts) {
            if(d.getDebtTo().equals(member))
                totalDebt += d.getDebtAmount();
            else if(d.getDebtFrom().equals(member))
                totalDebt -= d.getDebtAmount();
        }
        return totalDebt;
    }
    public Map<Member, Double> calcMemberSpecificDebt(List<Debt> debts, List<Member> members, Member member) {
        Map<Member, Double> specificDebtsMap = new HashMap<>();
        initSpecificDebtsMap(specificDebtsMap, members, member);
        for(Debt d : debts) {
            Member debtFrom = d.getDebtFrom();
            Member debtTo = d.getDebtTo();
            if(debtFrom.equals(member)) {
                double previousDebt = specificDebtsMap.get(debtTo);
                double newDebt = previousDebt -= d.getDebtAmount();
                specificDebtsMap.put(debtTo, newDebt);
            }
            else if(debtTo.equals(member)) {
                double previousDebt = specificDebtsMap.get(debtFrom);
                double newDebt = previousDebt += d.getDebtAmount();
                specificDebtsMap.put(debtFrom, newDebt);
            }
        }
        return specificDebtsMap;
    }
    private void initSpecificDebtsMap(Map<Member, Double> map, List<Member> members, Member member) {
        for(Member m : members) {
            if(!m.equals(member)) {
                map.put(m, 0.0);
            }
        }
    }
}
