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
import com.example.payme20.view_models.GroupListViewModel;
import com.example.payme20.helpers.OpenViewHelper;
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
        for(int i = 0; i < groups.size(); i++){
            addCard(groups.get(i));
        }
    }

    private void initWidgets() {
        this.currentGroupsReturnButton = findViewById(R.id.currentGorupsReturnButton);
        this.createGroupFAB = findViewById(R.id.createGroupFAB);
        this.container = findViewById(R.id.groupListContainer);
    }

    private void addCard(Group group) {
        View view = getLayoutInflater().inflate(R.layout.group_card_view, null);
        TextView groupName = view.findViewById(R.id.activeGroupsName);
        groupName.setText(groupListViewModel.getGroupName(group));
        setListenerOnGroupCard(view, group);
        container.addView(view);
    }

    private void setListenerOnGroupCard(View viewToGetListener, Group group){
        viewToGetListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(GroupPageView.class, GroupListView.this, group);
            }
        });
    }

    private void FABonClickListener(FloatingActionButton FAB){
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupListView.this, MemberView.class);
                GroupListView.this.startActivity(intent);

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
