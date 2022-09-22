package com.example.payme20;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailedDebtUpdater implements IDebtUpdater {
    @Override
    public List<Debt> updateDebts(Map<Member, Double> eventMemberPaidAmount, Member memberToGetPaid) {
        List<Debt> eventDebtList = new ArrayList<>();

        for (Map.Entry<Member, Double> debtMap: eventMemberPaidAmount.entrySet()) {
            Member memberToPay = debtMap.getKey();
            double amount = debtMap.getValue();
            if (!(memberToPay.equals(memberToGetPaid))) {
                eventDebtList.add(new Debt(memberToGetPaid, memberToPay, amount));
            }
        }
        return eventDebtList;
    }
}
