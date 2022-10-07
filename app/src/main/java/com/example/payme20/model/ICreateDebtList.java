package com.example.payme20.model;

import java.util.List;
import java.util.Map;

public interface ICreateDebtList {
    List<Debt> createDebtList(Map<Member, Integer> map, Member Payer);
}

