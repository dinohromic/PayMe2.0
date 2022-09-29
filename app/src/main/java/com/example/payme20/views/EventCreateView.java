package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.payme20.R;
import com.example.payme20.ViewModels.EventCreateViewmodel;
import com.example.payme20.model.DetailedDebtUpdater;
import com.example.payme20.model.Group;
import com.example.payme20.model.IDebtUpdater;
import com.example.payme20.model.Member;
import com.example.payme20.model.SplitDebtUpdater;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class EventCreateView extends AppCompatActivity {

    TextInputLayout eventName;

    Spinner memberSpinner;
    EditText eventDate;
    RadioGroup paymentType;

    EventCreateViewmodel ecViewmodel;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creare_event);
        Group group = ((Group) getIntent().getSerializableExtra("GROUP"));
        initiate();
        ecViewmodel = new ViewModelProvider(this).get(EventCreateViewmodel.class);
        initPaymentType();
        initMemberSpinner();

    }

    private void initMemberSpinner() {
        ArrayList<String> memberUserNames = new ArrayList<>();
        memberUserNames.add("Anton");
        memberUserNames.add("Oscar");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, memberUserNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberSpinner.setAdapter(arrayAdapter);
        memberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                /*for(Member m : ListaAvMembers) {
                    if(m.getUserName().equals(arrayAdapter.getItem(i)))
                        ecViewmodel.setPayer(m);
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initPaymentType() {
        ecViewmodel.setDebtUpdater(new SplitDebtUpdater());
        paymentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedButton = radioGroup.findViewById(i);
                boolean isChecked = checkedButton.isChecked();
                if(isChecked) {
                    String text = (String) checkedButton.getText();
                    IDebtUpdater debtUpdater = new SplitDebtUpdater();
                    switch (text) {
                        case "split":
                            debtUpdater = new SplitDebtUpdater();
                            break;
                        case "detailed":
                            debtUpdater = new DetailedDebtUpdater();
                            break;
                    }
                    ecViewmodel.setDebtUpdater(debtUpdater);
                }
            }
        });
    }

    private void initiate(){
        this.eventName = findViewById(R.id.eventNameInput);
        this.eventDate = findViewById(R.id.editEventDate);
        this.paymentType = findViewById(R.id.paymentType);
        this.memberSpinner = findViewById(R.id.chooseMemberSpinner);
    }
}
