/*The responsibility of this class is to manage all existing debts
within a group.
*/
package com.example.payme20.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtHandler implements Serializable {
    //@JsonSerialize(keyUsing = MemberSerializer.class)

    private Map<String, List<Debt>> incomingDebtsMaps = new HashMap<>();
    //@JsonSerialize(keyUsing = MemberSerializer.class)
    private Map<String, List<Debt>> outgoingDebtsMap = new HashMap<>();

    /**
     * Create new debt list if the current debt lists are empty, then adds the debt to both from and to debt maps.
     * @param debt the debt object to be added
     */
    public void addDebt(Debt debt) {
        List<Debt> toDebts = incomingDebtsMaps.get(debt.getDebtTo().getUserName());
        List<Debt> fromDebts = outgoingDebtsMap.get(debt.getDebtFrom().getUserName());
        if(toDebts == null) {
            toDebts = new ArrayList<>();
        }
        if(fromDebts == null) {
            fromDebts = new ArrayList<>();
        }
        toDebts.add(debt);
        fromDebts.add(debt);
        incomingDebtsMaps.put(debt.getDebtTo().getUserName(), toDebts);
        outgoingDebtsMap.put(debt.getDebtFrom().getUserName(), fromDebts);
    }

    /**
     * get a members incoming debts
     * @return a map with the member and a list of debts
     */
    public Map<String, List<Debt>> getIncomingDebtsMaps() {
        return incomingDebtsMaps;
    }

    /**
     * get a members outgoing debts
     * @return a map with the member and a list of debts
     */
    public Map<String, List<Debt>> getOutgoingDebtsMap() {
        return outgoingDebtsMap;
    }

    /**
     * if the current debt lists are empty, then remove the debt from both from and to debt maps.
     * @param debt the debt object to be removed
     */
    public void removeDebt(Debt debt) {
        List<Debt> toDebts = incomingDebtsMaps.get(debt.getDebtTo().getUserName());
        List<Debt> fromDebts = outgoingDebtsMap.get(debt.getDebtFrom().getUserName());
        if(toDebts == null) {
            toDebts = new ArrayList<>();
        }
        if(fromDebts == null) {
            fromDebts = new ArrayList<>();
        }
        toDebts.remove(debt);
        fromDebts.remove(debt);
        incomingDebtsMaps.put(debt.getDebtTo().getUserName(), toDebts);
        outgoingDebtsMap.put(debt.getDebtFrom().getUserName(), fromDebts);
    }
}
