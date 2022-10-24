package com.example.payme20;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.payme20.model.Debt;
import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MemberTest {
    Group group;
    Member user1;
    Member user2;
    Member user3;
    @Before
    public void init() {
        group = Factory.createGroup("gruppTest", new ArrayList<>(), 13);
        user1 = Factory.createMember("user1", "07", 15);
        user2 = Factory.createMember("user2", "07", 17);
        user3 = Factory.createMember("user3", "07", 19);
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
    }

    @Test
    public void testSetMemberUserName(){
        user1.setUserName("user_xyz");
        assertEquals("user_xyz", user1.getUserName());
    }

    @Test
    public void testSetMemberPhoneNumber(){
        user1.setPhoneNumber("112");
        assertEquals("112", user1.getPhoneNumber() );
    }
    @Test
    public void testToString() {
        assertTrue(user1.toString().contains(user1.getUserName() + " and"));
    }
    @Test
    public void testMemberIsNotEqualToObjectOfOtherClass() {
        assertFalse(user2.equals(new Debt(user1, user3, 10)));
    }
    @Test
    public void testMemberNotEqualToNull() {
        assertFalse(user1.equals(null));
    }
    @Test
    public void testMemberActiveStatus() {
        user1.setActiveStatus(false);
        assertFalse(user1.getActiveStatus());
    }
    @Test
    public void testDeserializingConstructor() {
        Member member = new Member("test and 123 and 0 and true");
        assertEquals("test", member.getUserName());
    }
}
