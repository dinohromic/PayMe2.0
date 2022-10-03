package com.example.payme20.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.view_models.createGroupViewModel;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.R;

public class MemberView extends AppCompatActivity{

    private static final String TAG = "MemberView";
    private TextView txtWarningName, txtWarningPhoneNumber;
    private EditText edtName, edtPhoneNumber;
    private TextView    membersPhone, membersName;
    private Button addMemberbutton,addMembersFinishButton;
    private LinearLayout membersContainer;
    private EditText groupName;
    createGroupViewModel createGroup;

//    private ConstraintLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_members);
        createGroup = new createGroupViewModel();
        groupName=findViewById(R.id.groupNameTextView);
        addMemberbutton = findViewById(R.id.mainActCreateGroupButton);
        edtName = findViewById(R.id.edtTxtName);
        addMembersFinishButton = findViewById(R.id.addMembersFinishButton);
        addMembersFinishButton.setOnClickListener(view -> {
            if(TextUtils.isEmpty(groupName.getText().toString())){
                Toast.makeText(MemberView.this,"Group name needed", Toast.LENGTH_SHORT).show();
            }

            else {
                openCurrenGroups();
            }
        });
        edtPhoneNumber = findViewById(R.id.editTextPhone);
        membersContainer = findViewById(R.id.membersConatiner);

        addMemberbutton.setOnClickListener(view -> {
            if(TextUtils.isEmpty(groupName.getText().toString())){
                Toast.makeText(MemberView.this,"Group name needed", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(edtName.getText().toString())){
                Toast.makeText(MemberView.this,"Members name needed", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(edtPhoneNumber.getText().toString())){
                Toast.makeText(MemberView.this,"Members phone needed", Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    addMembers(edtName.getText().toString(),edtPhoneNumber.getText().toString());
                } catch (Exception e){
                    addMembers("None", "0");
                }

            }

        });

    }

    private void openCurrenGroups() {
       createGroup.createGroup(groupName.getText().toString());
        OpenViewHelper.openView(GroupListView.class, MemberView.this);
    }

    private void addMembers(String name, String numbber) {
        View view = getLayoutInflater().inflate(R.layout.members_card, null);
        membersName= view.findViewById(R.id.addMembersNameText);
        membersPhone = view.findViewById(R.id.addMembersPhoneText);
        membersName.setText(name);
        membersPhone.setText(numbber);
        createGroup.addMembers(name,numbber, -1);
        membersContainer.addView(view);
        edtName.getText().clear();
        edtPhoneNumber.getText().clear();
//        txtWarningName.setVisibility(View.INVISIBLE);
//        txtWarningPhoneNumber.setVisibility(View.INVISIBLE);
    }




//    private void initMember(){
//        Log.d(TAG, "initMember: started");
//        if (validateData()) {
//                showSnackBar();
//            }
//            else{
//                Toast.makeText(this, "You need to fill in the fields to register", Toast.LENGTH_SHORT).show();
//            }
//        }






    /* This function is checking whether or not a username or phone number is valid or not*/
    private boolean validateData(){
        Log.d(TAG, "validateData: started" );
        if(edtName.getText().toString().equals("")){
            txtWarningName.setVisibility(View.VISIBLE);
            txtWarningName.setText("Enter your UserName");
            return false;
        }
        if(edtPhoneNumber.getText().toString().equals("")){
            txtWarningPhoneNumber.setVisibility((View.VISIBLE));
            txtWarningPhoneNumber.setText("Enter your phone number");
            return false;
        }
        return true;
    }

}
