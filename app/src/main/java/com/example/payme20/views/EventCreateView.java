package com.example.payme20.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
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
import com.example.payme20.view_models.EventCreateViewmodel;
import com.example.payme20.view_models.ViewModelFactory;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class EventCreateView extends AppCompatActivity {

    TextInputEditText eventName;

    Spinner memberSpinner;
    RadioGroup paymentType;
    LinearLayout container;
    Button createEvent;
    Button dateButton;
    DatePickerDialog datePickerDialog;

    EventCreateViewmodel ecViewmodel;
    Group group;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creare_event);
        group = ((Group) getIntent().getSerializableExtra("GROUP_KEY"));
        System.out.println(group.getGroupEvents());
        initiate();
        ViewModelFactory vmFactory = new ViewModelFactory();
        vmFactory.add(new EventCreateViewmodel(group));
        ecViewmodel = new ViewModelProvider(this, vmFactory).get(EventCreateViewmodel.class);
        initPaymentType();
        initMemberSpinner();
        initEventMembersCards();
        initCreateButton();
        initEventName();
        initDatePicker();
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dataSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateButton.setText(date);
            ecViewmodel.setDate(date);
            System.out.println(date);
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dataSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        dateButton.setText(getTodaysDate());
        ecViewmodel.setDate(getTodaysDate());
        System.out.println(getTodaysDate());
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return day + "-" + month + "-" + year;
    }

    private void initEventName() {
        eventName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                ecViewmodel.setEventName(Objects.requireNonNull(eventName.getText()).toString());
            }
        });
    }

    private void initCreateButton() {
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(eventName.getText().toString())){
                    Toast.makeText(EventCreateView.this,"Event name needed", Toast.LENGTH_SHORT).show();
                }
                else{
                    ecViewmodel.createEvent();
                }
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
        EditText amount = view.findViewById(R.id.eventMemberAmount);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b) {
                    ecViewmodel.removeEventMember((String) name);
                    amount.setEnabled(false);
                    //amount.setHintTextColor(F6F6F6);
                }
                if(b) {
                    ecViewmodel.addEventMember((String) name);
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
                if(amount.getText().toString().equals(""))
                    ecViewmodel.setMemberPayment(0, (String) name);
                else
                    ecViewmodel.setMemberPayment(Integer.parseInt(amount.getText().toString()), (String) name);
            }
        });
    }

//    private void disableEditText(EditText editText) {
//        editText.setEnabled(false);
//        editText.setCursorVisible(false);
//        editText.setBackgroundColor(Color.TRANSPARENT);
//    }
//    private void enableEditText(EditText editText) {
//        editText.setFocusable(true);
//        editText.setEnabled(true);
//        editText.setCursorVisible(true);
//    }

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
        this.dateButton = findViewById(R.id.datePickerButton);
        this.paymentType = findViewById(R.id.paymentType);
        this.memberSpinner = findViewById(R.id.chooseMemberSpinner);
        this.container = findViewById(R.id.eventMembersContainer);
        this.createEvent = findViewById(R.id.buttonCreateEvent);
    }
}
