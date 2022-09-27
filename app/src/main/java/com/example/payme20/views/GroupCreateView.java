package com.example.payme20.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.R;

public class GroupCreateView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        Button CreateGroupButton = findViewById(R.id.createGroupView);
        CreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddMembersPage();
            }
        });
    }

    private void openAddMembersPage() {
        Intent intent = new Intent(GroupCreateView.this, MemberView.class);
        GroupCreateView.this.startActivity(intent);
    }

        //EditText groupName= findViewById(R.id.groupNameTextView);
}
