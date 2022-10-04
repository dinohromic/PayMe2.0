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
    EventFragmentGroupPage eventFragmentGroupPage;
    MemberFragmentGroupPage memberFragmentGroupPage;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_page);
        findViewForWidgets();

        Group myObject = (Group) getIntent().getSerializableExtra("GROUP_KEY");
        groupPageGroupName.setText(myObject.getGroupName());


        GroupPageAdapter gpAdapter = new GroupPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        eventFragmentGroupPage = new EventFragmentGroupPage();
        memberFragmentGroupPage = new MemberFragmentGroupPage();
        eventFragmentGroupPage.setGroup(myObject);
        memberFragmentGroupPage.setGroup(myObject);

        gpAdapter.addGroupPageFragments(eventFragmentGroupPage,"Events");
        gpAdapter.addGroupPageFragments(memberFragmentGroupPage, "Members");

        groupPageViewPager.setAdapter(gpAdapter);
        this.groupPageTabs.setupWithViewPager(groupPageViewPager);

        setListenerReturnButton();
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
                OpenViewHelper.openView(GroupListView.class, getApplicationContext());
            }
        });
    }
}
