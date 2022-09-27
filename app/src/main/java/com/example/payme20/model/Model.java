package com.example.payme20.model;

import java.util.Map;

public class Model {


    public void createNewGroupEvent(Group group, Map<Member, Double> debtMap, String eventName, Member payer, IDebtUpdater iDebtUpdater) {
        Event event = Factory.createEvent(eventName, debtMap, payer, iDebtUpdater);
        group.addEvent(event);
    }

    public int getTotalDebt(Group group, Member member) {
        DebtCalculator dc = new DebtCalculator();
        return dc.calcMemberTotalDebt(member, group.getDebtHandler());
    }

    public Map<Member, Integer> getSpecificDebts(Group group, Member member) {
        DebtCalculator dc = new DebtCalculator();
        return dc.calcMemberSpecificDebt(group.getGroupMembers(), member, group.getDebtHandler());
    }
    public void inactivateEvent(Event event, Group group) {
        event.setEventInactive();
        group.removeEventDebts(event);
    }
    public void inactivateAllEvents(Group group) {
        for(Event e : group.getGroupEvents()) {
            if(e.getActiveStatus()) {
                e.setEventInactive();
                group.removeEventDebts(e);
            }
        }
    }
    public boolean removeMember(Group group, Member member) {
        return group.removeGroupMember(member); //Vad ska den returna?
    }
}
