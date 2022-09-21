package com.example.payme20;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class GroupTest {
    Group group;
    Member user1;
    Member user2;
    Member user3;
    @Before
    public void init() {
        group = Factory.createGroup("gruppTest");
        user1 = Factory.createMember("user1", "07");
        user2 = Factory.createMember("user3", "07");
        user3 = Factory.createMember("user2", "07");
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
    }

    @Test
    public void testRemoveMember() {
        group.removeGroupMember(user1);
        assertFalse(group.getGroupMembers().contains(user1));
        //assertEquals(2, group.getGroupMembers().size());
    }
    @Test
    public void testAddMember() {
        Member member = Factory.createMember("member", "07");
        group.addNewGroupMember(member);
        assertTrue(group.getGroupMembers().contains(member));
    }
    @Test
    public void testAddMemberGetsAddedToDebtsList() {
        Member member = Factory.createMember("member", "07");
        group.addNewGroupMember(member);
        List<Debt> debtList = group.getGroupMembers().get(0).getDebtList();
        assertEquals(debtList.get(debtList.size() - 1).getDebtHolder(), member);

    }
}