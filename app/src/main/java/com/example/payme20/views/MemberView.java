package com.example.payme20.views;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.R;

public class MemberView extends AppCompatActivity{

    private Member member;
    private Group group;

    public void onAddMemberBtnClick(View view){
        TextView TxtName = findViewById(R.id.edtTxtName);
        TextView TxtPhoneNumber = findViewById(R.id.editTextPhone);
    }

}
