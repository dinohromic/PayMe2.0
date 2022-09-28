package com.example.payme20.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.MainActivity;
import com.example.payme20.R;
import com.example.payme20.ViewModels.GroupListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GroupListView extends AppCompatActivity {

    GroupListViewModel groupListViewModel;

    ImageButton currentGroupsReturnButton;
    LinearLayout groupCardHolder;
    FloatingActionButton createGroupFAB;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_groups);
        this.groupListViewModel = new GroupListViewModel();
        initWidgets();
        setOpenViewListener(this.currentGroupsReturnButton, MainActivity.class);
        FABonClickListener(createGroupFAB);

    }
    private void initWidgets() {
        this.currentGroupsReturnButton = findViewById(R.id.currentGorupsReturnButton);
        this.createGroupFAB = findViewById(R.id.createGroupFAB);
        this.container = findViewById(R.id.groupListContainer);
    }

    private void addCard(String name) {
        View view = getLayoutInflater().inflate(R.layout.group_card_view, null);
        TextView groupName = view.findViewById(R.id.activeGroupsName);

        groupName.setText(name);
        container.addView(view);
    }

    private void FABonClickListener(FloatingActionButton FAB){
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //#TODO send user to create group steps that is: create_group.xml --> add_members.xml --> back HERE?
                Intent intent = new Intent(GroupListView.this, GroupCreateView.class);
                GroupListView.this.startActivity(intent);
                //addCard("Test"); //Group.getGroupName() här???
            }
        });
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
