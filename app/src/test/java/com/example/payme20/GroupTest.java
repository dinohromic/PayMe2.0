package com.example.payme20;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GroupTest {
    Group group;
    Member user1;
    Member user2;
    Member user3;
    @Before
    public void init() {
        group = Factory.createGroup("gruppTest");
        user1 = Factory.createMember("user1", "07");
        user2 = Factory.createMember("user3", "07");
        user3 = Factory.createMember("user2", "07");
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
    }

    @Test
    public void testRemoveMember() {
        group.removeGroupMember(user1);
        assertFalse(group.getGroupMembers().contains(user1));
        assertEquals(2, group.getGroupMembers().size());
    }
    @Test
    public void testAddMember() {
        Member member = Factory.createMember("member", "07");
        group.addNewGroupMember(member);
        assertTrue(group.getGroupMembers().contains(member));
    }
    @Test
    public void testRemoveMemberWhoIsInActiveEvent() {
        Map<Member, Double> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20.0);
        eventPaymentMap.put(user2, 30.0);
        eventPaymentMap.put(user3, 50.0);

        Event event = new Event("event", eventPaymentMap, user1, new SplitDebtUpdater());
        group.addEvent(event);
        group.removeGroupMember(user2);
        assertTrue(group.getGroupMembers().contains(user2));
    }
    @Test
    public void testRemoveMemberWhoIsInInactiveEvent() {
        Map<Member, Double> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20.0);
        eventPaymentMap.put(user2, 30.0);
        eventPaymentMap.put(user3, 50.0);

        Event event = new Event("event", eventPaymentMap, user1, new SplitDebtUpdater());
        group.addEvent(event);
        group.setEventInactive(event);
        group.removeGroupMember(user2);
        assertFalse(group.getGroupMembers().contains(user2));
    }
    @Test
    public void testSetAllEventsInactive() {
        Map<Member, Double> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20.0);
        eventPaymentMap.put(user2, 30.0);
        eventPaymentMap.put(user3, 50.0);

        Event event = new Event("event", eventPaymentMap, user1, new SplitDebtUpdater());
        Event event1 = new Event("event", eventPaymentMap, user2, new DetailedDebtUpdater());
        Event event2 = new Event("event", eventPaymentMap, user3, new SplitDebtUpdater());
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
}