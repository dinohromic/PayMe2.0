package com.example.payme20.ViewModels;

import android.nfc.tech.IsoDep;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.payme20.model.DetailedDebtUpdater;
import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.IDebtUpdater;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import com.example.payme20.model.SplitDebtUpdater;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventCreateViewmodel extends ViewModel {
    private Map<Member, Integer> memberAndAmount = new HashMap<>();
    private String eventName;
    private IDebtUpdater debtUpdater;
    private Member payer;
    private Group group;
    private List<Member> groupMembers;
    private List<Member> eventMembers;

    public EventCreateViewmodel(Group group) {
        this.group = group;
        initMemberList();
    }

    private void initMemberList() {
        if(group.getGroupMembers().size() != 0)
            groupMembers.addAll(group.getGroupMembers());
    }


    public void setDebtUpdater(String str) {
        switch (str) {
            case "split":
                debtUpdater = new SplitDebtUpdater();
                break;
            case "detailed":
                debtUpdater = new DetailedDebtUpdater();
                break;
        }
    }
    public void setPayer(Member member) {
        payer = member;
    }
}
