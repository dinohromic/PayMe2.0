package com.example.payme20.views;

import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.R;
import com.google.android.material.textfield.TextInputLayout;

public class EventView extends AppCompatActivity {
    public void createEvent(){
        TextInputLayout eventName = findViewById(R.id.eventNameInput);
        EditText eventdate= findViewById(R.id.editEventDate);
        RadioGroup paymentType = findViewById(R.id.payemtnType);
        TextInputLayout payer = findViewById(R.id.Payer);

    }
}
