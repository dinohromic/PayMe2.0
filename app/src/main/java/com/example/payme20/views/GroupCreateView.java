package com.example.payme20.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.view_models.CreateGroupViewModel;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.R;

public class GroupCreateView extends AppCompatActivity{

    private static final String TAG = "MemberView";
    private EditText edtName, edtPhoneNumber;
    private TextView    membersPhone, membersName;
    private Button addMemberButton, finishButton;
    private LinearLayout membersContainer;
    private EditText edtGroupName;
    CreateGroupViewModel createGroupVM;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        createGroupVM = new CreateGroupViewModel(this);
        initWidget();
        setOnClickListenerFinish();
        setOnClickListenerAddMembers();
    }

    private void setOnClickListenerFinish() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtGroupName.getText().toString())){
                    Toast.makeText(GroupCreateView.this,"Group name needed", Toast.LENGTH_SHORT).show();
                }
                else {
                    openCurrentGroups();
                }
            }
        });
    }

    private void setOnClickListenerAddMembers() {
        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkTextFields();
            }
        });
    }

    private void checkTextFields() {
        if(TextUtils.isEmpty(edtGroupName.getText().toString())){
            Toast.makeText(GroupCreateView.this,"Group name needed", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtName.getText().toString())){
            Toast.makeText(GroupCreateView.this,"Members name needed", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtPhoneNumber.getText().toString())){
            Toast.makeText(GroupCreateView.this,"Members phone needed", Toast.LENGTH_SHORT).show();
        }
        else{
            addMemberCards(edtName.getText().toString(),edtPhoneNumber.getText().toString(), edtGroupName.getText().toString());
        }
    }

    private void initWidget() {
        edtGroupName = findViewById(R.id.groupNameTextView);
        edtName = findViewById(R.id.edtTxtName);
        edtPhoneNumber = findViewById(R.id.editTextPhone);

        membersContainer = findViewById(R.id.membersConatiner);

        addMemberButton = findViewById(R.id.mainActCreateGroupButton);
        finishButton = findViewById(R.id.addMembersFinishButton);
    }

    private void initCardWidgets(View view){
        this.membersName = view.findViewById(R.id.addMembersNameText);
        this.membersPhone = view.findViewById(R.id.addMembersPhoneText);
    }

    private void openCurrentGroups() {
       createGroupVM.createGroup(edtGroupName.getText().toString());
       OpenViewHelper.openViewPutExtra(GroupListView.class, GroupCreateView.this);
    }

    private void addMemberCards(String name, String number, String groupName) {
        View view = getLayoutInflater().inflate(R.layout.members_card, null);
        initCardWidgets(view);
        membersName.setText(name);
        membersPhone.setText(number);
        createGroupVM.addMembers(name, number);
        membersContainer.addView(view);
        edtName.getText().clear();
        edtPhoneNumber.getText().clear();
    }
}
