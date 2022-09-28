package com.example.payme20.views;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.R;
import com.example.payme20.model.Factory;

public class GroupCreateView extends AppCompatActivity {
    private EditText groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        Button CreateGroupButton = findViewById(R.id.createGroupView);
        groupName= findViewById(R.id.groupNameTextView);
//        Factory factory =new Factory();
//        factory.createGroup(groupName.getText().toString());
        CreateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(groupName.getText().toString())){
                    Toast.makeText(GroupCreateView.this,"Group name needed", Toast.LENGTH_SHORT);
                }
                else {
                    openAddMembersPage();
                }
            }
        });
    }

    private void openAddMembersPage() {
        Intent intent = new Intent(GroupCreateView.this, MemberView.class);
        GroupCreateView.this.startActivity(intent);
    }
    public void onAddMemberBtnClick(View view){

    }

}
