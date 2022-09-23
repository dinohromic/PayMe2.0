package com.example.payme20.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Event {
    private Map<Member, Double> eventPaymentDetails;
    private IDebtUpdater debtUpdater;
    private boolean activeStatus;
    private List<Debt> eventDebtList;
    private String eventName;
    private Member payer;
    private String date;


    public Event(String eventName, Map eventPaymentDetails, Member payer, IDebtUpdater debtUpdater){
        this.eventName=eventName;
        this.eventPaymentDetails = eventPaymentDetails;
        this.payer = payer;
        this.activeStatus = true;
        this.debtUpdater = debtUpdater;
        this.date = getDate();
        this.eventDebtList = createEventDebts();

    }

    private String getDate(){
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat wantedDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = wantedDateFormat.format(currentDate);
        return currentDateString;
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

    public Member getPayer() {
        return payer;
    }

    public List<Debt> getDebtList(){
        return this.eventDebtList;
    }

    private List<Debt> createEventDebts() {
        return debtUpdater.updateDebts(eventPaymentDetails, payer);

    }
}
