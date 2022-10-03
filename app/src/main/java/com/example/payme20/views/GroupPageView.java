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
        groupPageGroupName = findViewById(R.id.groupPageGroupName);
        Group myObject = (Group) getIntent().getSerializableExtra("GROUP_KEY");
        groupPageGroupName.setText(myObject.getGroupName());
        groupPageTabs = findViewById(R.id.groupPageTabLayout);
        groupPageViewPager= findViewById(R.id.groupPageViewPager);
        groupPageTabs.setupWithViewPager(groupPageViewPager);

        GroupPageAdapter gpAdapter = new GroupPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        eventFragmentGroupPage = new EventFragmentGroupPage();
        memberFragmentGroupPage = new MemberFragmentGroupPage();
        eventFragmentGroupPage.setGroup(myObject);
        memberFragmentGroupPage.setGroup(myObject);

        gpAdapter.addGroupPageFragments(eventFragmentGroupPage,"Events");
        gpAdapter.addGroupPageFragments(memberFragmentGroupPage, "Members");

        groupPageViewPager.setAdapter(gpAdapter);

        initializeView();
        setListenerReturnButton(this.returnButton);
    }

    private void initializeView(){
        this.returnButton =  findViewById(R.id.groupPageReturnButton);
    }

    private void setListenerReturnButton(ImageButton returnButton){
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openView(GroupListView.class, getApplicationContext());
            }
        });
    }
}
