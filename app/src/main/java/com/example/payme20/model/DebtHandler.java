package com.example.payme20.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtHandler implements Serializable {
    private Map<Member, List<Debt>> toMap = new HashMap<>();
    private Map<Member, List<Debt>> fromMap = new HashMap<>();

    public void addDebt(Debt debt) {
        List<Debt> toDebts = toMap.get(debt.getDebtTo());
        List<Debt> fromDebts = fromMap.get(debt.getDebtFrom());
        if(toDebts == null) {
            toDebts = new ArrayList<>();
        }
        if(fromDebts == null) {
            fromDebts = new ArrayList<>();
        }
        toDebts.add(debt);
        fromDebts.add(debt);
        toMap.put(debt.getDebtTo(), toDebts);
        fromMap.put(debt.getDebtFrom(), fromDebts);
    }

    public Map<Member, List<Debt>> getToMap() {
        return toMap;
    }

    public Map<Member, List<Debt>> getFromMap() {
        return fromMap;
    }

    public void removeDebt(Debt debt) {
        List<Debt> toDebts = toMap.get(debt.getDebtTo());
        List<Debt> fromDebts = fromMap.get(debt.getDebtFrom());
        toDebts.remove(debt);
        fromDebts.remove(debt);
        toMap.put(debt.getDebtTo(), toDebts);
        fromMap.put(debt.getDebtFrom(), fromDebts);
    }
}
