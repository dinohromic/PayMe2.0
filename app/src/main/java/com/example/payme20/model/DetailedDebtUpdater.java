package com.example.payme20.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailedDebtUpdater implements IDebtUpdater, Serializable {
    @Override
    public List<Debt> updateDebts(Map<Member, Integer> eventMemberPaidAmount, Member memberToGetPaid) {
        List<Debt> eventDebtList = new ArrayList<>();

        for (Map.Entry<Member, Integer> debtMap: eventMemberPaidAmount.entrySet()) {
            Member memberToPay = debtMap.getKey();
            int amount = debtMap.getValue();
            if (!(memberToPay.equals(memberToGetPaid))) {
                eventDebtList.add(new Debt(memberToGetPaid, memberToPay, amount));
            }
        }
        return eventDebtList;
    }
}
