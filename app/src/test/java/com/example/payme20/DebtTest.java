package com.example.payme20;

import com.example.payme20.model.Debt;
import com.example.payme20.model.Member;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DebtTest {
    private Debt debt;
    private Debt otherDebt;
    private Member member;
    private Member otherMember;
    @Before
    public void init() {
        member = new Member("hej", "123", 0);
        otherMember = new Member("test", "123", 3);
        debt = new Debt(member, otherMember, 100);
        otherDebt = new Debt(otherMember, member, 30);
    }

    @Test
    public void testDebtShouldNotEqualOtherDebt() {
        assertFalse(debt.equals(otherDebt));
    }
    @Test
    public void testDebtShouldNotEqualAMember() {
        assertFalse(otherDebt.equals(member));
    }
}
