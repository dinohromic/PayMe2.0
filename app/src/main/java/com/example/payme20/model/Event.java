package com.example.payme20.model;

import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

//TODO Is this really a good description?
/**
 *  An event holds data and makes calculations with data about the event
 */
public class Event implements Serializable {
    private final Map<Member, Integer> eventPaymentDetails;
    private final ICreateDebtList debtUpdater;
    private boolean activeStatus;
    private List<Debt> eventDebtList;
    private final String eventName;
    private final Member payer;
    private final String date;


    /**
     * Create a new event
     * @param eventName is the name of the event
     * @param eventPaymentDetails is a map containing a member as a key and an integer as the value
     * @param payer the member who paid for the event
     * @param debtUpdater the method of paying for the event
     * @param date the date the event took place
     */
    public Event(String eventName, Map eventPaymentDetails, Member payer, ICreateDebtList debtUpdater, String date){
        this.eventName=eventName;
        this.eventPaymentDetails = eventPaymentDetails;
        this.payer = payer;
        this.activeStatus = true;
        this.debtUpdater = debtUpdater;
        this.date = date;
        this.eventDebtList = createEventDebts();
    }

    /**
     * Return the map with payment-details from the event
     * @return returns a map containing members as a key and values as integers
     */
    public Map<Member, Integer> getEventPaymentDetails() {
        return eventPaymentDetails;
    }

    /**
     * Get the status of the events activity
     * @return returns a boolean, true if event is active and false if event i inactive
     */
    public boolean getActiveStatus() {
        return activeStatus;
    }

    /**
     * Set the event as inactive
     */
    public void setEventInactive(){
        this.activeStatus = false;
    }

    /**
     * Set the event as active
     */
    public void setEventActive(){
        this.activeStatus = true;
    }

    /**
     * Get the member who paid for the event
     * @return returns a Member-object
     */
    public Member getPayer() {
        return payer;
    }

    /**
     * Get the list of debts that the event holds
     * @return the list containing Debt-objects
     */
    public List<Debt> getDebtList(){
        return this.eventDebtList;
    }

    /**
     * Creates the list of debts
     * @return returns a created list of Debt-objects
     */
    private List<Debt> createEventDebts() {
        return debtUpdater.createDebtList(eventPaymentDetails, payer);

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


    /**
     * Get the name of the event
     * @return returns a string of the events name
     */
    public String getEventName() {
        return this.eventName;
    }

    /**
     * Get the date that the event took place
     * @return returns a string with the date
     */
    public String getEventDate() {
        return this.date;
    }

    /**
     * Get the debtUpdater this class used
     * @return returns an object that implements IDebtUpdater
     */
    public ICreateDebtList getdebtUpdater() {
        return this.debtUpdater;
    }

    /**
     * Calculates a total expenditure for all event participants
     * @return returns an integer holding the total event cost
     */
    public int totalEventCost(){
        int total = 0;
        for (Map.Entry<Member, Integer> eventPaymentDetail: this.eventPaymentDetails.entrySet()) {
            total += eventPaymentDetail.getValue();
        }
        return total;
    }
}
