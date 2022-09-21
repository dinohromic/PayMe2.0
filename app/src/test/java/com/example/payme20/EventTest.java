package com.example.payme20;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTest {

    Group testGroup;
    Map<Member, Double> debtMap;
    Member payer;
    IDebtUpdater debtUpdater;
    Event testEvent;

    @Before
    public void init(){
        this.testGroup = new Group("TestGroup");
        this.debtMap = new HashMap<>();
        this.debtUpdater = new DetailedDebtUpdater();
        this.testEvent = Factory.createEvent("TestEvent", this.debtMap, this.payer, this.debtUpdater);
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
}