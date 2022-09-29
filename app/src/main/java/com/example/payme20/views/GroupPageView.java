package com.example.payme20.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.payme20.R;
import com.example.payme20.helpers.GroupPageAdapter;
import com.example.payme20.model.Group;
import com.google.android.material.tabs.TabLayout;


public class GroupPageView extends AppCompatActivity {

    private TabLayout groupPageTabs;
    private ViewPager groupPageViewPager;
    private TextView groupPageGroupName;

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

        gpAdapter.addGroupPageFragments(new EventFragmentGroupPage(),"Events" );
        gpAdapter.addGroupPageFragments(new MemberFragmentGroupPage(), "Members");

        groupPageViewPager.setAdapter(gpAdapter);
    }
}
