package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.R;
import com.example.payme20.ViewModels.MemberPageViewModel;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Member;

public class MemberPageView extends AppCompatActivity {

    private EditText newPhoneNumber, newUserName;
    private TextView userName;
    private MemberPageViewModel memberPageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_page);
        this.memberPageViewModel = new MemberPageViewModel((Member) getIntent().getSerializableExtra("MEMBER_KEY"));
        initializeView();
        populateView();
    }

    private void populateView() {
        String userProfile = "User profile: " + memberPageViewModel.getMemberName();
        this.userName.setText(userProfile);
        this.newPhoneNumber.setText(memberPageViewModel.getPhoneNumber());
    }

    private void initializeView(){
        this.userName = findViewById(R.id.memberPageMemberName);
        this.newPhoneNumber = findViewById(R.id.memberPageEditPhone);
        this.newUserName = findViewById(R.id.memberPageEditName);
    }

}
