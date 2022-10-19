package com.example.payme20;

import android.app.Instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import com.example.payme20.model.DetailedCreateDebtList;
import com.example.payme20.model.Event;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import com.example.payme20.model.SplitCreateDebtList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RunWith(AndroidJUnit4.class)
public class PayMeModelTest extends TestCase {
    PayMeModel payMeModel = PayMeModel.INSTANCE;
    @Before
    public void init() {
        List<Member> members = createMembers();
        payMeModel.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
        payMeModel.createNewGroup("testgroup", members);
        createMembers();
        createEvent();
    }

    private void createEvent() {
        Map<Member, Integer> paymentMap = new HashMap<>();
        Member member = payMeModel.getGroups().get("testgroup").getGroupMembers().get(0);
        Member member1 = payMeModel.getGroups().get("testgroup").getGroupMembers().get(1);
        Member member2 = payMeModel.getGroups().get("testgroup").getGroupMembers().get(2);
        paymentMap.put(member, 10);
        paymentMap.put(member2, 12);
        paymentMap.put(member1, 14);
        payMeModel.createNewGroupEvent("testgroup", paymentMap, "event", payMeModel.getGroups().get("testgroup").getGroupMembers().get(1),new DetailedCreateDebtList(), "");
    }

    private List<Member> createMembers() {
        List<Member> members = new ArrayList<>();
        members.add(payMeModel.createNewMember("test", "123"));
        members.add(payMeModel.createNewMember("testMember", "123"));
        members.add(payMeModel.createNewMember("member", "123"));
        return members;
    }

    @Test
    public void testAddNewMemberToGroup() {
        payMeModel.addNewMemberToGroup(payMeModel.getGroups().get("testgroup"),"member2", "123");
        assertEquals(4, payMeModel.getGroups().get("testgroup").getGroupMembers().size());
    }
    @Test
    public void testCreateNewEvent() {
        Map<Member, Integer> paymentMap = new HashMap<>();
        Member member = payMeModel.getGroups().get("testgroup").getGroupMembers().get(0);
        Member member1 = payMeModel.getGroups().get("testgroup").getGroupMembers().get(1);
        Member member2 = payMeModel.getGroups().get("testgroup").getGroupMembers().get(2);
        paymentMap.put(member, 10);
        paymentMap.put(member2, 12);
        paymentMap.put(member1, 14);
        payMeModel.createNewGroupEvent("testgroup", paymentMap, "testevent", payMeModel.getGroups().get("testgroup").getGroupMembers().get(1),new SplitCreateDebtList(), "");
        assertEquals(2, payMeModel.getGroups().get("testgroup").getGroupEvents().size());
    }
    @Test
    public void testGetTotalDebtMember() {
        Member member = payMeModel.getGroups().get("testgroup").getGroupMembers().get(1);
        assertEquals(22, payMeModel.getTotalDebt("testgroup", member));
    }
    @Test
    public void testGetSpecificDebtsForMember() {
        Member debtFrom = payMeModel.getGroups().get("testgroup").getGroupMembers().get(0);
        Member debtTo = payMeModel.getGroups().get("testgroup").getGroupMembers().get(1);
        Map<Member, Integer> specificDebts = payMeModel.getSpecificDebts(payMeModel.getGroups().get("testgroup"), debtTo);
        assertEquals(10, (int) specificDebts.get(debtFrom));
    }
    @Test
    public void testInactivatingEvent() {
        Event e = payMeModel.getGroups().get("testgroup").getGroupEvents().get(0);
        payMeModel.inactivateEvent(e, payMeModel.getGroups().get("testgroup"));
        assertFalse(e.getActiveStatus());
    }
    @Test
    public void testInactivatingAllEventsSetsAllBalancesToZero() {
        payMeModel.inactivateAllEvents(payMeModel.getGroups().get("testgroup"));
        int i = new Random().nextInt(3);
        Member m = payMeModel.getGroups().get("testgroup").getGroupMembers().get(i);
        assertEquals(0, payMeModel.getTotalDebt("testgroup", m));
    }
    @Test
    public void testTotalExpenditureGroup() {
        assertEquals(36, payMeModel.calcTotalExpenditureForGroup(payMeModel.getGroups().get("testgroup")));
    }
    @Test
    public void testActivateEvent() {
        Event e = payMeModel.getGroups().get("testgroup").getGroupEvents().get(0);
        if(!e.getActiveStatus())
            payMeModel.activateEvent(e, payMeModel.getGroups().get("testgroup"));
        assertTrue(e.getActiveStatus());
    }
    @Test
    public void testSerializeandDeserializeGroups() {

    }

}
