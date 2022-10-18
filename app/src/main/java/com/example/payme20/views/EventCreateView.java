/* The responsibility of this class is to act as an view during the creation of events,
thereby taking input from a user. */
package com.example.payme20.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.payme20.R;
import com.example.payme20.helpers.DatePickerDialogHelper;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.view_models.EventCreateViewmodel;
import com.example.payme20.view_models.ViewModelFactory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class EventCreateView extends AppCompatActivity {

    private TextInputEditText eventNameInput;
    private Spinner memberSpinner;
    private RadioGroup paymentType;
    private LinearLayout cardContainer;
    private Button createEventButton;
    private Button dateButton;
    private DatePickerDialog datePickerDialog;
    private EventCreateViewmodel ecViewmodel;
    private final DatePickerDialogHelper datePickerDialogHelper = new DatePickerDialogHelper();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creare_event);
        initiate();
        createViewModel();
        initPaymentType();
        initMemberSpinner();
        initEventMembersCards();
        initCreateButton();
        initEventName();
        initDatePickerDialog();
        setCurrentDateOnView();
    }

    private String retrieveIntentGroup(){
        return (String) getIntent().getSerializableExtra("GROUP_NAME_KEY");
    }

    private void createViewModel(){
        ViewModelFactory vmFactory = ViewModelFactory.INSTANCE;
        vmFactory.add(new EventCreateViewmodel(retrieveIntentGroup()));
        ecViewmodel = new ViewModelProvider(this, vmFactory).get(EventCreateViewmodel.class);
    }

    private void initDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dataSetListener = listenerForDateButton();
        this.datePickerDialog = datePickerDialogHelper.initiateDatePickerDialog(dataSetListener, this);
        this.dateButton.setOnClickListener(view -> this.datePickerDialog.show());
    }

    private DatePickerDialog.OnDateSetListener listenerForDateButton(){
        return (datePicker, year, month, day) -> {
            month = month + 1;
            this.dateButton.setText(datePickerDialogHelper.makeDateString(day, month, year));
            this.ecViewmodel.setDate(datePickerDialogHelper.makeDateString(day, month, year));
        };

    }

    private void setCurrentDateOnView(){
        this.dateButton.setText(datePickerDialogHelper.getCurrentDate());
        this.ecViewmodel.setDate(datePickerDialogHelper.getCurrentDate());
    }

    private void initEventName() {
        eventNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                ecViewmodel.setEventName(Objects.requireNonNull(eventNameInput.getText()).toString());
            }
        });
    }

    private void initCreateButton() {
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(eventNameInput.getText().toString())){
                    Toast.makeText(EventCreateView.this,"Event name needed", Toast.LENGTH_SHORT).show();
                }
                else if(ecViewmodel.getEventMembers().size()<2){
                    Toast.makeText(EventCreateView.this,"At least 2 members required ", Toast.LENGTH_SHORT).show();
                }
                else{
                    ecViewmodel.createEvent();
                    OpenViewHelper.openViewPutExtra(GroupPageView.class, EventCreateView.this, ecViewmodel.getGroup().getGroupName(), 0);
                }
            }
        });
    }

    private void initEventMembersCards() {
        populateList();
    }

    private void populateList() {
        for (Map.Entry<Integer, Member> memberMap: ecViewmodel.getGroupMembers().entrySet()) {
            Member member = memberMap.getValue();
            if(member.getActiveStatus()) {
                addCard(member);
            }
        }
    }

    private void addCard(Member m) {
        View view = getLayoutInflater().inflate(R.layout.event_member_card, null);
        TextView name = view.findViewById(R.id.eventMemberName);
        name.setText(m.getUserName() + " (id: " + m.getId() + ")");
        setListenersOnEventMemberCard(view, m);
        cardContainer.addView(view);
    }

    private void setListenersOnEventMemberCard(View view, Member m) {
        CheckBox checkbox = view.findViewById(R.id.eventMemberIncluded);
        EditText amount = view.findViewById(R.id.eventMemberAmount);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b) {
                    ecViewmodel.removeEventMember(m);
                    amount.setEnabled(false);
                }
                if(b) {
                    ecViewmodel.addEventMember(m);
                    amount.setEnabled(true);
                }
                updateMemberSpinner();
            }
        });

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(amount.getText().toString().equals("")) {
                    ecViewmodel.setMemberPayment(0, m.getId());
                }
                else {
                    ecViewmodel.setMemberPayment(Integer.parseInt(amount.getText().toString()), m.getId());
                }}
        });
    }

    private void initMemberSpinner() {
        updateMemberSpinner();
        memberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for(Member m : ecViewmodel.getEventMembers()) {
                    if(m.getUserName().equals(memberSpinner.getSelectedItem().toString())) {
                        ecViewmodel.setPayer(ecViewmodel.getGroupMembers().get(m.getId()));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateMemberSpinner() {
        List<String> memberUserNames = new ArrayList<>();
        for(Member m : ecViewmodel.getEventMembers()) {
            if(m.getActiveStatus()) {
                memberUserNames.add(m.getUserName());
            }
        }
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
        this.eventNameInput = findViewById(R.id.eventName);
        this.dateButton = findViewById(R.id.datePickerButton);
        this.paymentType = findViewById(R.id.paymentType);
        this.memberSpinner = findViewById(R.id.chooseMemberSpinner);
        this.cardContainer = findViewById(R.id.eventMembersContainer);
        this.createEventButton = findViewById(R.id.buttonCreateEvent);
    }
}
