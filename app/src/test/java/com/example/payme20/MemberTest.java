package com.example.payme20;

import static org.junit.Assert.assertEquals;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import org.junit.Before;
import org.junit.Test;

public class MemberTest {
    Group group;
    Member user1;
    Member user2;
    Member user3;
    @Before
    public void init() {
        group = Factory.createGroup("gruppTest");
        user1 = Factory.createMember("user1", "07");
        user2 = Factory.createMember("user2", "07");
        user3 = Factory.createMember("user3", "07");
        group.addNewGroupMember(user1);
        group.addNewGroupMember(user2);
        group.addNewGroupMember(user3);
    }

    @Test
    public void sadTestOfTestingSetter(){
        user1.setUserName("user_xyz");
        assertEquals("user_xyz", user1.getUserName());
    }

    @Test
    public void sadTestOfTestingSetter2(){
        user1.setPhoneNumber("112");
        assertEquals("112", user1.getPhoneNumber() );
    }
}
