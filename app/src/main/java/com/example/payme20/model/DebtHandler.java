package com.example.payme20.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtHandler implements Serializable {
    private Map<Member, List<Debt>> incomingDebtsMaps = new HashMap<>();
    private Map<Member, List<Debt>> outgoingDebtsMap = new HashMap<>();

    public void addDebt(Debt debt) {
        List<Debt> toDebts = incomingDebtsMaps.get(debt.getDebtTo());
        List<Debt> fromDebts = outgoingDebtsMap.get(debt.getDebtFrom());
        if(toDebts == null) {
            toDebts = new ArrayList<>();
        }
        if(fromDebts == null) {
            fromDebts = new ArrayList<>();
        }
        toDebts.add(debt);
        fromDebts.add(debt);
        incomingDebtsMaps.put(debt.getDebtTo(), toDebts);
        outgoingDebtsMap.put(debt.getDebtFrom(), fromDebts);
    }

    public Map<Member, List<Debt>> getIncomingDebtsMaps() {
        return incomingDebtsMaps;
    }

    public Map<Member, List<Debt>> getOutgoingDebtsMap() {
        return outgoingDebtsMap;
    }

    public void removeDebt(Debt debt) {
        List<Debt> toDebts = incomingDebtsMaps.get(debt.getDebtTo());
        List<Debt> fromDebts = outgoingDebtsMap.get(debt.getDebtFrom());
        toDebts.remove(debt);
        fromDebts.remove(debt);
        incomingDebtsMaps.put(debt.getDebtTo(), toDebts);
        outgoingDebtsMap.put(debt.getDebtFrom(), fromDebts);
    }
}
