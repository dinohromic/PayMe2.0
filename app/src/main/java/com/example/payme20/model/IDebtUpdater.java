package com.example.payme20.model;

import java.util.List;
import java.util.Map;

public interface IDebtUpdater {
    List<Debt> updateDebts(Map<Member, Double> map, Member Payer);
}
