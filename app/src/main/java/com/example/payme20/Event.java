package com.example.payme20;

import java.util.Map;

public class Event {
    private Map<Member, Double> eventPaymentDetails;
    private IPaymentCalculation paymentCalculation;
    private boolean eventIsActive;
    private String eventName;
    private Member payer;
    private String date;
}
