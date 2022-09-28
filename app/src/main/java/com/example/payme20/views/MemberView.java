package com.example.payme20.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.R;

public class MemberView extends AppCompatActivity{

    private static final String TAG = "MemberView";
    private Member member;
    private Group group;
    private TextView txtWarningName, txtWarningPhoneNumber;
    private EditText edtName, edtPhoneNumber;
    private TextView    membersPhone, membersName;
    private CheckBox agreementCheck;
    private Button addMemberbutton;
    private LinearLayout membersContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_members);
        addMemberbutton = findViewById(R.id.mainActCreateGroupButton);
        edtName = findViewById(R.id.edtTxtName);

        edtPhoneNumber = findViewById(R.id.editTextPhone);
        membersContainer = findViewById(R.id.membersConatiner);
        addMemberbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMembers(edtName.getText().toString(),edtPhoneNumber.getText().toString());
            }
        });
    }

    private void addMembers(String name, String  numbber) {
        View view = getLayoutInflater().inflate(R.layout.members_card, null);
        membersName= view.findViewById(R.id.addMembersNameText);
        membersPhone = view.findViewById(R.id.addMembersPhoneText);
        membersName.setText(name);
        membersPhone.setText(numbber);
        membersContainer.addView(view);
    }




    private void initMember(){
        Log.d(TAG, "initMember: started");
        if (validateData()) {
            if (agreementCheck.isChecked()){
                showSnackBar();
            }
            else{
                Toast.makeText(this, "You need to fill in the fields to register", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void showSnackBar() {
    }

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
