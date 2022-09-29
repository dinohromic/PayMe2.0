package com.example.payme20.ViewModels;

import android.nfc.tech.IsoDep;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.payme20.model.Factory;
import com.example.payme20.model.IDebtUpdater;
import com.example.payme20.model.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventCreateViewmodel extends ViewModel {
    private Map<Member, Integer> memberAndAmount = new HashMap<>();
    private String eventName;
    private IDebtUpdater debtUpdater;
    private Member payer;

    private MutableLiveData<List<Member>> mSpinnerMembers;

    public MutableLiveData<List<Member>> getSpinnerMembers() {
        return mSpinnerMembers;
    }
    public void addNewSpinnerMember(final Member member) {
    }

    public void createEvent() {
        Factory.createEvent(eventName, memberAndAmount, payer, debtUpdater);
    }
    public void addMemberAndAmountMap(Member member, int amount) {
        memberAndAmount.put(member, amount);
    }
    public void setDebtUpdater(IDebtUpdater IDebtUpdater) {
        debtUpdater = IDebtUpdater;
    }
    public void setPayer(Member member) {
        payer = member;
    }
}
