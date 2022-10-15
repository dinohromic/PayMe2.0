/*The responsibility of this class is to process input-data
in order to create debts and putting them in a list to return. */

package com.example.payme20.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DetailedDebtUpdater returns a list of detailed debts
 */
public class DetailedCreateDebtList implements ICreateDebtList, Serializable {
    private final String name = "Detailed";
    public DetailedCreateDebtList () {}

    /**
     * Takes in data from the event and returns a list with debts in a list
     * @param eventMemberPaidAmount Map with Members as keys and Integers as values
     * @param memberToGetPaid The Member to receive payment
     * @return returns list containing Debt-objects
     */
    @Override
    public List<Debt> createDebtList(Map<Member, Integer> eventMemberPaidAmount, Member memberToGetPaid) {
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

    @Override
    public String getName() {
        return this.name;
    }

}
