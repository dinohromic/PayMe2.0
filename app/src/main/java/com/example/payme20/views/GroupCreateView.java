/*The responsibility of this class is handling the process of creating new groups
by presenting data on the view and taking in unprocessed data about group members
*/
package com.example.payme20.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.MainActivity;
import com.example.payme20.view_models.CreateGroupViewModel;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.R;

public class GroupCreateView extends AppCompatActivity{

    private EditText edtName, edtPhoneNumber;
    private TextView    membersPhone, membersName;
    private Button addMemberButton, finishButton;
    private LinearLayout membersContainer;
    private EditText edtGroupName;
    private CreateGroupViewModel createGroupVM;
    private ImageButton returnButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        createGroupVM = new CreateGroupViewModel();
        initWidget();
        setOnClickListenerFinish();
        setOnClickListenerAddMembers();
        setOnClickListenerReturnButton();
    }

    private void setOnClickListenerReturnButton() {
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openView(MainActivity.class, GroupCreateView.this);
            }
        });
    }

    private void setOnClickListenerFinish() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtGroupName.getText().toString())){
                    Toast.makeText(GroupCreateView.this,"Group name needed", Toast.LENGTH_SHORT).show();
                }
                else {
                    openNewGroup();
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
            addMemberCards(edtName.getText().toString(),edtPhoneNumber.getText().toString());
        }
    }

    private void initWidget() {
        edtGroupName = findViewById(R.id.groupNameTextView);
        edtName = findViewById(R.id.edtTxtName);
        edtPhoneNumber = findViewById(R.id.editTextPhone);

        membersContainer = findViewById(R.id.membersConatiner);

        addMemberButton = findViewById(R.id.mainActCreateGroupButton);
        finishButton = findViewById(R.id.addMembersFinishButton);
        returnButton = findViewById(R.id.returnButtonCreateGroup);
    }

    private void initCardWidgets(View view){
        this.membersName = view.findViewById(R.id.addMembersNameText);
        this.membersPhone = view.findViewById(R.id.addMembersPhoneText);
    }

    private void openNewGroup() {
       createGroupVM.createGroup(edtGroupName.getText().toString());
       OpenViewHelper.openViewPutExtra(GroupPageView.class, GroupCreateView.this, edtGroupName.getText().toString(), 0);
    }

    private void addMemberCards(String name, String number) {
        View view = getLayoutInflater().inflate(R.layout.members_card, null);
        initCardWidgets(view);
        createGroupVM.addMember(name, number);
        membersName.setText(name);
        membersPhone.setText(number);
        membersContainer.addView(view);
        edtName.getText().clear();
        edtPhoneNumber.getText().clear();
    }
}
