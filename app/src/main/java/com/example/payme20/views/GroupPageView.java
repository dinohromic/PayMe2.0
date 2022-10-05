package com.example.payme20.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.payme20.R;
import com.example.payme20.helpers.GroupPageAdapter;
import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.model.Group;
import com.google.android.material.tabs.TabLayout;


public class GroupPageView extends AppCompatActivity {

    private TabLayout groupPageTabs;
    private ViewPager groupPageViewPager;
    private TextView groupPageGroupName;
    private ImageButton returnButton;
    private EventFragmentGroupPage eventFragmentGroupPage;
    private MemberFragmentGroupPage memberFragmentGroupPage;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_page);
        findViewForWidgets();
        Group currentGroup = (Group) getIntent().getSerializableExtra("GROUP_KEY");
        populateView(currentGroup);
        createAdapterForFragments();
        setGroupFragment(currentGroup);
        setListenerReturnButton();
    }

    private void createAdapterForFragments(){
        GroupPageAdapter gpAdapter = new GroupPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.eventFragmentGroupPage = new EventFragmentGroupPage();
        this.memberFragmentGroupPage = new MemberFragmentGroupPage();
        gpAdapter.addGroupPageFragments(eventFragmentGroupPage,"Events");
        gpAdapter.addGroupPageFragments(memberFragmentGroupPage, "Members");
        this.groupPageTabs.setupWithViewPager(this.groupPageViewPager);
        this.groupPageViewPager.setAdapter(gpAdapter);
    }

    private void setGroupFragment(Group currentGroup){
        this.eventFragmentGroupPage.setGroup(currentGroup);
        this.memberFragmentGroupPage.setGroup(currentGroup);
    }

    private void populateView(Group currentGroup){
        this.groupPageGroupName.setText(currentGroup.getGroupName());
    }

    private void findViewForWidgets(){
        this.groupPageGroupName = findViewById(R.id.groupPageGroupName);
        this.returnButton =  findViewById(R.id.groupPageReturnButton);
        this.groupPageTabs = findViewById(R.id.groupPageTabLayout);
        this.groupPageViewPager= findViewById(R.id.groupPageViewPager);
    }

    private void setListenerReturnButton(){
        this.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openView(GroupListView.class, GroupPageView.this);
            }
        });
    }
}
