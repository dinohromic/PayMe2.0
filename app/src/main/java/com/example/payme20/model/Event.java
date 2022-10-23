/* The responsibility of this class is to hold and process data about events.
* */
package com.example.payme20.model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.example.payme20.fileservice.MemberDeserializer;

//TODO Is this really a good description?
/**
 *  An event holds data and makes calculations with data about the event
 */
public class Event implements Serializable {
    @JsonDeserialize(keyUsing = MemberDeserializer.class)
    private Map<Member, Integer> eventPaymentDetails;
    private ICreateDebtList createDebtList;
    private boolean activeStatus;
    private List<Debt> eventDebtList;
    private String eventName;
    private Member payer;
    private String eventDate;
    private int id;


    /**
     * Create a new event
     * @param eventName is the name of the event
     * @param eventPaymentDetails is a map containing a member as a key and an integer as the value
     * @param payer the member who paid for the event
     * @param createDebtList the method of paying for the event
     * @param date the date the event took place
     */

    public Event(String eventName, Map<Member, Integer> eventPaymentDetails, Member payer, ICreateDebtList createDebtList, String date, int id){
        this.eventName=eventName;
        this.eventPaymentDetails = eventPaymentDetails;
        this.payer = payer;
        this.activeStatus = true;
        this.createDebtList = createDebtList;
        this.eventDate = date;
        this.eventDebtList = createEventDebts();
        this.id = id;
    }
    private Event() {}

    /**
     * This method is used to update the eventPaymentDetails map after deserialization
     * @param map is the new map for the details of teh event payments
     */
    public void setNewEventPaymentDetailsMap(Map <Member, Integer> map) {
        this.eventPaymentDetails = map;
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
     * Unfortunately necessary for JSON, but is private and not used in any of our code.
     * @param list is the debtList from JSON deserialization.
     */
    private void setDebtList(List<Debt> list) {
        this.eventDebtList = list;
    }

    /**
     * Creates the list of debts
     * @return returns a created list of Debt-objects
     */
    private List<Debt> createEventDebts() {
        return createDebtList.createDebtList(eventPaymentDetails, payer);

    }

    /**
     * Gets the id of the event
     * @return returns the id of the event
     */
    public int getId() {
        return this.id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Event{" +
                "eventPaymentDetails=" + eventPaymentDetails +
                ", debtUpdater=" + createDebtList +
                ", activeStatus=" + activeStatus +
                ", eventDebtList=" + eventDebtList +
                ", eventName='" + eventName + '\'' +
                ", payer=" + payer +
                ", date='" + eventDate + '\'' +
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
        return this.eventDate;
    }

    /**
     * Get the debtlist creater this class used
     * @return returns an object that implements IDebtUpdater
     */
    public ICreateDebtList getCreateDebtList() {
        return this.createDebtList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
