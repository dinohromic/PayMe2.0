package com.example.payme20.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.payme20.model.Member;

public class MemberPageViewModel extends ViewModel {

    Member currentMember;

    public MemberPageViewModel(Member memberOnPage){
        this.currentMember = memberOnPage;
    }

    public String getMemberName(){
        return this.currentMember.getUserName();
    }
    public String getPhoneNumber(){
        return this.currentMember.getPhoneNumber();
    }

    public void setNewName(String newName){
        this.currentMember.setUserName(newName);
    }
    public void setNewPhoneNumber(String newPhoneNumber){
        this.currentMember.setPhoneNumber(newPhoneNumber);
    }
}
