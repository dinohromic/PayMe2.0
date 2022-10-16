package com.example.payme20;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import com.example.payme20.model.SplitCreateDebtList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplitDebtUpdaterTest {

    Member user1;
    Member user2;
    Member user3;
    Group group;
    Map<Member, Integer> debtMap = new HashMap<Member, Integer>();
    Event testEvent;
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    @Before
    public void init(){
        user1 = new Member("User1", "100", -1);
        user2 = new Member("User2", "200", -1);
        user3 = new Member("User3", "300", -1);
        group = new Group("TestGroup", new ArrayList<>());
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
        debtMap.put(user1, 332);
        debtMap.put(user2, 0);
        debtMap.put(user3, 0);
        this.testEvent= new Event("TestEvent", debtMap, user1, new SplitCreateDebtList(), "");
        group.addEvent(testEvent);
    }
    @Test
    public void testPayerPositiveDebt(){
        System.out.println(payMeModel.getTotalDebt(group.getGroupName(), user1));
        System.out.println(payMeModel.getTotalDebt(group.getGroupName(), user2));
        System.out.println(payMeModel.getTotalDebt(group.getGroupName(), user3));
        int user1TotalDebt = -(payMeModel.getTotalDebt(group.getGroupName(), user2) + payMeModel.getTotalDebt(group.getGroupName(), user2));
        assertEquals(user1TotalDebt, payMeModel.getTotalDebt(group.getGroupName(),user1), 1); // Hur testa detta?
    }
    @Test
    public void testUser2NegativeDebt(){
        assertEquals(-110, payMeModel.getTotalDebt(group.getGroupName(), user2), 1);
    }
    @Test
    public void testUser3NegativeDebt(){
        assertEquals(-110, payMeModel.getTotalDebt(group.getGroupName(), user3), 1);
    }
}
