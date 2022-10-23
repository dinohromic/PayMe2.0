/* The responsibility of this class is to list all groups in a view
* */
package com.example.payme20.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.payme20.MainActivity;
import com.example.payme20.R;
import com.example.payme20.view_models.GroupListViewModel;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Group;
import com.example.payme20.view_models.GroupPageViewModel;
import com.example.payme20.view_models.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;
/**
 * This class is responsible for the view of the page where the user can see all current groups
 */
public class GroupListView extends AppCompatActivity {

    private GroupListViewModel groupListViewModel;
    private ImageButton currentGroupsReturnButton;
    private FloatingActionButton createGroupFAB; //FAB = Floating Action Button
    private LinearLayout groupCardContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_groups);
        initViewModel();
        initWidgets();
        populateGroupList(groupListViewModel.getGroups());
        setOpenViewListener(this.currentGroupsReturnButton, MainActivity.class);
        FABonClickListener(createGroupFAB);
    }

    private void initViewModel() {
        ViewModelFactory vmFactory = ViewModelFactory.INSTANCE;
        vmFactory.add(new GroupListViewModel());
        this.groupListViewModel = new ViewModelProvider(this, vmFactory).get(GroupListViewModel.class);
    }

    private void populateGroupList(Map<String,Group> groups) {
        for (Map.Entry<String, Group> groupMap : groups.entrySet()) {
            Group group = groupMap.getValue();
            createCardForContainer(group);
        }
    }

    private void initWidgets() {
        this.currentGroupsReturnButton = findViewById(R.id.currentGorupsReturnButton);
        this.createGroupFAB = findViewById(R.id.createGroupFAB);
        this.groupCardContainer = findViewById(R.id.groupListContainer);
    }

    private void createCardForContainer(Group group) {
        View cardView = createCard(group);
        groupCardContainer.addView(cardView);
    }

    private View createCard(Group group){
        View cardView = getLayoutInflater().inflate(R.layout.group_card_view, null);
        TextView groupName = cardView.findViewById(R.id.activeGroupsName);
        groupName.setText(group.getGroupName());
        setListenerOnGroupCard(cardView, group);
        return cardView;
    }

    private void setListenerOnGroupCard(View viewToGetListener, Group group){
        viewToGetListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openViewPutExtra(GroupPageView.class, GroupListView.this, group.getGroupName(), 0);
            }
        });
    }

    private void FABonClickListener(FloatingActionButton FAB){
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupListView.this, GroupCreateView.class);
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
