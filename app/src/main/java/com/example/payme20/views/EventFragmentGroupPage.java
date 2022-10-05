package com.example.payme20.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.payme20.R;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Event;
import com.example.payme20.model.Group;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventFragmentGroupPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragmentGroupPage extends Fragment {
    private Button createNewEvent;
    private Group group;
    private LinearLayout eventContainer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventFragmentGroupPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFragmentGroupPage.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFragmentGroupPage newInstance(String param1, String param2) {
        EventFragmentGroupPage fragment = new EventFragmentGroupPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_group_page, null);
        initWidgetsEventFragment(view);
        setAddNewEventListener(createNewEvent);
        populateEventContainer();

        return view;
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

            }
        });
    }

    private void populateCard(View cardView, Event event) {
        TextView eventName = cardView.findViewById(R.id.eventName);
        eventName.setText(event.getEventName());
        TextView eventDate = cardView.findViewById(R.id.eventDate);
        eventDate.setText(event.getEventDate());
    }

    private void setAddNewEventListener(Button createNewEvent) {
        createNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(getActivity(), EventCreateView.class);
                intent.putExtra("GROUP", group);
                startActivity(intent);*/
                OpenViewHelper.openViewPutExtra(EventCreateView.class, getActivity(), group);
            }
        });
    }

    private void initWidgetsEventFragment(View view) {
        this.eventContainer = view.findViewById(R.id.eventFragmentContainer);
        this.createNewEvent = (Button) view.findViewById(R.id.addEventButton);
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}