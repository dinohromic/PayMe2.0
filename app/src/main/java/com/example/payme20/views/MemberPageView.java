package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;

public class MemberPageView extends AppCompatActivity {

    private ImageButton returnButton;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_page);
        initializeView();

    }
    private void initializeView(){
        this.returnButton = findViewById(R.id.memberPageReturnButton);
        this.userName = findViewById(R.id.memberPageMemberName);
    }


}
