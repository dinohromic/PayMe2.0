package com.example.payme20;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class TestDebtUpdater {

    @Test
    public void testSplittingDebt(){
        Member user1 = new Member("User1", "100");
        Member user2 = new Member("User2", "200");
        Member user3 = new Member("User3", "300");

        Map<Member, Double> debtMap = new HashMap<Member, Double>();
        debtMap.put(user1, 330.0);
        debtMap.put(user2, 0.0);
        debtMap.put(user3, 0.0);


        Group group = new Group("TestGroup");
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
        Event testEvent = new Event("TestEvent", debtMap, user1, new SplitDebtUpdater());
        group.addEvent(testEvent);


        testEvent.updateEventMemberDebts();



    }
}
