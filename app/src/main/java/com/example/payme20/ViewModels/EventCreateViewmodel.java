package com.example.payme20.viewmodels;

import com.example.payme20.model.Factory;
import com.example.payme20.model.IDebtUpdater;
import com.example.payme20.model.Member;

import java.util.HashMap;
import java.util.Map;

public class EventCreateViewmodel {
    Map<Member, Integer> memberAndAmount = new HashMap<>();
    public void createEvent(String eventName, Member payer, IDebtUpdater debtUpdater) {
        Factory.createEvent(eventName, memberAndAmount, payer, debtUpdater);
    }
    public void addMemberAndAmountMap(Member member, int amount) {
        memberAndAmount.put(member, amount);
    }
}
