package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.R;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.view_models.createNewMembersViewModel;

public class createNewMembersView extends AppCompatActivity {

    private EditText newMemberName;
    private EditText newMemberPhone;
    private Button createNewMemberButton;
    private ImageButton returnButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog_view);
        initWidgets();
        initViewModel();
        setOnClickListener(createNewMemberButton);


    }

    private void initViewModel() {
        Group belongsToGroup = (Group) getIntent().getSerializableExtra("GROUP_KEY");
        createNewMembersViewModel viewModel = new createNewMembersViewModel(belongsToGroup);
    }

    private void setOnClickListener(Button createNewMemberButton) {
        createNewMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewMember(newMemberName, newMemberName);

            }
        });
    }

    private void createNewMember(EditText newMemberName, EditText newMemberName1) {
    }

    private void initWidgets() {
        this.newMemberName = findViewById(R.id.editTextTextPersonName);
        this.newMemberPhone = findViewById(R.id.editTextPhoneGP);
        this.createNewMemberButton = findViewById(R.id.createNewMember);
        this.returnButton = findViewById(R.id.returnButton);
    }

    private void setGroup (Group group){

    }

}
