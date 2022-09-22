package com.example.payme20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import View.EventFragmentGroupPage;
import View.MemberFragmentGroupPage;

public class MainActivity extends AppCompatActivity {

    TabItem eventTabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_page2);

        eventTabButton = findViewById(R.id.tabItemEventsButton);

        getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragment, new EventFragmentGroupPage()).commit();

    }

    /*@Override
    public void onClick(View v){
        if(v.getId()==R.id.tabItemEventsButton){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutFragment, new EventFragmentGroupPage()).commit();
        }

    }*/
}