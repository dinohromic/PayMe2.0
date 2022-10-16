package com.example.payme20.view_models;

import com.example.payme20.model.Group;
import com.example.payme20.model.PayMeModel;

import java.util.Map;

public class GroupListViewModel {
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    public Map<String,Group> getGroups(){
        payMeModel.deserializeModel();
        return payMeModel.getGroups();
    }
}
