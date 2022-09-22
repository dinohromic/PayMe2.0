package com.example.payme20;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;


public class DetailDebtUpdaterTest {

    Member user1;
    Member user2;
    Member user3;
    Group group;
    HashMap<Member, Double> debtMap = new HashMap<Member, Double>();
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
        debtMap.put(user1, 150.0);
        debtMap.put(user2, 300.0);
        debtMap.put(user3, 120.0);
        model.createNewGroupEvent(group, debtMap, "Test", user3, new DetailedDebtUpdater());
    }

    /*
    * User3's debt is positive to both User1 and User2*/
    @Test
    public void testUser3GotPositiveDebt(){
        Map<Member, Double> debtMapUser3 = model.getSpecificDebts(group, user3);
            double user3DebtToUser1 = debtMapUser3.get(user3);
            assertEquals(150.0, user3DebtToUser1,0.1);
    }

    /*
    * User2 owes 0 to User1
    * User2 owes 300:- to User3 since User3 payed for the event.*/
    @Test
    public void testUser2GotNegativeDebt(){

    }

    /**
    * User1 owes o to User2
    * User1 owes 120 to user3 since user3 payed
    */
    @Test
    public void testUser1GotNegativeDebt(){

    }
}
