package com.example.payme20.view_models;

import androidx.lifecycle.ViewModel;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.model.PayMeModel;
import java.util.List;
import java.util.Map;

public class MemberPageViewModel extends ViewModel {

    Member currentProfileMember;
    Group belongsToGroup;
    Map<Member, Integer> currentGroupDebtsMap;
    PayMeModel payMeModel = PayMeModel.INSTANCE;

    public MemberPageViewModel(Group group, Member member){
        this.belongsToGroup = payMeModel.getGroups().get(group.getGroupName());
        this.currentProfileMember = getMemberInActualGroup(member);
        this.currentGroupDebtsMap = getCurrentGroupDebtsMap();
    }

    //TODO Remove this and replace with finding the same ID when we got the functioning
    //This method is sadly necessary because serializing changes reference of objects
    public final Member getMemberInActualGroup(Member memberToFind){
        if(belongsToGroup.getGroupMembers().contains(memberToFind)) {
            return belongsToGroup.getGroupMembers().get(belongsToGroup.getGroupMembers().indexOf(memberToFind));
        }
        return null;
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
            for(Member m : belongsToGroup.getGroupMembers()) {
                if(m.equals(this.currentProfileMember)) {
                    m.setUserName(newName);
                }
            }
        }
        payMeModel.serializeModel();
    }

    public void setNewPhoneNumber(String newPhoneNumber){
        if(newPhoneNumber != null){
            for(Member m : belongsToGroup.getGroupMembers()) {
                if(m.equals(this.currentProfileMember)) {
                    m.setPhoneNumber(newPhoneNumber);
                }
            }
        }
        payMeModel.serializeModel();
    }

    public int getProfileMemberTotalDebt(){
        return payMeModel.getTotalDebt(this.belongsToGroup.getGroupName(), this.currentProfileMember);
    }

    private Map<Member, Integer> getCurrentGroupDebtsMap(){
        return payMeModel.getSpecificDebts(this.belongsToGroup, this.currentProfileMember);
    }

    public int debtToMember(Member memberDebt){
        if(currentGroupDebtsMap.containsKey(memberDebt)) {
            return currentGroupDebtsMap.get(memberDebt);
        } else {
            return 0;
        }
    }

    public boolean inactivateCurrentMember() {
        return payMeModel.inactivateMember(belongsToGroup, currentProfileMember);
    }

    public void activateCurrentMember() {
        payMeModel.activateMember(currentProfileMember);
    }
}
