/*The responsibility of this class is to act as an facade when
top-level modules needs to access the model.
* */
package com.example.payme20.model;

import android.content.Context;

import java.util.List;
import java.util.Map;

import com.example.payme20.GlobalApplication;
import com.example.payme20.fileservice.DataHandler;
import com.example.payme20.fileservice.DataManager;
import com.example.payme20.helpers.ReMapper;

public enum PayMeModel {
    INSTANCE;
    DataHandler dataHandler = DataHandler.INSTANCE;
    DataManager dataManager = new DataManager(GlobalApplication.getAppContext());

    /**
     * Creates a new Event and adds it to the group
     * @param groupName the name of the group to which the event is to be added
     * @param eventPaymentDetails the members and the value of their part in this event
     * @param eventName the name of the event
     * @param payer the member who payed for everything
     * @param iCreateDebtList the type of debt list create
     * @param date the date for the event
     */
    public void addNewGroupEvent(String groupName, Map<Member, Integer> eventPaymentDetails, String eventName, Member payer, ICreateDebtList iCreateDebtList, String date) {
        deserializeId();
        int id = dataHandler.getId();
        Event event = Factory.createEvent(eventName, eventPaymentDetails, payer, iCreateDebtList, date, id);
        dataHandler.getGroups().get(groupName).addEvent(event);
        serializeModel();
    }

    /**
     * Gets the total debt of a specific member
     * @param groupName the group in which to find the debts
     * @param member the member whose debts are asked for
     * @return returns an integer with the total debt of this member
     */
    public int getTotalDebt(String groupName, Member member) {
        DebtCalculator dc = new DebtCalculator();
        return dc.calcMemberTotalDebt(member, dataHandler.getGroups().get(groupName).getDebtHandler());
    }

    /**
     * Get specific debts for a specific memnber
     * @param group the group in which to find the debts
     * @param member the member whose debts are asked for
     * @return returns a map with the other members in the group and the debt between them and the member
     */
    public Map<Member, Integer> getSpecificDebts(Group group, Member member) {
        DebtCalculator dc = new DebtCalculator();
        return dc.calcMemberSpecificDebt(group.getGroupMembers(), member, group.getDebtHandler());
    }

    /**
     * Inactivates an event
     * @param event the event to be inactivated
     * @param group the group in which to deactivate the event
     */
    public void inactivateEvent(Event event, Group group) {
        event.setEventInactive();
        dataHandler.getGroups().get(group.getGroupName()).removeEventDebts(event);
        serializeModel();
    }

    /**
     * Inactivates all events in a group
     * @param group the group in which all events are to be inactivated
     */
    public void inactivateAllEvents(Group group) {
        for(Event e : group.getGroupEvents()) {
            inactivateEvent(e, group);
        }
    }
    //TODO

    /**
     * Inactivates a specific member
     * @param member the member to be inactivated
     */
    public void inactivateMember(Member member) {
        member.setActiveStatus(false);
    }

    /**
     * Adds a new member to a group
     * @param group the group to which the member is to be added
     * @param name the name of the new member
     * @param num the phone number of the new member
     */
    public void addNewMemberToGroup(Group group, String name, String num){
         group.addNewGroupMember(createNewMember(name, num));
         serializeModel();
    }

    /**
     * Calculates the total expenditure for a group's active events
     * @param group the group to be calculate for
     * @return returns an integer with the total expenditure
     */
    public int calcTotalExpenditureForGroup(Group group){
        int total = 0;
        for (Event eventInGroup: group.getGroupEvents()) {
            if(eventInGroup.getActiveStatus()) {
                total += eventInGroup.totalEventCost();
            }
        }
        return total;
    }
    //TODO

    /**
     * Activates an event
     * @param event the event to be activated
     * @param group the group in which the event is to be activated
     */
    public void activateEvent(Event event, Group group) {
        event.setEventActive();
        group.addEventDebtToGroup(event.getDebtList());
        serializeModel();
    }

    /**
     * Creates a new group
     * @param groupName the name of the group
     * @param membersList the list of members which are added at creation of the group
     */
    public void createNewGroup(String groupName, List<Member> membersList) {
        deserializeModel();
        int id = dataHandler.getId();
        dataHandler.addGroup(Factory.createGroup(groupName, membersList, id));
        serializeModel();

    }
    //TODO

    /**
     * Serializes the model
     */
    public void serializeModel() {
        dataManager.writeToJSON();
    }
    //TODO

    /**
     * Deserializes the model
     */
    public void deserializeModel() {
        deserializeGroups();
        deserializeId();
    }
    private void deserializeGroups() {
        dataHandler.refreshGroups(dataManager.readGroups());
        for(Map.Entry<String, Group> groupMaps : dataHandler.getGroups().entrySet()) {
            Group g = groupMaps.getValue();
            for(Event e : g.getGroupEvents()) {
                e.setNewEventPaymentDetailsMap(ReMapper.INSTANCE.remapKeys(e.getEventPaymentDetails(), g.getGroupMembers()));
            }
        }
    }
    //TODO

    /**
     * Gets the groups available
     * @return returns a map with the name of the groups as keys and the respective groups as values
     */
    public Map<String,Group> getGroups() {
        return dataHandler.getGroups();
    }

    /**
     * Creates a new member
     * @param memberName the name of the member
     * @param memberNumber the phone number of the member
     * @return returns a new Member
     */
    public Member createNewMember(String memberName, String memberNumber) {
        deserializeId();
        int id = dataHandler.getId();
        serializeModel();
        return Factory.createMember(memberName, memberNumber, id);
    }

    private void deserializeId() {
        dataHandler.refreshId(dataManager.readId());
    }
    //TODO

    /**
     * Activates a member
     * @param member the member to be activated
     */
    public void activateMember(Member member) {
        member.setActiveStatus(true);
    }
    //TODO

    /**
     * Checks if a member can be inactivated in a group
     * @param group the group in which the member is
     * @param member the member to be inactivated
     * @return returns true if the member can be inactivated, else false
     */
    public boolean canMemberBeInactivated(Group group, Member member) {
        return !group.isMemberInActiveEvents(member);
    }

    /**
     * Only used for testing, unfortunately we could not find any other way for now
     * @param context The context used for the path where the JSON files are to be created
     */
    public void setContext(Context context) {
        this.dataManager = new DataManager(context);
    }

    public Map<String, Group> getDeserializedGroups() {
        deserializeGroups();
        return dataHandler.getGroups();
    }
}
