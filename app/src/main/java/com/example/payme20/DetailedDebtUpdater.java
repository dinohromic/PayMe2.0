package com.example.payme20;

import java.util.Map;

public class DetailedDebtUpdater implements IDebtUpdater {
    @Override
    public void updateDebts(Map<Member, Double> eventMemberPaidAmount, Member memberToGetPaid) {
        /*for (Map.Entry<Member, Double> debtMap: eventMemberPaidAmount.entrySet()) {
            Member memberToPay = debtMap.getKey();
            double amount = debtMap.getValue();
            if (!(memberToPay.equals(memberToGetPaid))) {
                memberToGetPaid.updateDebt(amount, memberToPay);
                memberToPay.updateDebt(-amount, memberToGetPaid);
            }
        }*/
    }
}
