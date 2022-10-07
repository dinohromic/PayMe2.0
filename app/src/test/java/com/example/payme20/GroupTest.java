package com.example.payme20;

import org.junit.*;
import static org.junit.Assert.*;

import com.example.payme20.model.DetailedDebtUpdater;
import com.example.payme20.model.Event;
import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import com.example.payme20.model.SplitDebtUpdater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GroupTest {
    Group group;
    Member user1;
    Member user2;
    Member user3;
    Event event;
    Event event1;
    Event event2;
    PayMeModel payMeModel;
    @Before
    public void init() {
        group = Factory.createGroup("gruppTest",new ArrayList<>());
        user1 = Factory.createMember("user1", "07", -1);
        user2 = Factory.createMember("user2", "07", -1);
        user3 = Factory.createMember("user3", "07", -1);

        Map<Member, Integer> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20);
        eventPaymentMap.put(user2, 30);
        eventPaymentMap.put(user3, 50);

        event = new Event("event", eventPaymentMap, user1, new SplitDebtUpdater(), "");
        event1 = new Event("event", eventPaymentMap, user2, new DetailedDebtUpdater(), "");
        event2 = new Event("event", eventPaymentMap, user3, new SplitDebtUpdater(), "");

        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
        payMeModel = PayMeModel.INSTANCE;
    }

    @Test
    public void testRemoveMember() {
        group.removeGroupMember(user1);
        assertFalse(group.getGroupMembers().contains(user1));
        assertEquals(2, group.getGroupMembers().size());
    }
    @Test
    public void testAddMember() {
        Member member = Factory.createMember("member", "07", -1);
        payMeModel.addMember(this.group, member);
        assertTrue(group.getGroupMembers().contains(member));
    }
    @Test
    public void testRemoveMemberWhoIsInActiveEvent() {
        Map<Member, Integer> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20);
        eventPaymentMap.put(user2, 30);
        eventPaymentMap.put(user3, 50);
        Event event = new Event("event", eventPaymentMap, user1, new SplitDebtUpdater(), "");
        group.addEvent(event);
        payMeModel.removeMember(this.group, this.user2);
        assertTrue(group.getGroupMembers().contains(user2));
    }
    @Test
    public void testRemoveMemberWhoIsInInactiveEvent() {
        Map<Member, Integer> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20);
        eventPaymentMap.put(user2, 30);
        eventPaymentMap.put(user3, 50);

        Event event = new Event("event", eventPaymentMap, user1, new SplitDebtUpdater(), "");
        group.addEvent(event);
        event.setEventInactive();
        group.removeGroupMember(user2);
        assertFalse(group.getGroupMembers().contains(user2));
    }
    @Test
    public void testSetAllEventsInactive() {
        group.addEvent(event);
        group.addEvent(event1);
        group.addEvent(event2);
        Random random = new Random();
        int i = random.nextInt(group.getGroupMembers().size());
        group.setAllEventsInactive();
        assertFalse(group.getGroupEvents().get(i).getActiveStatus());
    }
    @Test
    public void testGetGroupName() {
        assertEquals("gruppTest", group.getGroupName());
    }
    @Test
    public void testRemoveEventDebts() {
        Map<Member, Integer> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20);
        eventPaymentMap.put(user2, 30);
        eventPaymentMap.put(user3, 40);

        payMeModel.createNewGroupEvent(group, eventPaymentMap, "event", user1, new SplitDebtUpdater(), "");
        payMeModel.createNewGroupEvent(group, eventPaymentMap, "event", user2, new DetailedDebtUpdater(), "");
        payMeModel.createNewGroupEvent(group, eventPaymentMap, "event", user3, new SplitDebtUpdater(), "");

        payMeModel.inactivateEvent(group.getGroupEvents().get(0), group);
        assertEquals(-50.0, payMeModel.getTotalDebt(group, user1), 1);

    }

    @Test
    public void testInactivatingAllEvents(){
        group.addEvent(event);
        group.addEvent(event1);
        group.addEvent(event2);
        payMeModel.inactivateAllEvents(this.group);

        for (Event event :this.group.getGroupEvents()) {
            assertFalse(event.getActiveStatus());
        }
    }

    @Test
    public void totalGroupExpenditure(){
        this.group.addEvent(this.event);
        this.group.addEvent(this.event1);
        this.group.addEvent(this.event2);
        assertEquals(300, payMeModel.calcTotalExpenditureForGroup(this.group));

    }
}