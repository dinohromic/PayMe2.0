package com.example.payme20.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Event;
import com.example.payme20.view_models.EventPageViewModel;
import com.example.payme20.view_models.MemberPageViewModel;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

public class EventPageView extends AppCompatActivity {

    private ImageButton memberPageReturnButton;
    private TextView eventName;
    private TextView eventDate;
    private TextView eventPayer;
    private TextView eventPayment;
    private ImageButton returnButton;

    private EventPageViewModel epViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_page);
        initWidgets();
        initViewModel();
        populateView();
        setReturnListener();
    }

    private void setReturnListener() {
        this.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(GroupPageView.class, EventPageView.this, epViewmodel.getGroup());
            }
        });
    }

    private void populateView() {
        String eventName = epViewmodel.getEventName();
        String eventDate = "Date: " + epViewmodel.getEventDate();
        String payer = epViewmodel.getPayerName() + " paid for this event";
        String paymentType = "Payment of this event was: " + epViewmodel.getEventPaymentType();
        this.eventName.setText(eventName);
        this.eventDate.setText(eventDate);
        this.eventPayment.setText(paymentType);
        this.eventPayer.setText(payer);

    }

    private void initViewModel() {
        Group belongsToGroup = (Group) getIntent().getSerializableExtra("GROUP_KEY");
        Event event = (Event) getIntent().getSerializableExtra("EVENT_KEY");
        this.epViewmodel = new EventPageViewModel(belongsToGroup, event);
    }

    private void initWidgets() {
        this.eventName = findViewById(R.id.textEventName);
        this.eventDate = findViewById(R.id.textDate);
        this.eventPayer = findViewById(R.id.textPayer);
        this.eventPayment = findViewById(R.id.textPayment);
        this.returnButton = findViewById(R.id.goBackButton);
    }

}
