package com.example.payme20.ViewModels;

import androidx.lifecycle.ViewModel;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import java.util.List;
import java.util.Map;

public class MemberPageViewModel extends ViewModel {

    Member currentMember;
    Group belongsToGroup;
    Map<Member, Integer> debtMap;
    PayMeModel payMeModel = new PayMeModel();

    public MemberPageViewModel(Member memberOnPage, Group group){
        this.currentMember = memberOnPage;
        this.belongsToGroup = group;
        this.debtMap = getDebtMap();
    }

    public String getMemberName(Member member){
        return member.getUserName();
    }

    public String getCurrentUserProfileName(){
        return this.currentMember.getUserName();
    }
    public String getPhoneNumber(){
        return this.currentMember.getPhoneNumber();
    }

    public void setNewName(String newName){
        if(newName != null){
            this.currentMember.setUserName(newName);
        }
    }
    public void setNewPhoneNumber(String newPhoneNumber){
        if(newPhoneNumber != null){
            this.currentMember.setPhoneNumber(newPhoneNumber);
        }
    }

    public List<Member> getExcludeCurrentMemberList(){
        List<Member> groupMembers = this.belongsToGroup.getGroupMembers();
        groupMembers.remove(this.currentMember);
        return groupMembers;
    }

    public int getMemberTotalDebt(){
        return payMeModel.getTotalDebt(this.belongsToGroup, this.currentMember);
    }
    private Map<Member, Integer> getDebtMap(){
        return payMeModel.getSpecificDebts(this.belongsToGroup, this.currentMember);
    }

    public int debtToMember(Member memberDebt){
        int debt = 0;
        try{return debtMap.get(memberDebt);}
        catch (NullPointerException e){System.out.println("Debt for specific member missing [MemberPageViewModel]");}

       return debt;
    }

}
