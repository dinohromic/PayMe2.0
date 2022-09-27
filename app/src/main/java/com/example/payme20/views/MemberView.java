package com.example.payme20.views;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
    private CheckBox agreementCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_members);
    }

    private void initViews(){

    }

    public void onAddMemberBtnClick(View view){
        edtName = findViewById(R.id.edtTxtName);
        edtPhoneNumber = findViewById(R.id.editTextPhone);
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
