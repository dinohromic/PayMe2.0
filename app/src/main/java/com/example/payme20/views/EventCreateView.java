package com.example.payme20.views;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import com.example.payme20.R;
import com.google.android.material.textfield.TextInputLayout;


public class EventCreateView extends AppCompatActivity {

    TextInputLayout eventName;
    Spinner memberSpinner;
    EditText eventDate;
    RadioGroup paymentType;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creare_event);
        initiate();
    }

    private void initiate(){
        this.eventName = findViewById(R.id.eventNameInput);
        this.eventDate = findViewById(R.id.editEventDate);
        this.paymentType = findViewById(R.id.payemtnType);
        this.memberSpinner = findViewById(R.id.chooseMemberSpinner);
    }
}
