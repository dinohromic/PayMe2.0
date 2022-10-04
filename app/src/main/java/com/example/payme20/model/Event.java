package com.example.payme20.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Event implements Serializable {
    private Map<Member, Integer> eventPaymentDetails;
    private IDebtUpdater debtUpdater;
    private boolean activeStatus;
    private List<Debt> eventDebtList;
    private String eventName;
    private Member payer;
    private String date;


    public Event(String eventName, Map eventPaymentDetails, Member payer, IDebtUpdater debtUpdater, String date){
        this.eventName=eventName;
        this.eventPaymentDetails = eventPaymentDetails;
        this.payer = payer;
        this.activeStatus = true;
        this.debtUpdater = debtUpdater;
        this.date = date;
        this.eventDebtList = createEventDebts();

    }

    public Map<Member, Integer> getEventPaymentDetails() {
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

    public Member getPayer() {
        return payer;
    }

    public List<Debt> getDebtList(){
        return this.eventDebtList;
    }

    private List<Debt> createEventDebts() {
        return debtUpdater.updateDebts(eventPaymentDetails, payer);

    }

    @Override
    public String toString() {
        return "Event{" +
                "eventPaymentDetails=" + eventPaymentDetails +
                ", debtUpdater=" + debtUpdater +
                ", activeStatus=" + activeStatus +
                ", eventDebtList=" + eventDebtList +
                ", eventName='" + eventName + '\'' +
                ", payer=" + payer +
                ", date='" + date + '\'' +
                '}';
    }
}
