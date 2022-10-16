package com.example.payme20;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.example.payme20.model.DebtCalculator;
import com.example.payme20.model.DetailedCreateDebtList;
import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DetailDebtUpdaterTest {

    DebtCalculator debtCalculator = new DebtCalculator();
    HashMap<Member, Integer> debtMap;
    Member user1;
    Member user2;
    Member user3;
    Group group;
    Event event;

    @Before
    public void init(){
        this.debtMap = new HashMap<>();
        this.user1 = new Member("User1", "100", -1);
        this.user2 = new Member("User2", "200", -2);
        this.user3 = new Member("User3", "300", -3);
        this.group = new Group("TestGroup",new ArrayList<>());
        this.group.addNewGroupMember(user1);
        this.group.addNewGroupMember(user2);
        this.group.addNewGroupMember(user3);
        this.debtMap.put(user1, 150);
        this.debtMap.put(user2, 300);
        this.debtMap.put(user3, 120);
        this.event = new Event("Test", this.debtMap, user3, new DetailedCreateDebtList(), "");
        this.group.addEvent(this.event);
    }

    /* Test debt from user1 to user3 */
    @Test
    public void testUser3GotPositiveToUser1(){
        Map<String, Integer> debtMapUser3 = debtCalculator.calcMemberSpecificDebt(group.getGroupMembers(), this.user3, group.getDebtHandler());
        double user3DebtToUser1 = debtMapUser3.get(user1.getUserName());
        assertEquals(150, user3DebtToUser1,0.01);
    }

    /*Test debt from user3 to user2*/
    @Test
    public void testUser3GotPositiveToUser2(){
        Map<String, Integer> debtMapUser3 = debtCalculator.calcMemberSpecificDebt(group.getGroupMembers(), this.user3, group.getDebtHandler());
        double user3DebtToUser1 = debtMapUser3.get(user2.getUserName());
        assertEquals(300, user3DebtToUser1, 0.01);
    }

    /* Test debt from user2 to user3 */
     @Test
     public void testUser2GotNegativeDebtToUser3(){
         Map<String, Integer> debtMapUser2 = debtCalculator.calcMemberSpecificDebt(group.getGroupMembers(), this.user2, group.getDebtHandler());
         double user2DebtToUser3 = debtMapUser2.get(user3.getUserName());
         assertEquals(-300.0, user2DebtToUser3, 0.01);
     }

     /* Test debt from user1 to user3 */
     @Test
     public void testUser1GotNegativeDebtToUser3(){
         Map<String, Integer> debtMapUser1 = debtCalculator.calcMemberSpecificDebt(group.getGroupMembers(), this.user1, group.getDebtHandler());
         double user2DebtToUser3 = debtMapUser1.get(user3.getUserName());
         assertEquals(-150.0, user2DebtToUser3, 0.01);
     }

    @Test
    public void testTotalDebtOfUsers(){
        assertEquals(-150.0, debtCalculator.calcMemberTotalDebt(user1, group.getDebtHandler()), 0.01);
        assertEquals(-300.0, debtCalculator.calcMemberTotalDebt(user2, group.getDebtHandler()), 0.01);
        assertEquals(450.0, debtCalculator.calcMemberTotalDebt(user3, group.getDebtHandler()), 0.01);
    }

}
