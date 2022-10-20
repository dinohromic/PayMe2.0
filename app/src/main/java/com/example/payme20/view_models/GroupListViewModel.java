package com.example.payme20.view_models;

import androidx.lifecycle.ViewModel;

import com.example.payme20.model.Group;
import com.example.payme20.model.PayMeModel;

import java.util.Map;

public class GroupListViewModel extends ViewModel {
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    public Map<String,Group> getGroups(){
        return payMeModel.getDeserializedGroups();
    }
}
