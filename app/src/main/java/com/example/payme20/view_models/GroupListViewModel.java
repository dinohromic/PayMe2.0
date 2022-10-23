package com.example.payme20.view_models;

import androidx.lifecycle.ViewModel;

import com.example.payme20.model.Group;
import com.example.payme20.model.PayMeModel;

import java.util.Map;
/**
 * This class is responsible for the communication between GroupListView and the model
 */
public class GroupListViewModel extends ViewModel {
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    public Map<String,Group> getGroups(){
        return payMeModel.getDeserializedGroups();
    }
}
