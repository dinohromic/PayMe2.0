/* The responsibility of this class is to be to first view when starting
the application. */
package com.example.payme20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.payme20.helpers.OpenViewHelper;
import com.example.payme20.views.GroupListView;
import com.example.payme20.views.GroupCreateView;

/**
 * This class is responsible for the view of the start page
 */
public class MainActivity extends AppCompatActivity {
    
    private Button openGroupListButton;
    private Button createGroupButton;
    private Button darkModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setPageNavigation();


    }
    private void setPageNavigation(){
        setOpenViewListener(this.openGroupListButton, GroupListView.class);
        setOpenViewListener(this.createGroupButton, GroupCreateView.class);
        setDarkModeListener();
    }

    private void findViewsById(){
        this.darkModeButton = findViewById(R.id.darkModeButton);
        this.openGroupListButton = findViewById(R.id.groupPageButton);
        this.createGroupButton = findViewById(R.id.addMemberButton);
    }

    private void setOpenViewListener(Button openButton, Class<?> viewToOpen){
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenViewHelper.openView(viewToOpen, MainActivity.this);
            }
        });
    }

    private void setDarkModeListener(){
        this.darkModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });

    }
}