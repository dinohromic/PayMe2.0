package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.payme20.model.Factory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Map;


public class EventCreateView extends AppCompatActivity {

    TextInputEditText eventName;

    Spinner memberSpinner;
    EditText eventDate;
    RadioGroup paymentType;
    LinearLayout container;
    Button createEvent;

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
        initEventMembersCards();
        initCreateButton();
        initEventName();
    }

    private void initEventName() {
        eventName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    ecViewmodel.setEventName(eventName.getText().toString());
                }
            }
        });
    }

    private void initCreateButton() {
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ecViewmodel.createEvent();
            }
        });
    }

    private void initEventMembersCards() {
        populateList();
    }

    private void populateList() {
        for (Map.Entry<String, Member> memberMap: ecViewmodel.getGroupMembers().entrySet()) {
            Member member = memberMap.getValue();
            addCard(member);
        }
    }

    private void addCard(Member m) {
        View view = getLayoutInflater().inflate(R.layout.event_member_card, null);
        TextView name = view.findViewById(R.id.eventMemberName);
        name.setText(m.getUserName());
        setListenersOnEventMemberCard(view, name.getText());
        container.addView(view);
    }

    private void setListenersOnEventMemberCard(View view, CharSequence name) {
        CheckBox checkbox = view.findViewById(R.id.eventMemberIncluded);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b) {
                    ecViewmodel.removeEventMember((String) name);
                }
                if(b) {
                    ecViewmodel.addEventMember((String) name);
                }
                updateMemberSpinner();
            }
        });

        EditText amount = view.findViewById(R.id.eventMemberAmount);
        amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    ecViewmodel.setMemberPayment(Integer.parseInt(amount.getText().toString()), (String) name);
                }
            }
        });
    }

    private void initMemberSpinner() {
        updateMemberSpinner();
        memberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String memberName = memberSpinner.getSelectedItem().toString();
                Member member = ecViewmodel.getGroupMembers().get(memberName);
                ecViewmodel.setPayer(member);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateMemberSpinner() {
        List<String> memberUserNames = ecViewmodel.getEventMembers();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, memberUserNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberSpinner.setAdapter(arrayAdapter);
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
        this.eventName = findViewById(R.id.eventName);
        this.eventDate = findViewById(R.id.editEventDate);
        this.paymentType = findViewById(R.id.paymentType);
        this.memberSpinner = findViewById(R.id.chooseMemberSpinner);
        this.container = findViewById(R.id.eventMembersContainer);
        this.createEvent = findViewById(R.id.buttonCreateEvent);
    }
}
