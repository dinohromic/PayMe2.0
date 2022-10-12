package com.example.payme20.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtHandler implements Serializable {
    private Map<Member, List<Debt>> incomingDebtsMaps = new HashMap<>();
    private Map<Member, List<Debt>> outgoingDebtsMap = new HashMap<>();

    /**
     * Create new debt list if the current debt lists are empty, then adds the debt to both from and to debt maps.
     * @param debt the debt object to be added
     */
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

    /**
     * get a members incoming debts
     * @return a map with the member and a list of debts
     */
    public Map<Member, List<Debt>> getIncomingDebtsMaps() {
        return incomingDebtsMaps;
    }

    /**
     * get a members outgoing debts
     * @return a map with the member and a list of debts
     */
    public Map<Member, List<Debt>> getOutgoingDebtsMap() {
        return outgoingDebtsMap;
    }

    /**
     * if the current debt lists are empty, then remove the debt from both from and to debt maps.
     * @param debt the debt object to be removed
     */
    public void removeDebt(Debt debt) {
        List<Debt> toDebts = incomingDebtsMaps.get(debt.getDebtTo());
        List<Debt> fromDebts = outgoingDebtsMap.get(debt.getDebtFrom());
        if(toDebts == null) {
            toDebts = new ArrayList<>();
        }
        if(fromDebts == null) {
            fromDebts = new ArrayList<>();
        }
        toDebts.remove(debt);
        fromDebts.remove(debt);
        incomingDebtsMaps.put(debt.getDebtTo(), toDebts);
        outgoingDebtsMap.put(debt.getDebtFrom(), fromDebts);
    }
}
