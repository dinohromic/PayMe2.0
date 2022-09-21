package com.example.payme20;

import java.util.Map;

public class Event {
    private Map<Member, Double> eventPaymentDetails;
    private IDebtUpdater debtUpdater;
    private boolean activeStatus;
    private String eventName;
    private Member payer;
    private String date;


    public Event(String eventName, Map eventPaymentDetails, Member payer, IDebtUpdater debtUpdater){
        this.eventName=eventName;
        this.eventPaymentDetails = eventPaymentDetails;
        this.payer = payer;
        this.activeStatus = true;
        this.debtUpdater = debtUpdater;
    }

    public Map<Member, Double> getEventPaymentDetails() {
        return eventPaymentDetails;
    }

    public boolean getActiveStatus() {
        return activeStatus;
    }

    public void setEventInactive(){
        this.activeStatus = false;
    }

    public void setEventActive(){
        this.activeStatus = true;
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
