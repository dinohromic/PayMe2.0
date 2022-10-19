package com.example.payme20;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.payme20.model.DebtCalculator;
import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import com.example.payme20.model.SplitCreateDebtList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplitDebtUpdaterTest {

    Map<Member, Integer> debtMap = new HashMap<>();
    DebtCalculator debtCalculator = new DebtCalculator();
    Event testEvent;
    Member user1;
    Member user2;
    Member user3;
    Group group;

    @Before
    public void init(){
        this.user1 = new Member("User1", "100", 22);
        this.user2 = new Member("User2", "200", 23);
        this.user3 = new Member("User3", "300", 24);
        this.group = new Group("TestGroup", new ArrayList<>(), 25);
        this.group.addNewGroupMember(user1);
        this.group.addNewGroupMember(user2);
        this.group.addNewGroupMember(user3);
        this.debtMap.put(user1, 332);
        this.debtMap.put(user2, 0);
        this.debtMap.put(user3, 0);
        this.testEvent= new Event("TestEvent", debtMap, user1, new SplitCreateDebtList(), "", 26);
        this.group.addEvent(testEvent);
    }

    @Test
    public void testPayerPositiveDebt(){
        int user1TotalDebt = -(debtCalculator.calcMemberTotalDebt(this.user2, group.getDebtHandler())) - debtCalculator.calcMemberTotalDebt(this.user3, group.getDebtHandler());
        assertEquals(user1TotalDebt, debtCalculator.calcMemberTotalDebt(this.user1, group.getDebtHandler()), 1);
    }


    @Test
    public void testUser2NegativeDebt(){
        assertEquals(-110, debtCalculator.calcMemberTotalDebt(this.user2, group.getDebtHandler()), 1);
    }
    @Test
    public void testUser3NegativeDebt(){
       assertEquals(-110, debtCalculator.calcMemberTotalDebt(this.user3, group.getDebtHandler()), 1);
    }
}
