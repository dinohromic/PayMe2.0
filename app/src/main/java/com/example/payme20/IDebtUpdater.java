package com.example.payme20;

import java.util.HashMap;
import java.util.Map;

public interface IDebtUpdater {
    void calculateEventExpenditures(Map<Member, Double> map, Member Payer);
}
