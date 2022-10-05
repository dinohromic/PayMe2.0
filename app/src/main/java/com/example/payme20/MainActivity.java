package com.example.payme20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.views.GroupListView;
import com.example.payme20.views.MemberView;


public class MainActivity extends AppCompatActivity {
    
    private Button openGroupListButton;
    private Button createGroupButton;
    private SwitchCompat darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setPageNavigation();
        setDarkModeListener();
    }

    private void setPageNavigation(){
        setOpenViewListener(this.openGroupListButton, GroupListView.class);
        setOpenViewListener(this.createGroupButton, MemberView.class);
    }

    private void findViewsById(){
        this.darkModeSwitch = findViewById(R.id.switch1);
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

    private void setDarkModeListener(){
        this.darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }
}