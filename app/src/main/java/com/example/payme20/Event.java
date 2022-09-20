package com.example.payme20;

import java.util.Map;

public class Event {
    private Map<Member, Double> eventPaymentDetails;
    private IPaymentCalculation paymentCalculation;
    private boolean eventIsActive;
    private String eventName;
    private Member payer;
    private String date;


    public Event(String eventName, Map eventPaymentDetails, Member payer){
        this.eventName=eventName;
        this.eventPaymentDetails = eventPaymentDetails;
        this.payer = payer;
        this.eventIsActive = true;
    }
    
}
