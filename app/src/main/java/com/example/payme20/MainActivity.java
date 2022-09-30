package com.example.payme20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.views.GroupListView;
import com.example.payme20.views.MemberView;


public class MainActivity extends AppCompatActivity {
    
    Button openGroupListButton;
    Button createGroupButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateClickable();
        setOpenViewListener(this.openGroupListButton, GroupListView.class);
        setOpenViewListener(this.createGroupButton, MemberView.class);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.idRBLight:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case R.id.idRBDark:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                }
            }
    });
    }

    private void initiateClickable(){
        radioGroup = findViewById(R.id.idRGgroup);
        this.openGroupListButton = findViewById(R.id.groupPageButton);
        this.createGroupButton = findViewById(R.id.addMemberButton);
    }

    private void setOpenViewListener(Button openButton, Class<?> viewToOpen){
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openView(viewToOpen, MainActivity.this);
            }
        });
    }


}