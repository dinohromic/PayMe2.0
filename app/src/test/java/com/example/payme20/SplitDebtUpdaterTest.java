package com.example.payme20;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitDebtUpdaterTest {

    Member user1;
    Member user2;
    Member user3;
    Group group;
    Map<Member, Double> debtMap = new HashMap<Member, Double>();
    Event testEvent;
    Model model = new Model();

    @Before
    public void init(){
        user1 = new Member("User1", "100");
        user2 = new Member("User2", "200");
        user3 = new Member("User3", "300");
        group = new Group("TestGroup");
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
        debtMap.put(user1, 330.0);
        debtMap.put(user2, 0.0);
        debtMap.put(user3, 0.0);
        this.testEvent= new Event("TestEvent", debtMap, user1, new SplitDebtUpdater());
        group.addEvent(testEvent);
    }
    @Test
    public void testPayerPositiveDebt(){
        assertEquals(220.0,model.getTotalDebt(group,user1), 0.1 );
    }
    @Test
    public void testUser2NegativeDebt(){
        assertEquals(-110.0, model.getTotalDebt(group, user2), 0.1);
    }
    @Test
    public void testUser3NegativeDebt(){
        assertEquals(-110.0, model.getTotalDebt(group, user3), 0.1);
    }
}
