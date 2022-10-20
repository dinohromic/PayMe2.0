package com.example.payme20.view_models;

import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

import java.util.Map;

public class EventPageViewModel {
    private final Group group;
    private final Event event;
    private final PayMeModel model = PayMeModel.INSTANCE;
    public EventPageViewModel(String belongsToGroup, Event event) {
        this.group = model.getGroups().get(belongsToGroup);
        this.event = findEvent(event);
    }

    private Event findEvent(Event event) {
        for (Event e : group.getGroupEvents()) {
            if(e.equals(event)) {
                return e;
            }
        }
        return null;
    }

    public String getEventName() {
        return event.getEventName();
    }

    public String getEventDate() {
        return event.getEventDate();
    }

    public String getPayerName() {
        return this.group.getGroupMembers().get(this.group.getGroupMembers().indexOf(event.getPayer())).getUserName();
    }

    public String getEventPaymentType() {
        return event.getCreateDebtList().getName();
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

    public boolean getEventActiveStatus() {
        return event.getActiveStatus();
    }
}
