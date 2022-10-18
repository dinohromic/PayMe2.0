package com.example.payme20;

import org.junit.*;
import static org.junit.Assert.*;

import com.example.payme20.model.DebtCalculator;
import com.example.payme20.model.DetailedCreateDebtList;
import com.example.payme20.model.Event;
import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.SplitCreateDebtList;

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
    @Before
    public void init() {
        group = Factory.createGroup("gruppTest",new ArrayList<>());
        user1 = Factory.createMember("user1", "07", -1);
        user2 = Factory.createMember("user2", "07", -2);
        user3 = Factory.createMember("user3", "07", -3);

        Map<Member, Integer> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20);
        eventPaymentMap.put(user2, 30);
        eventPaymentMap.put(user3, 50);

        event = new Event("event", eventPaymentMap, user1, new SplitCreateDebtList(), "");
        event1 = new Event("event", eventPaymentMap, user2, new DetailedCreateDebtList(), "");
        event2 = new Event("event", eventPaymentMap, user3, new SplitCreateDebtList(), "");

        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
    }

    @Test
    public void testRemoveMember() {
        group.isMemberInActiveEvents(user1);
        assertFalse(group.getGroupMembers().contains(user1));
        assertEquals(2, group.getGroupMembers().size());
    }


    @Test
    public void testAddMember() {
        Member member = Factory.createMember("member", "07", -4);
        group.addNewGroupMember(member);
        assertTrue(group.getGroupMembers().contains(member));
    }

    @Test
    public void testRemoveMemberWhoIsInActiveEvent() {
        Map<Member, Integer> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20);
        eventPaymentMap.put(user2, 30);
        eventPaymentMap.put(user3, 50);
        Event event = new Event("event", eventPaymentMap, user1, new SplitCreateDebtList(), "");
        group.addEvent(event);
        this.group.isMemberInActiveEvents(this.user2);
        assertTrue(group.getGroupMembers().contains(user2));
    }

    @Test
    public void testRemoveMemberWhoIsInInactiveEvent() {
        Map<Member, Integer> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20);
        eventPaymentMap.put(user2, 30);
        eventPaymentMap.put(user3, 50);

        Event event = new Event("event", eventPaymentMap, user1, new SplitCreateDebtList(), "");
        group.addEvent(event);
        event.setEventInactive();
        group.isMemberInActiveEvents(user2);
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

        Event event1 = new Event("Event1", eventPaymentMap, user1, new SplitCreateDebtList(), "");
        Event event2 = new Event("Event2", eventPaymentMap, user2, new DetailedCreateDebtList(), "");
        Event event3 = new Event("Event3", eventPaymentMap, user3, new SplitCreateDebtList(), "");
        group.addEvent(event1);
        group.addEvent(event2);
        group.addEvent(event3);

        event1.setEventInactive();
        group.removeEventDebts(event1);

        DebtCalculator debtCalculator = new DebtCalculator();
        assertEquals(-50.0, debtCalculator.calcMemberTotalDebt(this.user1, group.getDebtHandler()), 1);

    }

    @Test
    public void testInactivatingAllEvents(){
        group.addEvent(event);
        group.addEvent(event1);
        group.addEvent(event2);
        for (Event event :group.getGroupEvents()) {
            event.setEventInactive();
        }
        for (Event event :this.group.getGroupEvents()) {
            assertFalse(event.getActiveStatus());
        }
    }

    @Test
    public void totalGroupExpenditure(){
        this.group.addEvent(this.event);
        this.group.addEvent(this.event1);
        this.group.addEvent(this.event2);

        int total = 0;
        for (Event eventInGroup: group.getGroupEvents()) {
            if(eventInGroup.getActiveStatus())
                total += eventInGroup.totalEventCost();
        }

        assertEquals(300, total);

    }
}