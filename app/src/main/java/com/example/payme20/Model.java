package com.example.payme20;

import java.util.HashMap;

public class Model {

    public void createEvent(Group group, HashMap<Member, Double> debtMap, String eventName, Member payer, IDebtUpdater iDebtUpdater){
        Event event = Factory.createEvent(eventName, debtMap, payer, iDebtUpdater);
        group.addEvent(event);
        group.addEventDebtToGroup(event.getDebtList());
    }
}
