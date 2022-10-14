package com.example.payme20.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.view_models.GroupPageViewModel;
import com.example.payme20.view_models.ViewModelFactory;


public class EventFragmentGroupPage extends Fragment {
    private Button createNewEventButton;
    private Group group;
    private LinearLayout eventContainer;
    private CheckBox eventActiveCheckBox;
    private GroupPageViewModel gpViewModel;

    public EventFragmentGroupPage(){
        //Public construct
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_group_page, null);
        initWidgetsEventFragment(view);
        setAddNewEventListener(createNewEventButton);
        initViewModel();
        populateEventContainer();
        return view;
    }

    private void initViewModel() {
        ViewModelFactory vmFactory = ViewModelFactory.INSTANCE;
        this.gpViewModel = new ViewModelProvider(this, vmFactory).get(GroupPageViewModel.class);
    }

    private void populateEventContainer() {
        for(Event event : group.getGroupEvents()) {
            View cardView = getLayoutInflater().inflate(R.layout.event_card, null);
            populateCard(cardView, event);
            setEventCardListener(cardView, event);
            eventContainer.addView(cardView);
        }
    }

    private void setEventCardListener(View cardView, Event event) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(EventPageView.class, getActivity(), event, group);
            }
        });
        eventActiveCheckBox = cardView.findViewById(R.id.checkBoxEventActive);
        if(event.getActiveStatus())
            eventActiveCheckBox.setChecked(true);
        eventActiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    gpViewModel.setEventActive(event);
                    gpViewModel.setText();
                }
                if(!isChecked) {
                    gpViewModel.setEventInactive(event);
                    gpViewModel.setText();
                }
            }
        });
    }

    private void populateCard(View cardView, Event event) {
        TextView eventName = cardView.findViewById(R.id.eventName);
        eventName.setText(event.getEventName());
        TextView eventDate = cardView.findViewById(R.id.eventDate);
        eventDate.setText(event.getEventDate());
        CheckBox checkBox = cardView.findViewById(R.id.checkBoxEventActive);
        if(!event.getActiveStatus())
            checkBox.setChecked(false);
    }

    private void setAddNewEventListener(Button createNewEvent) {
        createNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(EventCreateView.class, getActivity(), group.getGroupName());
            }
        });
    }

    private void initWidgetsEventFragment(View view) {
        this.eventContainer = view.findViewById(R.id.eventFragmentContainer);
        this.createNewEventButton = (Button) view.findViewById(R.id.addEventButton);
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}