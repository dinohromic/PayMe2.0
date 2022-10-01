package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.R;
import com.example.payme20.ViewModels.MemberPageViewModel;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

public class MemberPageView extends AppCompatActivity {

    private EditText newPhoneNumber, newUserName;
    private TextView userName, totalDebt;
    private MemberPageViewModel memberPageViewModel;
    private LinearLayout cardContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_page);
        findViewIdForWidgets();
        initializeViewModel();
        populateView();
    }

    private void initializeViewModel(){
        Group belongsToGroup = (Group) getIntent().getSerializableExtra("GROUP_KEY");
        Member currentMember = (Member) (Member) getIntent().getSerializableExtra("MEMBER_KEY");
        this.memberPageViewModel = new MemberPageViewModel(currentMember, belongsToGroup);
    }

    private void populateView() {
        String userProfile = "User profile: " + memberPageViewModel.getMemberName();
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
    }
    private void populateCardContainer(){
        for (Member groupMember: memberPageViewModel.getExcludeCurrentMemberList()) {
            View card = createCard(groupMember);
            this.cardContainer.addView(card);
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
        String nameOfMemberMessage = member.getUserName() + " has a total debt to you of: ";
        nameOfMemberTextView.setText(nameOfMemberMessage);
        String amountOfDebtMessage = memberPageViewModel.debtToMember(member) + " Kr";
        amountOfDebtTextView.setText(amountOfDebtMessage);
    }

}
