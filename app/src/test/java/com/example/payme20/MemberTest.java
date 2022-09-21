package com.example.payme20;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MemberTest {
    Group group;
    Member user1;
    Member user2;
    Member user3;
    @Before
    public void init() {
        group = Factory.createGroup("gruppTest");
        user1 = Factory.createMember("user1", "07");
        user2 = Factory.createMember("user2", "07");
        user3 = Factory.createMember("user3", "07");
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
    }

    @Test
    public void testGetTotalDebt() {
        Map<Member, Double> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20.0);
        eventPaymentMap.put(user2, 30.0);
        eventPaymentMap.put(user3, 40.0);
        Event event = new Event("event", eventPaymentMap, user1, new SplitDebtUpdater());
        event.updateEventMemberDebts();
        assertEquals(60, user1.getTotalDebt(), 0.0001);
    }
    @Test
    public void testResetDebts() {
        Map<Member, Double> eventPaymentMap = new HashMap<>();
        eventPaymentMap.put(user1, 20.0);
        eventPaymentMap.put(user2, 30.0);
        eventPaymentMap.put(user3, 40.0);
        Event event = Factory.createEvent("event", eventPaymentMap, user1, new SplitDebtUpdater());
        event.updateEventMemberDebts();
        user1.resetDebts();
        assertEquals(0, user1.getTotalDebt(), 0.0001);
    }
}
