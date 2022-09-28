package com.example.payme20;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.payme20.model.DetailedDebtUpdater;
import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.Model;

import java.util.HashMap;
import java.util.Map;


public class DetailDebtUpdaterTest {

    Member user1;
    Member user2;
    Member user3;
    Group group;
    HashMap<Member, Integer> debtMap = new HashMap<Member, Integer>();
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
        debtMap.put(user1, 150);
        debtMap.put(user2, 300);
        debtMap.put(user3, 120);
        model.createNewGroupEvent(group, debtMap, "Test", user3, new DetailedDebtUpdater());
    }

    /*
    * User3's debt is positive to User1 */
    @Test
    public void testUser3GotPositiveToUser1(){
        Map<Member, Integer> debtMapUser3 = model.getSpecificDebts(group, user3);
        double user3DebtToUser1 = debtMapUser3.get(user1);
        assertEquals(150, user3DebtToUser1,0.01);
    }

    /**
     *user3's debt is positive to user2 */
    @Test
    public void testUser3GotPositiveToUser2(){
        Map<Member, Integer> debtMapUser3 = model.getSpecificDebts(group, user3);
        double user3DebtToUser1 = debtMapUser3.get(user2);
        assertEquals(300, user3DebtToUser1, 0.01);
    }

    /*
    * User2 got negative debt to user3*/
    @Test
    public void testUser2GotNegativeDebtToUser3(){
        Map<Member, Integer> debtMapuser2 = model.getSpecificDebts(group, user2);
        double user2DebtToUser3 = debtMapuser2.get(user3);
        assertEquals(-300.0, user2DebtToUser3, 0.01);
    }

    /**
    */
    @Test
    public void testUser1GotNegativeDebtToUser3(){
        Map<Member, Integer> debtMapUser1 = model.getSpecificDebts(group, user1);
        double user2DebtToUser3 = debtMapUser1.get(user3);
        assertEquals(-150.0, user2DebtToUser3, 0.01);
    }

    /*
    * */
    @Test
    public void testTotalDebtOfUsers(){
        assertEquals(-150.0, model.getTotalDebt(group, user1), 0.01);
        assertEquals(-300.0, model.getTotalDebt(group, user2), 0.01);
        assertEquals(450.0, model.getTotalDebt(group, user3), 0.01);
    }
}
