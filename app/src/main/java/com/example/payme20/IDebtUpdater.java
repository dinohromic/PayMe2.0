package com.example.payme20;

import java.util.HashMap;
import java.util.Map;

public interface IDebtUpdater {
    void updateDebts(Map<Member, Double> map, Member Payer);
}
