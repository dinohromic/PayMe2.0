package com.example.payme20.view_models;

import com.example.payme20.model.DataManager;
import com.example.payme20.model.DetailedCreateDebtList;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupListViewModel {
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    public String getGroupName(Group currentGroup){
        return currentGroup.getGroupName();
    }

    public Map<String,Group> getGroupList(){
        payMeModel.deserializeModel();
        return payMeModel.getGroups();
    }

}
