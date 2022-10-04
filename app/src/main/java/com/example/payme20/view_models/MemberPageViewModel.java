package com.example.payme20.view_models;

import androidx.lifecycle.ViewModel;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MemberPageViewModel extends ViewModel {

    Member currentProfileMember;
    Group belongsToGroup;
    Map<Member, Integer> currentGroupDebtsMap;
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    public MemberPageViewModel(Group group){
        this.belongsToGroup = group;
        this.currentGroupDebtsMap = getCurrentGroupDebtsMap();
    }

    public void addCurrentProfileMember(Member profileMember){
        this.currentProfileMember = Objects.requireNonNull(profileMember);
    }

    //TODO Remove this and replace with finding the same ID when we got the functioning
    //This method is sadly necessary because serializing changes reference of objects
    public Member findCorrectMemberReferenceInGroup(Group group, Member memberToFind){
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

    public Member getProfileMember(){
        return this.currentProfileMember;
    }

    public List<Member> getGroupMemberList(){
        return this.belongsToGroup.getGroupMembers();
    }

    public String getMemberName(Member member){
        return member.getUserName();
    }

    public String getCurrentUserProfileName(){
        return this.currentProfileMember.getUserName();
    }

    public String getPhoneNumber(){
        return this.currentProfileMember.getPhoneNumber();
    }

    public void setNewName(String newName){
        if(newName != null){
            this.currentProfileMember.setUserName(newName);
        }
    }

    public void setNewPhoneNumber(String newPhoneNumber){
        if(newPhoneNumber != null){
            this.currentProfileMember.setPhoneNumber(newPhoneNumber);
        }
    }

    public int getProfileMemberTotalDebt(){
        return payMeModel.getTotalDebt(this.belongsToGroup, this.currentProfileMember);
    }

    private Map<Member, Integer> getCurrentGroupDebtsMap(){
        return payMeModel.getSpecificDebts(this.belongsToGroup, this.currentProfileMember);
    }

    public int debtToMember(Member memberDebt){
        int debt = 0;
        try{return currentGroupDebtsMap.get(memberDebt);}
        catch (NullPointerException e){System.out.println("Debt for specific member missing [MemberPageViewModel]");}
       return debt;
    }

}
