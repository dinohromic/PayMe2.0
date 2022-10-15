/*The responsibility of this class is to act as an facade when
top-level modules needs to access the model.
* */
package com.example.payme20.model;

import java.util.List;
import java.util.Map;

public enum PayMeModel {
    INSTANCE;
    DataHandler dataHandler = DataHandler.INSTANCE;
    DataManager dataManager = new DataManager();

    PayMeModel(){
    }

    public void createNewGroupEvent(String groupName, Map<Member, Integer> debtMap, String eventName, Member payer, ICreateDebtList iCreateDebtList, String date) {
        Event event = Factory.createEvent(eventName, debtMap, payer, iCreateDebtList, date);
        dataHandler.getGroups().get(groupName).addEvent(event);
        serializeModel();
    }

    public int getTotalDebt(String groupName, Member member) {
        DebtCalculator dc = new DebtCalculator();
        return dc.calcMemberTotalDebt(member, dataHandler.getGroups().get(groupName).getDebtHandler());
    }

    public Map<String, Integer> getSpecificDebts(Group group, Member member) {
        DebtCalculator dc = new DebtCalculator();
        return dc.calcMemberSpecificDebt(group.getGroupMembers(), member, group.getDebtHandler());
    }
    public void inactivateEvent(Event event, Group group) {
        event.setEventInactive();
        dataHandler.getGroups().get(group.getGroupName()).removeEventDebts(event);
        serializeModel();
    }
    public void inactivateAllEvents(Group group) {
        for(Event e : group.getGroupEvents()) {
            if(e.getActiveStatus()) {
                e.setEventInactive();
                group.removeEventDebts(e);
            }
        }
    }
    public boolean removeMember(Group group, Member member) {
        return group.removeGroupMember(member); //Vad ska den returna?
    }

    public void addNewMemberToGroup(Group group, String name, String num){
         group.addNewGroupMember(createNewMember(name, num));
    }

    public int calcTotalExpenditureForGroup(Group group){
        int total = 0;
        for (Event eventInGroup: group.getGroupEvents()) {
            if(eventInGroup.getActiveStatus())
                total += eventInGroup.totalEventCost();
        }
        return total;
    }

    public void activateEvent(Event event, Group group) {
        event.setEventActive();
        group.addEventDebtToGroup(event.getDebtList());
        serializeModel();
    }

    public void createNewGroup(String groupName, List<Member> membersList) {
        dataHandler.addGroup(Factory.createGroup(groupName, membersList));
        serializeModel();
    }
    public void serializeModel() {
        dataManager.writeToJSON();
    }
    public void deserializeGroups() {
        dataHandler.refreshGroups(dataManager.readGroups());
    }
    public Map<String,Group> getGroups() {
        deserializeGroups();
        return dataHandler.getGroups();
    }

    public Member createNewMember(String memberName, String memberNumber) {
        int id = dataHandler.getId();
        return Factory.createMember(memberName, memberNumber, id);
    }
}
