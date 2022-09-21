package com.example.payme20;

import java.util.Map;

public class Event {
    private Map<Member, Double> eventPaymentDetails;
    private IDebtUpdater debtUpdater;
    private boolean eventIsActive;
    private String eventName;
    private Member payer;
    private String date;


    public Event(String eventName, Map eventPaymentDetails, Member payer, IDebtUpdater debtUpdater){
        this.eventName=eventName;
        this.eventPaymentDetails = eventPaymentDetails;
        this.payer = payer;
        this.eventIsActive = true;
        this.debtUpdater = debtUpdater;
    }

    public boolean isEventIsActive() {
        return eventIsActive;
    }

    public String getEventName() {
        return eventName;
    }

    public Member getPayer() {
        return payer;
    }

    public String getDate() {
        return date;
    }

    public void updateEventMemberDebts() {
        debtUpdater.updateDebts(eventPaymentDetails, payer);
    }
}
