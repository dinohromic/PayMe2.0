/* The responsibility of this class is to receive input-data from a user in order to
pass the data to a class which can create objects of the inpu-data
*/
package com.example.payme20.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Group;
import com.example.payme20.view_models.createNewMembersViewModel;

public class createNewMembersView extends AppCompatActivity {

    private EditText newMemberName;
    private EditText newMemberPhone;
    private Button createNewMemberButton;
    private ImageButton returnButton;
    private createNewMembersViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_member);
        initWidgets();
        initViewModel();
        setOnClickListener(createNewMemberButton);
        setOnClickListener(returnButton);
    }

    private void initViewModel() {
        String groupName = (String) getIntent().getSerializableExtra("GROUP_NAME_KEY");
        this.viewModel = new createNewMembersViewModel(groupName);
    }

    private void setOnClickListener(Button createNewMemberButton) {
        createNewMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(newMemberName.getText().toString())){
                    Toast.makeText(createNewMembersView.this,"Member name needed!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(newMemberPhone.getText().toString())){
                    Toast.makeText(createNewMembersView.this, "Phone number needed!", Toast.LENGTH_SHORT).show();
                }
                else createNewMember(newMemberName, newMemberPhone);
            }
        });
    }

    private void setOnClickListener(ImageButton returnButton){
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToHome();
            }
        });
    }

    private void createNewMember(EditText newMemberName, EditText newMemberPhone){
        String name = newMemberName.getText().toString();
        String num = newMemberPhone.getText().toString();
        viewModel.addNewGroupMember(name, num);
        returnToHome();
    }

    private void returnToHome() {
        OpenViewHelper.openViewPutExtra(GroupPageView.class, createNewMembersView.this, viewModel.getGroup().getGroupName());
    }

    private void initWidgets() {
        this.newMemberName = findViewById(R.id.editTextTextPersonName);
        this.newMemberPhone = findViewById(R.id.editTextPhoneGP);
        this.createNewMemberButton = findViewById(R.id.createNewMember);
        this.returnButton = findViewById(R.id.returnButton);
    }
}
