package com.example.payme20.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberFragmentGroupPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberFragmentGroupPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Group group;

    private Button createNewMemberButton;
    private LinearLayout cardContainerLayout;

    public MemberFragmentGroupPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFragmentGroupPage.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberFragmentGroupPage newInstance(String param1, String param2) {
        MemberFragmentGroupPage fragment = new MemberFragmentGroupPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void setGroup(Group group){
        this.group = group;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View groupFragmentView = inflater.inflate(R.layout.fragment_member_group_page, container, false);
        initializeWidgetsFragmentMembers(groupFragmentView);
        setAddNewMemberListener();
        populateMemberContainer();
        return groupFragmentView;
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

    private void setAddNewMemberListener(){
        this.createNewMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog_view, null);
                EditText editMemberName = dialogView.findViewById(R.id.editTextTextPersonName);
                EditText editPhoneNumber = dialogView.findViewById(R.id.editTextPhoneGP);
                Button alertButton = dialogView.findViewById(R.id.createNewMember);
                alertButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(TextUtils.isEmpty(editMemberName.getText().toString()) && TextUtils.isEmpty(editPhoneNumber.getText().toString())){
                            Toast.makeText(getActivity(), "Empty text fields not allowed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            // do something
                        }

                    }
                });

                dialogBuilder.setView(dialogView);
            }
        });
    }

    private void populateAddMemberDialog (){

    }



    private void setMemberCardListener(Member member, View cardView){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(MemberPageView.class, getActivity(), member, group);

            }
        });

    }


}