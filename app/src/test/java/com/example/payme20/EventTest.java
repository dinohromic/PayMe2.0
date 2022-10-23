package com.example.payme20;

import org.junit.*;
import static org.junit.Assert.*;

import com.example.payme20.model.DetailedCreateDebtList;
import com.example.payme20.model.Event;
import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.ICreateDebtList;
import com.example.payme20.model.Member;
import com.example.payme20.model.SplitCreateDebtList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTest {

    Group testGroup;
    Map<Member, Integer> debtMap;
    Member payer;
    ICreateDebtList debtUpdater;
    Event testEvent;

    @Before
    public void init(){
        this.testGroup = new Group("TestGroup", new ArrayList<>(), 0);
        this.debtMap = new HashMap<>();
        Member member1 = (new Member("u1", "3", -1));
        Member member2 = (new Member("u2", "2", -2));
        Member member3 = (new Member("u3", "1", -3));
        this.debtMap.put(member2, 39);
        this.debtMap.put(member1, 41);
        this.debtMap.put(member3, 20);
        this.payer = member1;
        this.debtUpdater = new DetailedCreateDebtList();
        this.testEvent = Factory.createEvent("TestEvent", this.debtMap, this.payer, this.debtUpdater, "2022-10-10", 0);
        this.testGroup.addEvent(testEvent);
    }

    @Test
    public void testEventCreation(){
        List<Event> eventsInGroup = this.testGroup.getGroupEvents();
        assertEquals(eventsInGroup.get(0), this.testEvent);
    }

    @Test
    public void testPayer(){
        assertEquals(testEvent.getPayer(), this.payer);
    }

    @Test
    public void testActiveEvent(){
        assertTrue(testEvent.getActiveStatus());
        testEvent.setEventInactive();
        assertFalse(testEvent.getActiveStatus());
        testEvent.setEventActive();
        assertTrue(testEvent.getActiveStatus());
    }

    @Test
    public void totalEventCost(){
        assertEquals(100, this.testEvent.totalEventCost());
    }
    @Test
    public void testSetNewEventPaymentDetailsMap() {
        Map<Member, Integer> newMap = new HashMap<>();
        newMap.put(new Member("u1", "3", -1), 30);
        newMap.put(new Member("u2", "2", -2), 40);
        newMap.put(new Member("u3", "1", -3), 50);
        testEvent.setNewEventPaymentDetailsMap(newMap);
        assertEquals(120, testEvent.totalEventCost());
    }
    @Test
    public void testGetEventId() {
        assertEquals(0, this.testEvent.getId());
    }
    @Test
    public void testGetEventDate() {
        assertEquals("2022-10-10", this.testEvent.getEventDate());
    }
    @Test
    public void testGetEventName() {
        assertEquals("TestEvent", this.testEvent.getEventName());
    }
    @Test
    public void testGetCreateDebtList() {
        assertEquals(testEvent.getCreateDebtList().getName(), new DetailedCreateDebtList().getName());
        Event event = Factory.createEvent("test", this.debtMap, payer, new SplitCreateDebtList(), "2022-10-10", 7);
        assertEquals(event.getCreateDebtList().getName(), new SplitCreateDebtList().getName());
    }
    @Test
    public void testEventNotEqualToOtherObject() {
        assertFalse(testEvent.equals(new Member("test", "123", 7)));
    }
    @Test
    public void testEventWithDifferentIdNotEquals() {
        Event event = Factory.createEvent("TestEvent", this.debtMap, this.payer, new DetailedCreateDebtList(), "2022-10-10", 1);
        assertFalse(testEvent.equals(event));
    }
    @Test
    public void testToString() {
        assertTrue(testEvent.toString().contains("activeStatus=" + testEvent.getActiveStatus()));
    }
    @Test
    public void testHashCode() {
        Event event = Factory.createEvent("TestEvent", this.debtMap, this.payer, new DetailedCreateDebtList(), "2022-10-10", 1);
        assertNotEquals(event.hashCode(), testEvent.hashCode());
    }
}
