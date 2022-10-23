/* The responsibility of this class is presenting data on the fragment of
members in the the view of the group page.
* */
package com.example.payme20.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.view_models.GroupPageViewModel;
import com.example.payme20.view_models.ViewModelFactory;
/**
 * This class is responsible for the fragment of members in the group page view
 */
public class MemberFragmentGroupPage extends Fragment {

    private Group group;

    private Button createNewMemberButton;
    private LinearLayout cardContainerLayout;
    private GroupPageViewModel gpViewModel;

    public MemberFragmentGroupPage() {
        // Required empty public constructor
    }

    public void setGroup(Group group){
        this.group = group;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View groupFragmentView = inflater.inflate(R.layout.fragment_member_group_page, container, false);
        initViewModel();
        initializeWidgetsFragmentMembers(groupFragmentView);
        setAddNewMemberListener(createNewMemberButton);
        populateMemberContainer();
        return groupFragmentView;
    }
    private void initViewModel() {
        ViewModelFactory vmFactory = ViewModelFactory.INSTANCE;
        this.gpViewModel = new ViewModelProvider(this, vmFactory).get(GroupPageViewModel.class);
    }

    private void populateMemberContainer() {
        for (Member member: group.getGroupMembers()) {
            View cardView = getLayoutInflater().inflate(R.layout.members_card, null);
            populateCard(cardView, member);
            setMemberCardListener(member, cardView);
            cardContainerLayout.addView(cardView);
        }
    }

    private void populateCard(View cardView, Member memberData){
        TextView nameText = cardView.findViewById(R.id.addMembersNameText);
        nameText.setText(memberData.getUserName());
        TextView phoneNumber = cardView.findViewById(R.id.addMembersPhoneText);
        phoneNumber.setText(memberData.getPhoneNumber());
    }

    private void initializeWidgetsFragmentMembers(View fragmentView) {
        this.cardContainerLayout = fragmentView.findViewById(R.id.memberFragmentContainer);
        this.createNewMemberButton = fragmentView.findViewById(R.id.addMemberButtonFragment);
    }

    private void setAddNewMemberListener(Button createNewMemberButton){
        createNewMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(CreateNewMembersView.class,getActivity(), group.getGroupName());
            }
        });
    }

    private void setMemberCardListener(Member member, View cardView){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group = gpViewModel.getGroup();
                OpenViewHelper.openViewPutExtra(MemberPageView.class, getActivity(), member, group);

            }
        });

    }


}