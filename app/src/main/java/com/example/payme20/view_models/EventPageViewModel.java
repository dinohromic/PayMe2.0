package com.example.payme20.view_models;

import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

import java.util.Map;

public class EventPageViewModel {
    private final Group group;
    private final Event event;
    public EventPageViewModel(Group belongsToGroup, Event event) {
        this.event = event;
        this.group = belongsToGroup;
    }

    public String getEventName() {
        return event.getEventName();
    }

    public String getEventDate() {
        return event.getEventDate();
    }

    public String getPayerName() {
        return event.getPayer().getUserName();
    }

    public String getEventPaymentType() {
        return event.getdebtUpdater().toString();
    }

    public Group getGroup() {
        return this.group;
    }

    public Map<Member, Integer> getEventPaymentDetails() {
        return event.getEventPaymentDetails();
    }

    public int getEventTotalPrice() {
        int amount = 0;
        for (Map.Entry<Member, Integer> memberMap: getEventPaymentDetails().entrySet()) {
            amount += memberMap.getValue();
        }
        return amount;
    }
}
