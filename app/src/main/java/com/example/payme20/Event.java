package com.example.payme20;

import java.util.Map;

public class Event {
    private Map<Member, Double> eventPaymentDetails;
    private IDebtUpdater paymentCalculation;
    private boolean eventIsActive;
    private String eventName;
    private Member payer;
    private String date;


    public Event(String eventName, Map eventPaymentDetails, Member payer, IDebtUpdater paymentCalculation){
        this.eventName=eventName;
        this.eventPaymentDetails = eventPaymentDetails;
        this.payer = payer;
        this.eventIsActive = true;
        this.paymentCalculation = paymentCalculation;
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

    public void calculateEventExpenditures() {
        paymentCalculation.calculateEventExpenditures(eventPaymentDetails, payer);
    }


}
