package com.example.payme20.ViewModels;

import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

import java.util.ArrayList;

public class createGroupViewModel {
    Group group;
    String string;
    public createGroupViewModel(String groupName, ArrayList<Member> membersList){
        this.group= Factory.createGroup(groupName, membersList);
    }


}
