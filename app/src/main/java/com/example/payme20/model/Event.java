package com.example.payme20.model;

import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Event implements Serializable {
    private final Map<Member, Integer> eventPaymentDetails;
    private final IDebtUpdater debtUpdater;
    private boolean activeStatus;
    private List<Debt> eventDebtList;
    private final String eventName;
    private final Member payer;
    private final String date;


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

    @NonNull
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

    public String getEventName() {
        return this.eventName;
    }

    public String getEventDate() {
        return this.date;
    }

    public IDebtUpdater getdebtUpdater() {
        return this.debtUpdater;
    }
}
