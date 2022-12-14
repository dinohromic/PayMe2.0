/* The responsibility of this class is presenting events in a listed order
inside a fragment contained in the GroupView.
* */
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

import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for the fragment of events in the group page view
 */
public class EventFragmentGroupPage extends Fragment {
    private Button createNewEventButton, inactivateAllEventsButton;
    private Group group;
    private LinearLayout eventContainer;
    private GroupPageViewModel gpViewModel;

    public EventFragmentGroupPage(){
        //Public construct
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_group_page, null);
        initWidgetsEventFragment(view);
        setAddNewEventListener(createNewEventButton);
        setInactivateAllEventsButton(inactivateAllEventsButton);
        initViewModel();
        populateEventContainer();
        return view;
    }

    private void initViewModel() {
        ViewModelFactory vmFactory = ViewModelFactory.INSTANCE;
        this.gpViewModel = new ViewModelProvider(this, vmFactory).get(GroupPageViewModel.class);
    }

    private void populateEventContainer() {
        for(int i = group.getGroupEvents().size() - 1; i >= 0; i--) {
            Event event = group.getGroupEvents().get(i);
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
        CheckBox eventActiveCheckBox = cardView.findViewById(R.id.checkBoxEventActive);
        if(event.getActiveStatus()) {
            eventActiveCheckBox.setChecked(true);
        }
        eventActiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    gpViewModel.setEventActive(event);
                    gpViewModel.setTotalExpenditureText();
                }
                if(!isChecked) {
                    gpViewModel.setEventInactive(event);
                    gpViewModel.setTotalExpenditureText();
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
        if(!event.getActiveStatus()) {
            checkBox.setChecked(false);
        }
    }

    private void setAddNewEventListener(Button createNewEvent) {
        createNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(EventCreateView.class, getActivity(), group.getGroupName());
            }
        });
    }

    private void setInactivateAllEventsButton(Button inactivateAllEvents){
        inactivateAllEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gpViewModel.setAllEventsInactive();
                gpViewModel.setTotalExpenditureText();
                eventContainer.removeAllViews();
                populateEventContainer();
            }
        });
    }

    private void initWidgetsEventFragment(View view) {
        this.eventContainer = view.findViewById(R.id.eventFragmentContainer);
        this.createNewEventButton = (Button) view.findViewById(R.id.addEventButton);
        this.inactivateAllEventsButton = view.findViewById(R.id.inactivateAllEventsButton);
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}