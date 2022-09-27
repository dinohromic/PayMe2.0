package com.example.payme20.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.payme20.MainActivity;
import com.example.payme20.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GroupListView extends AppCompatActivity {

    ImageButton currentGroupsReturnButton;
    LinearLayout groupCardHolder;
    FloatingActionButton createGroupFAB;
    CardView groupCardView;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_groups);
        initiateClickable();
        setOpenViewListener(this.currentGroupsReturnButton, MainActivity.class);

        createGroupFAB = findViewById(R.id.createGroupFAB);
        container = findViewById(R.id.groupListContainer);
        createGroupFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //#TODO send user to create_group.xml --> add_members. When user is done go back here.

                addCard("Test");
            }
        });


    }
    private void addCard(String name) {
        View view = getLayoutInflater().inflate(R.layout.group_card_view, null);

        TextView groupName = view.findViewById(R.id.activeGroupsName);

        groupName.setText(name);
        container.addView(view);
    }

    private void initiateClickable() {
        this.currentGroupsReturnButton = findViewById(R.id.currentGorupsReturnButton);
        this.groupCardHolder = findViewById(R.id.groupListContainer);
    }

    private void setOpenViewListener(ImageView openButton, Class<?> viewToOpen){
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(GroupListView.this, viewToOpen);
                GroupListView.this.startActivity(myIntent);
            }
        });
    }

}
