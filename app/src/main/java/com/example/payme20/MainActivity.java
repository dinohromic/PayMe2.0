package com.example.payme20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.views.GroupListView;
import com.example.payme20.views.MemberView;


public class MainActivity extends AppCompatActivity {
    
    Button openGroupListButton;
    Button createGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateClickable();
        setOpenViewListener(this.openGroupListButton, GroupListView.class);
        setOpenViewListener(this.createGroupButton, MemberView.class);
    }

    private void initiateClickable(){
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