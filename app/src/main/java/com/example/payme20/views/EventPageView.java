/* The responsibility of this class is displaying data about an event. */
package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Event;
import com.example.payme20.view_models.EventPageViewModel;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;

import java.util.Map;
/**
 * This class is responsible for the view of an old event
 */
public class EventPageView extends AppCompatActivity {

    private TextView eventName, eventDate, eventPayer, eventPayment, eventActiveStatus;
    private ImageButton returnButton;
    private LinearLayout memberContainer;
    private EventPageViewModel epViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_page);
        initWidgets();
        initViewModel();
        populateView();
        setReturnListener();
        initEventMembersCards();
    }

    private void initEventMembersCards() {
        populateList();
    }

    private void populateList() {
        for (Map.Entry<Member, Integer> memberMap: epViewModel.getEventPaymentDetails().entrySet()) {
            Member member = memberMap.getKey();
            int amount = memberMap.getValue();
            addCard(member, amount);
        }
    }

    private void addCard(Member member, int amount) {
        View view = getLayoutInflater().inflate(R.layout.event_page_member_card, null);
        TextView memberName = view.findViewById(R.id.eventPageMemberName);
        memberName.setText(member.getUserName());
        TextView memberAmount = view.findViewById(R.id.eventPageMemberAmount);
        memberAmount.setText(String.format("%d kr", amount));
        memberContainer.addView(view);
    }

    private void setReturnListener() {
        this.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(GroupPageView.class, EventPageView.this, epViewModel.getGroup().getGroupName(), 0);
            }
        });
    }

    private void populateView() {
        String eventName = epViewModel.getEventName();
        String eventDate = "Date: " + epViewModel.getEventDate();
        int totalGroupAMount = epViewModel.getEventTotalPrice();
        String payer = String.format("%s paid a total of %d kr for this event", epViewModel.getPayerName(), totalGroupAMount);
        String paymentType = "Payment of this event was: " + epViewModel.getEventPaymentType();
        String activeStatus = epViewModel.getEventActiveStatus() ? "active" : "inactive";
        String eventActiveStatusText = "This event is " + activeStatus + ".";
        this.eventName.setText(eventName);
        this.eventDate.setText(eventDate);
        this.eventPayment.setText(paymentType);
        this.eventPayer.setText(payer);
        this.eventActiveStatus.setText(eventActiveStatusText);

    }

    private void initViewModel() {
        Group belongsToGroup = (Group) getIntent().getSerializableExtra("GROUP_KEY");
        Event event = (Event) getIntent().getSerializableExtra("EVENT_KEY");
        this.epViewModel = new EventPageViewModel(belongsToGroup.getGroupName(), event);
    }

    private void initWidgets() {
        this.eventName = findViewById(R.id.textEventName);
        this.eventDate = findViewById(R.id.textDate);
        this.eventPayer = findViewById(R.id.textPayer);
        this.eventPayment = findViewById(R.id.textPayment);
        this.returnButton = findViewById(R.id.goBackButton);
        this.memberContainer = findViewById(R.id.containerEventMembers);
        this.eventActiveStatus = findViewById(R.id.textEventDone);
    }

}
