package com.example.payme20.view_models;

import androidx.lifecycle.ViewModel;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MemberPageViewModel extends ViewModel {

    Member currentMember;
    Group belongsToGroup;
    Map<Member, Integer> debtMap;
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    public MemberPageViewModel(Group group){
        this.belongsToGroup = group;
        this.debtMap = getDebtMap();
    }

    public void addCurrentProfileMember(Member profileMember){
        this.currentMember = profileMember;
    }

    //TODO Remove this and replace with finding the same ID when we got the functioning
    public Member findMemberReferenceInGroup(Group group, Member memberToFind){
        Member memberByReference = new Member("This doesn't feel like good code", "1337", 1337);
        for (Member member :group.getGroupMembers()) {
            if(Objects.equals(memberToFind.getUserName(), member.getUserName()) && Objects.equals(memberToFind.getPhoneNumber(), member.getPhoneNumber())){
                memberByReference = member;
            }
        }
        return memberByReference;
    }

    public Group getGroup(){
        return this.belongsToGroup;
    }

    public Member getCurrentMember(){
        return this.currentMember;
    }

    public List<Member> getGroupMembers(){
        return this.belongsToGroup.getGroupMembers();
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
