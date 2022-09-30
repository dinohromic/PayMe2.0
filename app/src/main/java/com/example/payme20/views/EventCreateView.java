package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.payme20.R;
import com.example.payme20.ViewModels.EventCreateViewmodel;
import com.example.payme20.ViewModels.ViewModelFactory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class EventCreateView extends AppCompatActivity {

    TextInputLayout eventName;

    Spinner memberSpinner;
    EditText eventDate;
    RadioGroup paymentType;
    LinearLayout container;

    EventCreateViewmodel ecViewmodel;
    Group group;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creare_event);
        group = ((Group) getIntent().getSerializableExtra("GROUP"));
        initiate();
        ViewModelFactory vmFactory = new ViewModelFactory();
        vmFactory.add(new EventCreateViewmodel(group));
        ecViewmodel = new ViewModelProvider(this, vmFactory).get(EventCreateViewmodel.class);
        initPaymentType();
        initMemberSpinner();
        initEventMembersList();
    }

    private void initEventMembersList() {
        populateList();

    }

    private void populateList() {
        for(Member m : ecViewmodel.getGroupMembers()) {
            addCard(m);
        }
    }

    private void addCard(Member m) {
        View view = getLayoutInflater().inflate(R.layout.event_member_card, null);
        TextView name = view.findViewById(R.id.eventMemberName);
        name.setText(m.getUserName());
        setListenersOnEventMemberCard(view);
        container.addView(view);
    }

    private void setListenersOnEventMemberCard(View view) {
        CheckBox checkbox = view.findViewById(R.id.eventMemberIncluded);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b) {
                    ecViewmodel.removeEventMember(view.findViewById(R.id.eventMemberName).toString());

                }
                if(b) {
                    ecViewmodel.addEventMember(view.findViewById(R.id.eventMemberName).toString());
                }
            }
        });
        TextInputEditText amount = view.findViewById(R.id.eventMemberAmount);
        amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
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
        paymentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedButton = radioGroup.findViewById(i);
                boolean isChecked = checkedButton.isChecked();
                if(isChecked) {
                    String text = (String) checkedButton.getText();
                    ecViewmodel.setDebtUpdater(text);
                }
            }
        });
    }

    private void initiate(){
        this.eventName = findViewById(R.id.eventNameInput);
        this.eventDate = findViewById(R.id.editEventDate);
        this.paymentType = findViewById(R.id.paymentType);
        this.memberSpinner = findViewById(R.id.chooseMemberSpinner);
        this.container = findViewById(R.id.eventMembersContainer);
    }
}
