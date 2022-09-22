package com.example.payme20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import View.EventFragmentGroupPage;
import View.MemberFragmentGroupPage;
import helpers.GroupPageAdapter;


import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private TabLayout groupPageTabs;
    private ViewPager groupPageViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_page2);

        groupPageTabs = findViewById(R.id.groupPageTabLayout);
        groupPageViewPager= findViewById(R.id.groupPageViewPager);

        groupPageTabs.setupWithViewPager(groupPageViewPager);

        GroupPageAdapter gpAdapter = new GroupPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        gpAdapter.addGroupPageFragments(new EventFragmentGroupPage(),"Events" );
        gpAdapter.addGroupPageFragments(new MemberFragmentGroupPage(), "Members");

        groupPageViewPager.setAdapter(gpAdapter);





    }


}