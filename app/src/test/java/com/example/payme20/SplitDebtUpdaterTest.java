package com.example.payme20;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import com.example.payme20.model.SplitDebtUpdater;

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
    PayMeModel payMeModel = new PayMeModel();

    @Before
    public void init(){
        user1 = new Member("User1", "100");
        user2 = new Member("User2", "200");
        user3 = new Member("User3", "300");
        group = new Group("TestGroup", new ArrayList<>());
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
        debtMap.put(user1, 332);
        debtMap.put(user2, 0);
        debtMap.put(user3, 0);
        this.testEvent= new Event("TestEvent", debtMap, user1, new SplitDebtUpdater());
        group.addEvent(testEvent);
    }
    @Test
    public void testPayerPositiveDebt(){
        System.out.println(payMeModel.getTotalDebt(group, user1));
        System.out.println(payMeModel.getTotalDebt(group, user2));
        System.out.println(payMeModel.getTotalDebt(group, user3));
        int user1TotalDebt = -(payMeModel.getTotalDebt(group, user2) + payMeModel.getTotalDebt(group, user2));
        assertEquals(user1TotalDebt, payMeModel.getTotalDebt(group,user1), 1); // Hur testa detta?
    }
    @Test
    public void testUser2NegativeDebt(){
        assertEquals(-110, payMeModel.getTotalDebt(group, user2), 1);
    }
    @Test
    public void testUser3NegativeDebt(){
        assertEquals(-110, payMeModel.getTotalDebt(group, user3), 1);
    }
}
