package com.example.payme20;

import java.util.HashMap;
import java.util.Map;

public class Model {

    public void createEvent(Group group, HashMap<Member, Double> debtMap, String eventName, Member payer, IDebtUpdater iDebtUpdater) {
        Event event = Factory.createEvent(eventName, debtMap, payer, iDebtUpdater);
        group.addEvent(event);
        group.addEventDebtToGroup(event.getDebtList());
    }

    public double getTotalDebt(Group group, Member member) {
        DebtCalculator dc = new DebtCalculator();
        return dc.calcMemberTotalDebt(group.getDebts(), member);
    }

    public Map<Member, Double> getSpecificDebts(Group group, Member member) {
        DebtCalculator dc = new DebtCalculator();
        return dc.calcMemberSpecificDebt(group.getDebts(), group.getGroupMembers(), member);
    }
}
