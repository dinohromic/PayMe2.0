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
import com.example.payme20.model.Group;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupListView extends AppCompatActivity {

    GroupListViewModel groupListViewModel;

    ImageButton currentGroupsReturnButton;
    FloatingActionButton createGroupFAB;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_groups);
        this.groupListViewModel = new GroupListViewModel();
        initWidgets();

        populateGroupList(groupListViewModel.getGroupList());
        setOpenViewListener(this.currentGroupsReturnButton, MainActivity.class);
        FABonClickListener(createGroupFAB);


    }

    private void populateGroupList(ArrayList<Group> groups) {

        for(Group group : groups){
            addCard(groupListViewModel.getGroupName(group));
        }

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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupListView.this, GroupPageView.class);
                intent.putExtra("GROUP_KEY", groupListViewModel.getGroupList().get(0));
                GroupListView.this.startActivity(intent);
            }
        });

        container.addView(view);

    }

    private void listener(){

    }

    private void FABonClickListener(FloatingActionButton FAB){
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //#TODO send user to create group steps that is: create_group.xml --> add_members.xml --> back HERE?
                Intent intent = new Intent(GroupListView.this, MemberView.class);
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
