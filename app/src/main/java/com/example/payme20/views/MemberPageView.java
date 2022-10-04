package com.example.payme20.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.view_models.MemberPageViewModel;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

import java.util.Objects;

public class MemberPageView extends AppCompatActivity {

    private EditText newPhoneNumber, newUserName;
    private TextView userName, totalDebt;
    private MemberPageViewModel memberPageViewModel;
    private LinearLayout cardContainer;
    private ImageButton memberPageReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_page);
        findViewIdForWidgets();
        initializeViewModel();
        populateView();
        setListenerOnWidgets();
    }

    private void initializeViewModel(){
        Group belongsToGroup = (Group) getIntent().getSerializableExtra("GROUP_KEY");
        Member member = (Member) getIntent().getSerializableExtra("MEMBER_KEY");
        Member profileMember = findMemberReferenceInGroup(belongsToGroup, member);
        this.memberPageViewModel = new MemberPageViewModel(profileMember, belongsToGroup);
    }

    private Member findMemberReferenceInGroup(Group group, Member memberToFind){
        Member memberByReference = new Member("This doesn't feel like good code", "1337", 1337);
        for (Member member :group.getGroupMembers()) {
            if(Objects.equals(memberToFind.getUserName(), member.getUserName()) && Objects.equals(memberToFind.getPhoneNumber(), member.getPhoneNumber())){
                memberByReference = member;
            }
        }
        return memberByReference;
    }

    private void populateView() {
        String userProfile = "User profile: " + memberPageViewModel.getCurrentUserProfileName();
        String userTotalDebt = "Total debt: " + memberPageViewModel.getMemberTotalDebt();
        this.userName.setText(userProfile);
        this.newPhoneNumber.setText(memberPageViewModel.getPhoneNumber());
        this.totalDebt.setText(userTotalDebt);
        populateCardContainer();
    }

    private void findViewIdForWidgets(){
        this.userName = findViewById(R.id.memberPageMemberName);
        this.newPhoneNumber = findViewById(R.id.memberPageEditPhone);
        this.newUserName = findViewById(R.id.memberPageEditName);
        this.totalDebt = findViewById(R.id.memberPageTotalDebt);
        this.cardContainer = findViewById(R.id.memberPageCardCointainer);
        this.memberPageReturnButton = findViewById(R.id.memberPageReturnButton);
    }
    private void populateCardContainer(){
        for (Member groupMember: memberPageViewModel.getGroupMembers()) {
            if(!(groupMember.equals(memberPageViewModel.getCurrentMember()))){
                View card = createCard(groupMember);
                this.cardContainer.addView(card);
            }
        }
    }

    private View createCard(Member member){
        View card = getLayoutInflater().inflate(R.layout.member_debt_card, null);
        setCardProperties(card, member);
        return card;
    }

    private void setCardProperties(View card, Member member){
        TextView nameOfMemberTextView = card.findViewById(R.id.debtCardUser);
        TextView amountOfDebtTextView = card.findViewById(R.id.debtCardAmount);
        String nameOfMemberMessage = memberPageViewModel.getMemberName(member) + " has a total debt to you of: ";
        nameOfMemberTextView.setText(nameOfMemberMessage);
        String amountOfDebtMessage = memberPageViewModel.debtToMember(member) + " Kr";
        amountOfDebtTextView.setText(amountOfDebtMessage);
    }

    private void setListenerOnWidgets(){
        userNameListener();
        phoneNumberListener();
        returnListener();
    }

    private void userNameListener(){
        newUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                memberPageViewModel.setNewName(newUserName.getText().toString());
                String profileInformation = "User profile: " + memberPageViewModel.getCurrentUserProfileName();
                userName.setText(profileInformation);
            }
        });
    }

    private void phoneNumberListener(){
        newPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                memberPageViewModel.setNewPhoneNumber(newPhoneNumber.getText().toString());
                newPhoneNumber.setText(memberPageViewModel.getPhoneNumber());
            }
        });
    }
    private void returnListener(){
        this.memberPageReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(GroupPageView.class, getApplicationContext(), memberPageViewModel.getGroup());
            }
        });
    }
}
